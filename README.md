## 简单介绍
data-service里面是一个使用springboot开发的服务，用于给前端提供数据接口。

vue-ui是前端代码，使用vue-cli搭建。

data里面是从网络上搜集到的豆瓣网的一些数据，数据并不是很全。其中origin是网络上查找到的原始数据，未做修改。

本项目最初是为了学习Vue而做的，学习练习期间发现如果没有提供数据的接口，有些Vue组件不容易使用上，会出现为了使用而使用，所以就计划着使用springboot作为服务端提供数据接口


## 项目涉及  
springboot  
mybatis  
maven  
vue  
nodejs  
mysql  

## 项目启动方式  
项目使用idea在windows下面开发。  
可以使用./data/database下面的文件在数据库中建表以及导入数据，如果想从头开始可以使用./data/origin里面的原始数据，.xls可以使用比如navicat直接导入，.json可以使用我工程里的DataIntoDatabase.java来协助导入。  
数据源配置完成之后就可以启动springboot客户端，入口是DoubanDataApplication.java。  
此处假设还没安装nodejs，那么就需要取下载nodejs最新版安装。  
安装好nodejs后，切换到vue-ui目录下，打开命令行输入npm install来安装依赖，如果npm下载依赖失败，那么可以切换到淘宝的源npm install -g cnpm --registry=https://registry.npm.taobao.org，然后使用cnpm install来下载依赖。  
依赖下载好之后，可以在vue-ui目录下命令行输入npm start来启动前端项目。  
服务端和前端均启动完成之后就能在浏览器中输入：http://localhost:7080/来访问了。  
启动教程到此为止，谢谢。  


