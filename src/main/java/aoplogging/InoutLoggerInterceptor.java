package aoplogging;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Vanwin
 * Date: 14-10-20
 */
public class InoutLoggerInterceptor implements MethodInterceptor {
    private InoutLoggerInterceptor(){
    }

    private static InoutLoggerInterceptor instance = new InoutLoggerInterceptor();

    public static InoutLoggerInterceptor getInstance(){
        return instance;
    }

    private final Logger logger = LoggerFactory.getLogger(InoutLoggerInterceptor.class);

    private boolean isPrint = true;

    public boolean getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(boolean isPrint) {
        isPrint = isPrint;
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (isPrint) {
            return this.invokeWithLogging(mi);
        } else {
            return mi.proceed();
        }
    }


    private Object invokeWithLogging(MethodInvocation mi) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object obj = mi.proceed();
        logger.info("invokeMethod: {}, elapsed time (ms): {}", mi.getMethod().getName(), (System.currentTimeMillis() - startTime));

        Object[] args = mi.getArguments();
        if (null != args && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                logger.info("args[{}]: {}" ,i, JSON.toJSONString(args[i]));
            }
        }

        logger.info("returns:{}", JSON.toJSONString(obj));
        return obj;
    }
}
