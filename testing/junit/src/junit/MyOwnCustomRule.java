package junit;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class MyOwnCustomRule implements MethodRule {
	// http://cwd.dhemery.com/2010/12/junit-rules/

	@Override
	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		// TODO Auto-generated method stub
		System.out.println("Custom Rule apply() called");
		String className = method.getMethod().getDeclaringClass().getSimpleName();
		String methodName = method.getName();
		return new MyOwnCustomRuleStatement(base, method, target);
		// return base;
	}

	public class MyOwnCustomRuleStatement extends Statement {

		public MyOwnCustomRuleStatement(Statement base, FrameworkMethod method, Object target) {

		}

		@Override
		public void evaluate() throws Throwable {
			// TODO Auto-generated method stub
			System.out.println("MyOwnCustomRuleStatement evaluate() called");

		}

	}

}
