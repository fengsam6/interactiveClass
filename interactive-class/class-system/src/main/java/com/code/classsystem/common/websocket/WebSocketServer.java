package com.code.classsystem.common.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.classsystem.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/imServer/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clientsMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 登录userId
     */
    private String userId = null;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.userId = userId;
        this.session = session;
        if (clientsMap.containsKey(userId)) {
            clientsMap.remove(userId);
            clientsMap.put(userId, session);
            //加入set中
        } else {
            clientsMap.put(userId, session);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendJsonMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String userId = this.userId;
        if (clientsMap.containsKey(userId)) {
            clientsMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自用户id" + userId + ",消息报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        if (!StringUtils.isEmpty(message)) {
            try {
                sendToOther(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendJsonMessage(String message) throws IOException {
        sendJsonMessage(this.session,this.userId,message);
    }


    /**
     *
     * @param session
     * @param jsonMessage  json字符串
     * @throws IOException
     */
    public  void sendMessage(Session session, String jsonMessage) throws IOException {
        session.getBasicRemote().sendText(jsonMessage);
    }

    public  void sendJsonMessage(Session session,String toUserId, String message) throws IOException {
        if(JsonUtils.isJsonStr(message)){
            sendMessage(session,message);
            return ;
        }
        //解析发送的报文
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", this.userId);
        jsonObject.put("content",message);
        jsonObject.put("toUserId",toUserId);
        String msg = jsonObject.toJSONString();
        log.info("发送消息到:" + userId + "，报文:" + msg);
        session.getBasicRemote().sendText(msg);
    }


    /**
     * 发送自定义消息
     */
    public  void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (!StringUtils.isEmpty(userId) && clientsMap.containsKey(userId)) {
            Session session = clientsMap.get(userId);
            sendJsonMessage(session,userId, message);
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }

    /**
     * 发送信息给指定ID用户，如果用户不在线则返回不在线信息给自己
     *
     * @param message
     * @param sendUserId
     * @throws IOException
     */
    public void sendToUser(String message, String sendUserId) throws IOException {
        String userId = this.userId;
        if (!userId.equals(sendUserId) && clientsMap.get(sendUserId) != null) {
            Session session = clientsMap.get(userId);
            sendJsonMessage(session,sendUserId, message);
        } else {
            //如果用户不在线则返回不在线信息给自己
            sendToUser("当前用户不在线", userId);
        }
    }

    public void sendToUsers(String message, String[] sendUserIds) throws IOException {
        Set<String> userIds = new HashSet<>(Arrays.asList(sendUserIds));
        sendToUsers(message, userIds);
    }

    public void sendToUsers(String message, Set<String> sendUserIds) throws IOException {
        String userId = this.userId;
        for (String to : sendUserIds) {
            if (!userId.equals(to) && clientsMap.get(to) != null) {
                Session session = clientsMap.get(userId);
                sendJsonMessage(session,message, message);
            }
        }
    }

    /**
     * 发送信息给所有人
     *
     * @param message
     * @throws IOException
     */
    public void sendToAll(String message) throws IOException {
        for (String key : clientsMap.keySet()) {
            try {
                Session session = clientsMap.get(key);
                sendMessage(session, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToOther(String message) throws IOException {
        Set<String> userIds = clientsMap.keySet();
        sendToUsers(message, userIds);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

