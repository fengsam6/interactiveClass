### 项目名称

#### 互动课堂

### 项目简介

本项目是一个学生互动课堂的微信小程序，采用前后端分离开发模式。

### 目录结构

#### 1.interactive-class

后端逻辑代码，采用springboot构建，运行ClassSystemApplication类启动程序,权限模块采用Shiro控制，

数据库使用mysql,用token保存用户信息。

#### 2.interactiveClassAdminUi

互动课堂PC端前端代码，基于VUE开发的，刚开始需要安装依赖环境，npm install.

安装完环境后执行启动命令：**npm run dev**

*注意在npm install安装依赖环境中可能会出现安装sass失败，原因是默认是从github下载存在外网限制，建议更换为国内淘宝镜像或者国内其它镜像下载*

#### 3.interactiveClassUI

这个目录是小程序运行的代码，基于uni-app开发，引用了第三方UI：vant-weapp,启动程序类似于上面的PC前端，第一步需要安装依赖环境：npm install , 第二步运行程序：**npm run dev:mp-weixin**

### 系统流程

1.老师和学生可通过微信端注册用户。

老师：新建班级->新建课程（添加现有的班级）->老师分享班级二维码可邀请学生加入班级。这属于一个初始化的过程，老师完成这些操作流程后，学生可以使用学生的功能。。。

