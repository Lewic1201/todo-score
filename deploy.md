## 前后端部署手册

####1. 打开vue工程

####2. 打开config/index.js文件, 确保module.exports下的build中的assetsPublicPath为'/'

> 意即, 你设置为 ‘/’ , 表示编译后的静态文件要被部署到服务根路径下.
>> 例如:  
>>  1.如果部署到Nginx, 映射到你Nginx的域名为 example.com 并且Nginx访问的根目录为文件夹A的话, 编译后的文件将会被部署到文件夹A路径下;  
>>  2.如果采用SpringBoot的话, 默认静态文件根路径为src/main/resource/static, 那么就需要将编译后的文件放在static下.  

####3. 切换到Vue工程根目录,执行打包命令：npm run build

####4. 打开生成的dist文件夹，将里里面文件及目录拷贝至springboot工程中resource/static/文件夹下

####5. 使用maven打包springboot工程

####6. 将target目录下生成的jar包，application.yml，start.sh等文件上传至linux服务器，执行sh start.sh命令

