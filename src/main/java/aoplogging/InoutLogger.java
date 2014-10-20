package aoplogging;

import com.alibaba.fastjson.JSON;
import com.google.inject.Singleton;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Vanwin
 * Date: 14-10-20
 */
public class InoutLogger implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(InoutLogger.class);

    private static boolean isPrint = true;

    public boolean getIsPrint() {
        return InoutLogger.isPrint;
    }

    public void setIsPrint(boolean isPrint) {
        InoutLogger.isPrint = isPrint;
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
