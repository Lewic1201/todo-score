## 开发环境搭建手册

1. 安装java,配置环境变量

2. 配置maven为国内的仓库，不然下载很慢，创建~/.m2/settings.xml文件（或者在项目下创建此文件，idea勾选覆盖），文件内容如下：

```xml
<?xml version="1.0"?>
<settings>
  <mirrors>
        <mirror>
            <id>alimaven</id>
            <name>aliyun maven</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
  </mirrors>
  <profiles>
    <profile>
       <id>nexus</id>
        <repositories>
            <repository>
                <id>nexus</id>
                <name>local private nexus</name>
                <url>http://maven.oschina.net/content/groups/public/</url>
                <releases>
                    <enabled>true</enabled>
                </releases>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
            </repository>
        </repositories>

        <pluginRepositories>
            <pluginRepository>
            <id>nexus</id>
            <name>local private nexus</name>
            <url>http://maven.oschina.net/content/groups/public/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            </pluginRepository>
        </pluginRepositories>
    </profile></profiles>
</settings>
```



## Github访问host配置

C:\Windows\System32\drivers\etc\hosts 文件添加

```
192.30.253.112 github.com
151.101.185.194 github.global.ssl.fastly.net
```



## vue开发环境搭建

1. 安装node.js，下载对应你系统的Node.js版本:https://nodejs.org/en/download/ ，并安装

2. 运行命令提示符，执行node -v，npm -v，确认安装成功。

3. 设置npm下载包的位置

   ``` npm
   npm config set prefix "D:\Program Files\nodejs\node_global"
   npm config set cache "D:\Program Files\nodejs\node_cache"
   ```
   
4. 修改用户环境变量
   ```
   C:\Users\Administrator\AppData\Roaming\npm
   ->
   D:\Program Files\nodejs\node_global
   ```

5. 安装常用模块
   ```
   npm install express -g     # -g是全局安装的意思
   ```

6. 设置可以运行脚本
    ```
    get-ExecutionPolicy #显示Restricted（表示状态是禁止的）
    set-ExecutionPolicy #RemoteSigned
    get-ExecutionPolicy #显示RemoteSigned
    ```
   
7. 使用淘宝npm镜像源下载，cnpm和npm用法相同，不过cnpm更适用国内
   ```
   npm install -g cnpm --registry=https://registry.npm.taobao.org
   cnpm install vue-cli -g
   ```
   
8. 创建vue项目（创建名字为‘vue_demo’的文件夹）

   ```
   vue init webpack vue_demo
   ```

9. 手动下载项目的依赖包

   ```
   cd vue_demo
   cnpm install
   ```

10. 运行程序并访问http://localhost:8080
   ```
   npm run dev
   ```
   


## 前后端部署手册

1. 进入vue工程

2. 打开config/index.js文件，确保module.exports下的build中的assetsPublicPath为'/'

  > 意即，当你设置为 ‘/’ ，表示编译后的静态文件要被部署到服务根路径下。例如:  
  >  1. 如果部署到Nginx, 映射到你Nginx的域名为 example.com 并且Nginx访问的根目录为文件夹A的话, 编译后的文件将会被部署到文件夹A路径下;  
  >  2. 如果采用SpringBoot的话, 默认静态文件根路径为src/main/resource/static, 那么就需要将编译后的文件放在static下.  

3. 切换到Vue工程根目录,执行打包命令：  
   ```
   npm run build
   ```

4. 打开生成的dist文件夹，将里里面文件及目录拷贝至springboot工程中resource/static/文件夹下

5. 使用maven打包springboot工程

6. 将target目录下生成的jar包，application.yml，start.sh等文件上传至linux服务器，执行sh start.sh命令

## 运行环境搭建

1. 安装docker

2. 启动mysql8.0,并挂载目录

   ```
   docker run -d --name mysql8.0 --privileged=true -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -v /etc/mysql:/etc/mysql  -v /opt/mysql:/var/lib/mysql -v /etc/localtime:/etc/localtime docker.io/mysql:8.0.19
   ```
