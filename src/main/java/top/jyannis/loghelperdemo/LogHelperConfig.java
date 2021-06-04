package top.jyannis.loghelperdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.jyannis.loghelper.holder.LogFilterChainHolder;
import top.jyannis.loghelper.holder.LogHandlerHolder;
import top.jyannis.loghelper.processor.LogAspectProcessor;

import java.util.LinkedHashMap;


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
