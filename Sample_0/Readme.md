# 主要功能

* 单表增删改查
* 分页
    * jar依赖

          pagehelper-4.1.1.jar
* 上传下载
    * 通过tomcat配置虚拟目录[server.xml]

          <Context docBase="C:/image_folder" path="/image" reloadable="true"/>
    * jar包依赖

          commons-fileupload-1.3.3.jar
          commons-io-2.4.jar

* JSON数据交互
    * jar包依赖

          jackson-annotations-2.9.0.jar
          jackson-core-2.9.8.jar
          jackson-databind-2.9.8.jar
          fastjson-1.2.47.jar



