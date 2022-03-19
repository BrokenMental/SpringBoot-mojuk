package inhatc.group.mojuk.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//AOP 설정
@Component
@Aspect
public class AopConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(AopConfiguration.class);

	@Pointcut("execution(* inhatc.group.mojuk..*(..))")
	public void publicAll() {

	}

	@Around("publicAll()")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();

		if (name.contains("Controller") == true) {
			type = "AOP :: Controller >>> ";

		} else if (name.contains("Mapper") == true) {
			type = "AOP :: Mapper >>> ";
		}

		logger.info(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
	
	/*
	 * @Before("execution(* inhatc.group.mojuk..*(..))") public Object
	 * doSomethingBefore(ProceedingJoinPoint joinPoint) throws Throwable {
	 * logger.info("AOP : Before " + joinPoint); return joinPoint.proceed(); }
	 * 
	 * @After("execution(* inhatc.group.mojuk..*(..)) ") public Object
	 * doSomethingAfter(ProceedingJoinPoint joinPoint) throws Throwable {
	 * logger.info("AOP : After " + joinPoint); return joinPoint.proceed(); }
	 */
    
	/*
	 * @Pointcut("execution(* inhatc.group.mojuk..*(..))") public void publicAll() {
	 * 
	 * }
	 * 
	 * //@
	 * Around("execution(* inhatc.group.mojuk..*Controller.*(..)) or execution(*	inhatc.group.mojuk..*Mapper.*(..)))"
	 * )
	 * 
	 * @Around("publicAll()") public Object printLog(ProceedingJoinPoint joinPoint)
	 * throws Throwable {
	 * 
	 * String type = ""; String name =
	 * joinPoint.getSignature().getDeclaringTypeName();
	 * 
	 * if (name.contains("Controller") == true) { type = "Controller ===> ";
	 * 
	 * } else if (name.contains("Mapper") == true) { type = "Mapper ===> "; }
	 * 
	 * logger.info(type + name + "." + joinPoint.getSignature().getName() + "()");
	 * return joinPoint.proceed(); }
	 */
}
