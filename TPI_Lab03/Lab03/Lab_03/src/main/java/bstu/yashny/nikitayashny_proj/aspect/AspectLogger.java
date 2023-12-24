package bstu.yashny.nikitayashny_proj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect // Дополнительная функциональность
@Component
public class AspectLogger {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AspectLogger.class);

    @Pointcut("execution(* bstu.yashny.nikitayashny_proj.rest.MainRestController.*(..))")
    public void calledAtMainREstController(){}

    @After("calledAtMainREstController()")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* bstu.yashny.nikitayashny_proj.rest.CarRestController.*(..))")
    public void calledAtCarRestController(){}

    @After("calledAtCarRestController()")
    public void log2(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* bstu.yashny.nikitayashny_proj.rest.UserRentRestController.*(..))")
    public void calledAtUserRentRestController(){}

    @After("calledAtUserRentRestController()")
    public void log3(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}
