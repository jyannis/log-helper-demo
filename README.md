# Log-helper-demo

## 项目介绍

`log-helper-demo`是一系列使用`log-helper`插件的示例项目。

`log-helper`是一个轻量的接口级日志框架，便于节约用户自己设计日志处理器的工作成本。

<br>

<br>

## 分支介绍 - default

默认方式使用`log-helper`，不添加任何额外配置。

### 一、引入maven依赖

```xml
		<dependency>
			<groupId>top.jyannis</groupId>
			<artifactId>log-helper</artifactId>
			<version>0.1.2</version>
		</dependency>
```

<br>

### 二、编写控制层接口

#### 1、无异常接口示例

```java
@RequestMapping(value = "body",method = RequestMethod.POST)
public User post(@RequestBody User user){
    return user;
}
```

接口产生调用时，`log-helper`会自动帮助开发者生成相关info级别日志信息。

<br>

#### 2、异常接口示例

```java
@RequestMapping(value = "myError",method = RequestMethod.GET)
public String error(User user){
    throw new RuntimeException("some error occurs...");
}
```

接口产生调用时，`log-helper`会自动帮助开发者生成相关error级别日志信息，并打印异常堆栈。

在开发者不使用全局异常处理器下，Spring默认的异常处理器还会多打印一次异常堆栈。但考虑到全局异常处理器往往是有存在的必要的，为了让异常处理器开发者能够只需要关心如何控制异常情况下的<font color='red'>接口返回内容</font>，而无需额外关注如何记录<font color='red'>异常堆栈信息</font>，`log-helper`对异常堆栈也做了相关处理。

<br>

#### 3、文件上传接口示例

```java
@RequestMapping(value = "file",method = RequestMethod.POST)
public String get(@RequestParam("file")MultipartFile file){
    return file.getOriginalFilename();
}
```

文件可能过大，不宜采用和一般参数相同的处理方式，所以文件参数在`log-helper`中做了单独处理。

对于文件参数，只会打印文件名称及文件大小。