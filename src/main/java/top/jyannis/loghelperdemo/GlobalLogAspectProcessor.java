package top.jyannis.loghelperdemo;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import top.jyannis.loghelper.domain.LogInfo;
import top.jyannis.loghelper.processor.AbstractLogAspectProcessor;

import java.lang.reflect.Method;

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
