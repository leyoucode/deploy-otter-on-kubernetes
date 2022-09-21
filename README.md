# k8s 部署 Otter

‼️otter已在阿里云推出商业化版本，开源版本估计不维护了。因此建议采用源码编译的方式，bug太多了，后期需要自己修复。

## 1、基于源码编译打包

### 1.1、 下载源文件

https://github.com/alibaba/otter/releases

![download-source-code](attachments/download-source-code.png)

### 1.2、 Maven 编译打包

```shell
tar zxvf otter-otter-4.2.18.tar.gz
cd otter-otter-4.2.18
mvn clean install -Dmaven.test.skip -Denv=release
```

编译报错如下：

```
[ERROR] Failed to execute goal on project shared.common: Could not resolve dependencies for project com.alibaba.otter:shared.common:jar:4.2.18: Could not find artifact org.jtester:jtester:jar:1.1.8 in rdc-releases (https://packages.aliyun.com/maven/repository/2041383-release-F0cHwt/) -> [Help 1]
```

![build-error1](attachments/build-error1.png)


解决方案参考：https://github.com/alibaba/otter/issues/2901


执行lib目录下的install.sh 文件；

```
cd lib
sh install.sh
```

报错如下：

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-install-plugin:2.4:install-file (default-cli) on project standalone-pom: File not found /Users/liuwei/Documents/github/deploy-otter-on-kubernetes/otter-otter-4.2.18/lib/fastsql-2.0.0_preview_135.pom: /Users/liuwei/Documents/github/deploy-otter-on-kubernetes/otter-otter-4.2.18/lib/fastsql-2.0.0_preview_135.pom (No such file or directory) -> [Help 1]
```

![error2](attachments/error2.png)

解决方案参考：https://github.com/alibaba/otter/issues/824

![hulie](attachments/hulie.png)

那就先不去管它！

继续执行编译：

```
mvn clean install -Dmaven.test.skip -Denv=release
```

继续得到报错信息如下：

```
[ERROR] Failed to execute goal on project shared.common: Could not resolve dependencies for project com.alibaba.otter:shared.common:jar:4.2.18: Failed to collect dependencies at org.jtester:jtester:jar:1.1.8 -> mockit:jmockit:jar:0.999.10: Failed to read artifact descriptor for mockit:jmockit:jar:0.999.10: Could not find artifact mockit:mockit:pom:0.999.10 in rdc-releases (https://packages.aliyun.com/maven/repository/2041383-release-F0cHwt/) -> [Help 1]
```

![error3](attachments/error3.png)

解决方案参考：https://github.com/alibaba/otter/issues/290

![error4](attachments/error4.png)

```
vim lib/jmockit-0.999.10.pom
```

注释掉如下行：

![remove](attachments/remove.png)

再次编译，终于成功了：


![success](attachments/success.png)


编译完成后，会在根目录下产生target/manager.deployer-4.2.18.tar.gz 和 target/manager.deployer-4.2.18.tar.gz

![target](attachments/target.png)


## 2、构建 manager 镜像

### 2.1 创建**manager.deployer-4.2.18/manager**目录并解压manager.deployer-4.2.18.tar.gz到该目录

```
mkdir -p manager/manager.deployer-4.2.18
tar zxvf otter-otter-4.2.18/target/manager.deployer-4.2.18.tar.gz -C manager/manager.deployer-4.2.18
```

### 2.2 让启动脚本作为前台进程保持运行

```
cd manager
vim manager.deployer-4.2.18/bin/startup.sh
```

删除启动命令最后一个 **&** 符号；

![delete1](attachments/delete1.png)

### 2.3 打包

```
tar czvf manager.deployer-4.2.18.tar.gz manager.deployer-4.2.18
```

![dabao1](attachments/dabao1.png)

### 2.4 制作docker镜像

- 编写Dockerfile （基础镜像地址：https://github.com/liuweicode/docker-image-base/tree/main/openjdk）
- 编写构建脚本 build.sh

```
sh build.sh
```

## 3、构建 node 镜像

### 3.1 创建**node.deployer-4.2.18/node**目录并解压node.deployer-4.2.18.tar.gz到该目录

```
mkdir -p node/node.deployer-4.2.18
tar zxvf otter-otter-4.2.18/target/node.deployer-4.2.18.tar.gz -C node/node.deployer-4.2.18
```

### 3.2 让启动脚本作为前台进程保持运行

```
cd node
vim node.deployer-4.2.18/bin/startup.sh
```

删除启动命令最后一个 **&** 符号；

![delete2](attachments/delete2.png)


### 3.3 打包

```
tar czvf node.deployer-4.2.18.tar.gz node.deployer-4.2.18
```
![temp_2022-09-21-11-51-58](attachments/temp_2022-09-21-11-51-58.png)

### 2.4 制作docker镜像

- 编写Dockerfile （基础镜像地址：https://github.com/liuweicode/docker-image-base/tree/main/openjdk）
- 编写构建脚本 build.sh

```
sh build.sh
```

## 4、创建configmap

创建 **config** 目录，并 将默认配置文件复制过来；
```
mkdir config
cp manager/manager.deployer-4.2.18/conf/otter.properties config/otter-manager.properties
cp node/node.deployer-4.2.18/conf/otter.properties config/otter-node.properties
```

### 4.1 修改 **otter-manager.properties** 

![temp_2022-09-21-12-58-21](attachments/temp_2022-09-21-12-58-21.png)

### 4.2 修改 **otter-node.properties**

![temp_2022-09-21-12-59-05](attachments/temp_2022-09-21-12-59-05.png)

### 4.3 创建configmap

```
cd config
kubectl create namespace uat-otter
kubectl create configmap uat-otter-config --from-file=otter-manager.properties --from-file=otter-node.properties -n uat-otter
```

## 4、部署 otter manager

### 4.1 初始化otter manager系统表

参考：https://github.com/alibaba/otter/wiki/Manager_Quickstart


下载：

```
wget https://raw.github.com/alibaba/otter/master/manager/deployer/src/main/resources/sql/otter-manager-schema.sql
```

载入：

```
source otter-manager-schema.sql
```

### 4.2 搭建ZooKeeper集群

参考：[deploy-zookeeper-cluster-on-kubernetes](https://github.com/liuweicode/deploy-zookeeper-cluster-on-kubernetes)

### 4.3 编写 otter-manager.yaml

https://github.com/liuweicode/deploy-otter-on-kubernetes/blob/main/manager/otter-manager.yaml

### 4.4 编写 otter-node.yaml

https://github.com/liuweicode/deploy-otter-on-kubernetes/blob/main/node/otter-node.yaml

重点关注以下行：

![temp_2022-09-21-13-26-40](attachments/temp_2022-09-21-13-26-40.png)

## 5、配置

![temp_2022-09-21-13-29-06](attachments/temp_2022-09-21-13-29-06.png)


[使用介绍](https://github.com/alibaba/otter/wiki/Manager%E4%BD%BF%E7%94%A8%E4%BB%8B%E7%BB%8D)

TODO


















