package top.jyannis.loghelperdemo;

import lombok.extern.slf4j.Slf4j;
import top.jyannis.loghelper.domain.LogInfo;
import top.jyannis.loghelper.handler.AbstractLogHandler;
import top.jyannis.loghelper.util.ThrowableUtil;

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