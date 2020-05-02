package com.code.classsystem.common.websocket.userCache;

import com.code.classsystem.entity.ClassUser;
import com.code.classsystem.entity.User;
import com.code.classsystem.service.ClassUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassUserCacheService {
    @Autowired
    private ClassUserService classUserService;
    private Map<String, List<User>> classUserMap = new HashMap<>();

    public List<User> getUserList(String classId) {
        boolean contain = classUserMap.containsKey(classId);
        if (contain) {
            return classUserMap.get(classId);
        }
        List<User> users = classUserService.showClassUsers(classId);
        putClassUserList(classId, users);
        return users;
    }

    public void putClassUserList(String classId, List<User> users) {
        classUserMap.put(classId, users);
    }
}
