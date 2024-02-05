package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {
    private final Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getAllActiveProducts(..))")
    public void getProducts() {
    }

//    @Before("getProducts()")
//    public void beforeGetProducts() {
//        logger.info("Вызван метод getAllActiveProducts()");
//    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.restoreById(int))")
    public void restoreProduct() {
    }

//    @After("restoreProduct()")
//    public void afterRestoreProduct(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        logger.info("Вызван метод restoreById с идентификатором {}.", args[0]);
//    }

//    public void testAdvice(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        StringBuilder builder = new StringBuilder("Вызван метод с параметрами: ");
//        for (Object arg : args) {
//            builder.append(arg).append(", ");
//        }
//        // Вызван метод с параметрами: 7, Petya, 4.56, cat,
//        builder.setLength(builder.length() - 2);
//        builder.append(".");
//        logger.info(builder.toString());
//    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductById(int))")
    public void getActiveProductById() {
    }

//    @AfterReturning(pointcut = "getActiveProductById()", returning = "result")
//    public void afterReturningProduct(JoinPoint joinPoint, Object result) {
//        Object id = joinPoint.getArgs()[0];
//        logger.info("Метод getActiveProductById вызван с параметром {} и успешно вернул результат: {}.", id, result);
//    }

//    @AfterThrowing(pointcut = "getActiveProductById()", throwing = "e")
//    public void throwingExceptionWhileReturningProduct(JoinPoint joinPoint, Exception e) {
//        Object id = joinPoint.getArgs()[0];
//        logger.warn("Метод getActiveProductById вызван с параметром {} и выбросил ошибку: {}.", id, e.getMessage());
//    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductCount(..))")
    public void getActiveProductCount() {
    }

//    @Around("getActiveProductCount()")
//    public Object aroundGettingProductCount(ProceedingJoinPoint joinPoint) {
//        // код, выполняющийся до исходного метода
//        logger.info("Вызван метод getActiveProductCount.");
//        long start = System.currentTimeMillis();
//
//        Object result;
//
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//
//        // код, выполняющийся после исходного метода
//        long time = System.currentTimeMillis() - start;
//        logger.info("Метод getActiveProductCount отработал за {} миллисекунд с результатом {}.", time, result);
//        return result;
//    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.*(..))")
    public void runAllJpaProductServiceMethods() {
    }

//    @Before("runAllJpaProductServiceMethods()")
//    public void beforeEachJpaProductServiceMethod(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        String methodName = joinPoint.getSignature().getName();
//        StringBuilder builder = new StringBuilder(String.format("Вызван метод %s", methodName));
//        if (args.length != 0) {
//            builder.append(" c параметрами: ");
//            for (Object arg : args) {
//                builder.append(arg).append(", ");
//            }
//            builder.setLength(builder.length() - 2);
//            builder.append(".");
//        }
//        logger.info(builder.toString());
//    }
//
//    @AfterReturning(pointcut = "runAllJpaProductServiceMethods()", returning = "result")
//    public void afterReturningEachJpaProductServiceMethod(JoinPoint joinPoint, Object result) {
//        String methodName = joinPoint.getSignature().getName();
//        logger.info(String.format("Метод %s успешно завершил работу с результатом %s", methodName, result));
//    }
//
//    @AfterThrowing(pointcut = "runAllJpaProductServiceMethods()", throwing = "e")
//    public void afterThrowingEachJpaProductServiceMethod(JoinPoint joinPoint, Exception e) {
//        String methodName = joinPoint.getSignature().getName();
//        logger.warn(String.format("Метод %s выбросил ошибку %s", methodName, e.getMessage()));
//    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services..*(..))")
    public void runAllServicesMethods() {
    }

    @Before("runAllServicesMethods()")
    public void beforeEachServiceMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        String[] splitDeclaringTypeName = joinPoint.getSignature().getDeclaringTypeName().split("\\.");
        String className = splitDeclaringTypeName[splitDeclaringTypeName.length - 1];
        StringBuilder builder = new StringBuilder(String.format("Вызван метод %s класса %s", methodName, className));
        if (args.length != 0) {
            builder.append(" c параметрами: ");
            for (Object arg : args) {
                builder.append(arg).append(", ");
            }
            builder.setLength(builder.length() - 2);
            builder.append(".");
        }
        logger.info(builder.toString());
    }

    @AfterReturning(pointcut = "runAllServicesMethods()", returning = "result")
    public void afterReturningEachServiceMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String[] splitDeclaringTypeName = joinPoint.getSignature().getDeclaringTypeName().split("\\.");
        String className = splitDeclaringTypeName[splitDeclaringTypeName.length - 1];
        logger.info(String.format("Метод %s класса %s успешно завершил работу с результатом %s", methodName, className,
                result));
    }

    @AfterThrowing(pointcut = "runAllServicesMethods()", throwing = "e")
    public void afterThrowingEachServiceMethod(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        String[] splitDeclaringTypeName = joinPoint.getSignature().getDeclaringTypeName().split("\\.");
        String className = splitDeclaringTypeName[splitDeclaringTypeName.length - 1];
        logger.info(String.format("Метод %s класса %s выбросил ошибку %s", methodName, className, e.getMessage()));
    }
}
