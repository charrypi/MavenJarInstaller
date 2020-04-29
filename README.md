# MavenJarInstaller

#### 介绍
安装jar到本地仓库小工具，使用javafx开发。底层使用拼接maven上传jar的字符串命令，并返回执行后的结果。

#### 软件架构
JDK8+JAVAFX 8，使用Maven构建项目

#### 安装教程

1. 使用IDE导入maven项目即可

#### 使用说明

1、运行StartApplication类即可启动；
2、在项目根目录运行mvn jfx:jar生成可执行的jar包，jar包位置在target/jfx/app下。

### 运行截图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0429/114017_8cdb80d5_1770544.png "主界面")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0429/114038_c1551cd2_1770544.png "上传中")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0429/114048_990e869b_1770544.png "上传完成")
