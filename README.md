# 项目简介

database-doc-aid 项目能够为 Mysql 数据库生成数据库 pdf 文档，避免开发人员手动维护数据库文档，增加开发人员工作效率。

## 使用方式
运行环境：jdk 1.8+

将代码 clone 到本地：

~~~bash
git clone https://github.com/biyanwen/database-doc-aid.git
~~~

打包，首先进入到项目文件夹下并运行打包命令：

~~~bash
 mvn clean package 
~~~

找到可执行 jar 包执行运行命令：

~~~bash
java -jar database-doc-aid-0.0.1-SNAPSHOT.jar
~~~

**这个时候会自动 弹出项目页面**，如何没有自动弹出那么请手动在浏览器上输入如下地址：http://localhost:10001/index.html。正常情况下你可以看到以下页面：
![img.png](img.png)
将自己的数据库信息正确地填写进去就点击生成文档的按钮可以啦~~
在生成的 pdf 文档中点击表名会跳转到表对应的详情页。
