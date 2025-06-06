package app.pb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.app.service..*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Starting method: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        System.out.println("Finished method: " + joinPoint.getSignature());
        return result;
    }
}
