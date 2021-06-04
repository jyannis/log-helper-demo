# Log-helper-demo

## 项目介绍

`log-helper-demo`是一系列使用`log-helper`插件的示例项目。

`log-helper`是一个轻量的接口级日志框架，便于节约用户自己设计日志处理器的工作成本。

<br>

<br>

## 分支介绍 - custom-logInfo

根据业务需要，自定义日志实体数据。

### 一、引入maven依赖

```xml
		<dependency>
			<groupId>top.jyannis</groupId>
			<artifactId>log-helper</artifactId>
			<version>${log-helper.version}</version>
		</dependency>
```

<br>

### 二、自定义日志实体

例如我们现在希望为日志实体添加一个`description`字段。

只需要继承`log-helper`提供的`LogInfo`，补充自定义字段即可。

```java
/**
 * @author Jyannis
 * @version 1.0 update on 2021/6/4
 */
public class GlobalLogInfo extends LogInfo {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
```

<br>

### 三、为实体注入信息

`log-helper`提供的日志切面为开发者提供了钩子函数，可以在切面环绕通知前后为日志实体注入属性。

通过继承`AbstractLogAspectProcessor`可以实现这些函数。

- `buildLogInfo`

  返回一个后续用来操作的日志实体，默认情况下使用的是`new LogInfo()`，这里我们可以返回自己的子类对象。

- `preLogAround`

  环绕通知前置钩子方法，我们可以在这里注入实体信息。

- `postLogAround`

  环绕通知后置钩子方法，本例中不做任何处理。

- `preLogAfterThrow`

  异常通知前置钩子方法，我们可以在这里注入实体信息。

- `postLogAfterThrow`

  异常通知后置钩子方法，本例中不做任何处理。

```java
/**
 * @author Jyannis
 * @version 1.0 update on 2021/6/4
 */
public class GlobalLogAspectProcessor extends AbstractLogAspectProcessor {

    @Override
    public LogInfo buildLogInfo() {
        return new GlobalLogInfo();
    }

    @Override
    public void preLogAround(ProceedingJoinPoint joinPoint, LogInfo logInfo) {
        setDescription(joinPoint,logInfo);
    }

    @Override
    public void postLogAround(ProceedingJoinPoint proceedingJoinPoint, LogInfo logInfo) {

    }

    @Override
    public void preLogAfterThrow(ProceedingJoinPoint joinPoint, LogInfo logInfo) {
        setDescription(joinPoint,logInfo);
    }

    @Override
    public void postLogAfterThrow(ProceedingJoinPoint proceedingJoinPoint, LogInfo logInfo) {

    }

    /**
     * 以swagger为例，获取@ApiOperation上对接口的描述信息
     * take swagger as an example, get the description of the API on @ApiOperation
     * @param joinPoint
     * @param logInfo
     */
    private void setDescription(ProceedingJoinPoint joinPoint, LogInfo logInfo){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        if(annotation != null) {
            ((GlobalLogInfo) logInfo).setDescription(annotation.value());
        }
    }
}
```

<br>

### 四、实现自定义日志处理器

为了让修改后的日志实体数据体现出效果，自然也要做相应的日志处理。

```java
@Slf4j
public class GlobalLogHandler extends AbstractLogHandler {

    @Override
    public void processAround(LogInfo logInfo) {
        log.info("api description: {}",((GlobalLogInfo)logInfo).getDescription());
        log.info("call method: {}",logInfo.getMethod());
        log.info("call url: {}",logInfo.getLookupPath());
        log.info("request params: {}",logInfo.getParams());
        log.info("request ip: {}",logInfo.getRequestIp());
        log.info("request address: {}",logInfo.getAddress());
        log.info("request browser: {}",logInfo.getBrowser());
        log.info("request time cost: {} ms",logInfo.getTime());
    }

    @Override
    public void processAfterThrow(LogInfo logInfo) {
        String stackTrace = ThrowableUtil.getStackTrace(logInfo.getThrowable());
        log.error("api description: {}",((GlobalLogInfo)logInfo).getDescription());
        log.error("call method: {}",logInfo.getMethod());
        log.error("call url: {}",logInfo.getLookupPath());
        log.error("request params: {}",logInfo.getParams());
        log.error("request ip: {}",logInfo.getRequestIp());
        log.error("request address: {}",logInfo.getAddress());
        log.error("request browser: {}",logInfo.getBrowser());
        log.error("request time cost: {} ms",logInfo.getTime());
        log.error(stackTrace);
    }

}
```

<br>

### 五、编写配置文件

相比之前的配置，补充一个`LogAspectProcessor`即可。

```java
/**
 * @author Jyannis
 * @version 1.0 update on 2021/6/4
 */
@Configuration
public class LogHelperConfig {

    @Bean
    LogAspectProcessor logAspectProcessor(){
        return new GlobalLogAspectProcessor();
    }

    @Bean
    LogHandlerHolder logHandlerHolder(){
        LogHandlerHolder logHandlerHolder = new LogHandlerHolder();
        logHandlerHolder.addLogHandler("GLOBAL",new GlobalLogHandler());
        return logHandlerHolder;
    }

    @Bean
    LogFilterChainHolder logFilterChainHolder(){
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**", "GLOBAL");
        return new LogFilterChainHolder(filterChainDefinitionMap);
    }

}
```

<br>

<br>

运行项目，最后效果如下：

```shell
2021-06-05 00:43:22.744  INFO 11832 --- [nio-8080-exec-3] t.j.loghelperdemo.GlobalLogHandler       : api description: a body POST request
```

