# Log-helper-demo

## 项目介绍

`log-helper-demo`是一系列使用`log-helper`插件的示例项目。

`log-helper`是一个轻量的接口级日志框架，便于节约用户自己设计日志处理器的工作成本。

<br>

<br>

## 分支介绍 - custom-logHandler

根据业务需要，自定义日志处理器。

### 一、引入maven依赖

```xml
		<dependency>
			<groupId>top.jyannis</groupId>
			<artifactId>log-helper</artifactId>
			<version>${log-helper.version}</version>
		</dependency>
```

<br>

### 二、使用自定义日志处理器

以常见的**日志需要落库**的需求举例。

#### 1、编写自定义日志处理器

为了便于持久层调用（dao层调用），我们可以直接将处理器注册为组件。（也可以不注册，根据开发者需要即可）

这里只是为了演示，所以就不做具体的落库操作了，以注释代替。

```java
/**
 * @author Jyannis
 * @version 1.0 update on 2021/5/30
 */
@Component
public class LogStoreHandler extends AbstractLogHandler {

    /**
     * 注入dao
     * autowire dao
     */
//    @Autowired
//    private MyDao myDao;

    @Override
    public void processAround(LogInfo logInfo) {
        /*
        存库
        store logInfo
         */
//        myDao.insert(logInfo);
    }

    @Override
    public void processAfterThrow(LogInfo logInfo) {
        /*
        存库
        store logInfo
         */
//        myDao.insert(logInfo);
    }

}
```

<br>

#### 2、编写配置文件

1. 将我们自定义编写的日志处理器添加进`LogHandlerHolder`中，并为它添加一个key（下例中为"store"）

   注：由于本例中将`LogStoreHandler`注册为了组件，所以要用参数注入或者`@Autowired`注入的方式，不能直接手动new。难以理解这一点的同学请搜索“Spring注入方式”进行学习。

2. 在`LogFilterChainHolder`中添加需要的过滤器链

```java
/**
 * @author Jyannis
 * @version 1.0 update on 2021/5/30
 */
@Configuration
public class LogHelperConfig {
    
    @Bean
    LogHandlerHolder logHandlerHolder(LogStoreHandler logStoreHandler){
        LogHandlerHolder logHandlerHolder = new LogHandlerHolder();
        logHandlerHolder.addLogHandler("STORE",logStoreHandler);
        return logHandlerHolder;
    }

    @Bean
    LogFilterChainHolder logFilterChainHolder(){
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**", LogMode.ALL);
        filterChainDefinitionMap.put("/body", "STORE");
        return new LogFilterChainHolder(filterChainDefinitionMap);
    }
    
}

```

此时启动项目，访问接口`/body`，即可触发`LogStoreHandler`来完成日志处理。

<br>

<br>