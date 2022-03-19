package inhatc.group.mojuk.config;

import lombok.AllArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;
import java.util.List;

//transaction 설정
@Configuration
@AllArgsConstructor
public class TransactionAspect {
	private PlatformTransactionManager transactionManager;

	private static final String EXPRESSION = "execution(* inhatc.group.mojuk..*(..))";

	@Bean
	public TransactionInterceptor transactionAdvice() {

		List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));

		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttribute.setName("*");

		MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
		attributeSource.setTransactionAttribute(transactionAttribute);

		return new TransactionInterceptor(transactionManager, attributeSource);
	}

	@Bean
	public Advisor transactionAdvisor() {

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(EXPRESSION);

		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}
