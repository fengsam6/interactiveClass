### 项目名称

#### 互动课堂

### 项目简介

本项目是一个学生互动课堂的微信小程序，采用前后端分离开发模式。

### 目录结构

#### 1.interactive-class

       *  后端逻辑代码，为前端提供json接口。使用springboot、mybati-plus（操作数据库）、shiro（控制系统权限）、mysql等技术开发。
       *  项目启动。使用idea导入项目，下载maven依赖，运行ClassSystemApplication类main方法启动程序。

* 数据库使用mysql

* 前后端使用token验证户信息。

#### 2.interactiveClassAdminUi

​        * 互动课堂PC端后台代码(系统后台管理)，基于[vue  admin tempalate](https://panjiachen.gitee.io/vue-element-admin-site/zh/)开发，使用技术主要有vue、axios、vue router。

* 系统运行。第一次运行需要下载npm 依赖包，npm install（从网上下载npm 依赖包）。npm依赖下载完成后，执行启动命令：**npm run dev**，可以启动前端程序。

*注意在npm install安装依赖环境中可能会出现安装sass失败，原因是默认是从github下载存在外网限制，建议更换为国内淘宝镜像或者国内其它镜像下载*

#### 3.interactiveClassUI

​      这个目录是小程序运行的代码，基于[uni-app](https://uniapp.dcloud.io/collocation/pages)开发，开发微信小程序。使用第三方UI库：[vant-weapp](https://youzan.github.io/vant-weapp/#/button)。搭建系统环境，与普通vue项目一样。  启动程序类似于上面的PC前端，第一步需要安装依赖环境：npm install , 第二步运行程序：**npm run dev:mp-weixin  **

### 系统流程

1.老师和学生可通过微信端注册用户。

老师：新建班级->新建课程（添加现有的班级）->老师分享班级二维码可邀请学生加入班级。这属于一个初始化的过程，老师完成这些操作流程后，学生可以使用学生的功能。。。

