package top.jyannis.loghelperdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.jyannis.loghelper.domain.LogMode;
import top.jyannis.loghelper.holder.LogFilterChainHolder;
import top.jyannis.loghelper.holder.LogHandlerHolder;

import java.util.LinkedHashMap;

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
