# Log-helper-demo

## 项目介绍

`log-helper-demo`是一系列使用`log-helper`插件的示例项目。

`log-helper`是一个轻量的接口级日志框架，便于节约用户自己设计日志处理器的工作成本。

<br>

<br>

## 分支介绍 - path-match

采用URL过滤的方式，为项目提供细粒度的日志处理能力。

考虑到开发者有时可能会有不同的日志处理需求，例如部分业务需要**打印全日志**，部分业务只需要**打印错误日志**，还有部分核心业务为了适应数据统计需求而需要**存储数据库**等。

### 一、引入maven依赖

```xml
		<dependency>
			<groupId>top.jyannis</groupId>
			<artifactId>log-helper</artifactId>
			<version>0.1.2</version>
		</dependency>
```

<br>

### 二、编写过滤配置

#### 1、注入`LogFilterChainHolder`

```java
@Configuration
public class LogHelperConfig {
    @Bean
    LogFilterChainHolder logFilterChainHolder(){
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //路径为/**下的接口会由LogMode.ALL对应的日志处理器处理
        //LogMode.ALL == "ALL"
        filterChainDefinitionMap.put("/**", LogMode.ALL);
        //路径为/body/**下的接口会由LogMode.ERROR对应的日志处理器处理
        filterChainDefinitionMap.put("/body/**", LogMode.ERROR);
        return new LogFilterChainHolder(filterChainDefinitionMap);
    }
}
```

有了这层配置之后，细粒度的日志处理配置即可在全局生效了。

需要匹配其他URL规则时，只需不断为`filterChainDefinitionMap`添加元素即可。

<br><br>

### 补充说明

#### 1、URL匹配

`filterChainDefinitionMap`过滤器链采用**Ant**风格的模式匹配，主要规则如下：

| 通配符 | 说明                    |
| ------ | ----------------------- |
| ?      | 匹配任何单字符          |
| *      | 匹配0个或任意数量的字符 |
| **     | 匹配0个或更多的目录     |

匹配举例如下：

| URL          | 说明                                                  |
| ------------ | ----------------------------------------------------- |
| /app/*.x     | 匹配所有在app路径下的.x文件                           |
| /app/p?ttern | 匹配/app/pattern、/app/pXttern等，但不匹配/app/pttern |
| /**/example  | 匹配/app/example、/app/foo/example、/example等        |
| /\*\*/\*.jsp | 匹配任何.jsp文件                                      |

<br>

另外，匹配采用**优先匹配后置规则**的方式。

举例：

```java
filterChainDefinitionMap.put("/**", LogMode.ALL);
filterChainDefinitionMap.put("/body/**", LogMode.ERROR);
//这种情况下，有接口进来时先尝试匹配/body/**，匹配不成功才会尝试匹配/**
```

<br>

#### 2、默认提供的日志处理器列表

这里列出了`log-helper`框架原生提供的日志配置项值与日志处理器的对应关系。

| 配置项值        | 日志处理器        | 说明                                                         |
| --------------- | ----------------- | ------------------------------------------------------------ |
| `LogMode.ALL`   | `LogAllHandler`   | 全日志打印                                                   |
| `LogMode.INFO`  | `LogInfoHandler`  | 仅接口无异常时打印<br>（便于开发者实现自己的异常时日志处理器） |
| `LogMode.ERROR` | `LogErrorHandler` | 仅接口发生异常时打印                                         |
| `LogMode.NONE`  | `LogNoneHandler`  | 不打印任何日志                                               |

<br>

#### 3、自定义日志处理器

请参考分支 custom-logHandler

