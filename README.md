# CourseMultiplatform
掌邮课表多平台版，基于 ComposeMultiplatform

支持以下平台:
- Android
- iOS
- Mac
- Windows
- Linux

因为目前暂时没有做复杂的本地数据保存功能，所以每次打开课表都需要即时网络请求，在首次打开会看到 item 闪一下

## 打包教程
请先下载 idea 或者 Android Studio (后文简称为 AS)

### 检查环境
请看[官方教程](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-setup.html#check-your-environment)

### Android 打包
直接使用 Android Studio，然后选择配置为 composeApp 即可

注意：上述打包 AS 会直接安装到手机上，从手机上提取出的 apk 分享给他人无法安装

可以使用 AS 打包旁边的锤子🔨按钮打出默认的 debug apk (输出文件在 build/outputs/apk/debug 下)

### iOS 打包

> 前提：只能在 Mac 上才能打包

#### 模拟器
idea 或 AS 下载 `Kotlin Multiplatform Mobile` 插件，然后运行 `iosApp` 配置即可

#### 真机
因为本人无真机，所以无法调试，请看[官方教程](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-on-a-real-ios-device)

### Mac 打包

> 前提：只能在 Mac 上才能打包

在打包前请先确认 jdk 文件是否完整，请查看 [缺失 jpackage](#缺失jpackage)

运行 gradle 命令，输出文件在 build/compose/binaries/main(main-release)/dmg
```shell
# 可以点击 idea 或 AS 右侧的大象图标里 Tasks/compose desktop 下同名任务
# debug 包
./gradlew packageDmg

# release 包
./gradlew packageReleaseDmg
```

### Win 打包

> 前提：只能在 Win 上才能打包

在打包前请先确认 jdk 文件是否完整，请查看 [缺失 jpackage](#缺失jpackage)

运行 gradle 命令，输出文件在 build/compose/binaries/main(main-release)/msi
```shell
# 可以点击 idea 或 AS 右侧的大象图标里 Tasks/compose desktop 下同名任务
# debug 包
./gradlew packageMsi

# release 包
./gradlew packageReleaseMsi
```

### Linux 打包

> 前提：只能在 Linux 上才能打包

在打包前请先确认 jdk 文件是否完整，请查看 [缺失 jpackage](#缺失jpackage)

运行 gradle 命令，输出文件在 build/compose/binaries/main(main-release)/deb
```shell
# 可以点击 idea 或 AS 右侧的大象图标里 Tasks/compose desktop 下同名任务
# debug 包
./gradlew packageDeb

# release 包
./gradlew packageReleaseDeb
```

## 问题

### 缺失jpackage
gradle 打包可能会遇到以下问题：
```
* What went wrong:
Execution failed for task ':composeApp:checkRuntime'.
> Failed to check JDK distribution: 'jpackage' is missing
  JDK distribution path: /Applications/Android Studio.app/Contents/jbr/Contents/Home

```
Compose 在 desktop 端打包依赖 jpackage, AS 自带的 JDK 不完整，
请在 Settings - Build, Execution... - Build Tools - Gradle 中设置完整的 JDK
