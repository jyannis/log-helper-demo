package top.jyannis.loghelperdemo;

import org.springframework.stereotype.Component;
import top.jyannis.loghelper.domain.LogInfo;
import top.jyannis.loghelper.handler.AbstractLogHandler;

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
