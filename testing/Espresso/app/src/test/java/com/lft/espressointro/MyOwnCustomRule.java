package com.lft.espressointro;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Created by laaptu on 3/1/16.
 */
public class MyOwnCustomRule implements MethodRule {
    // http://cwd.dhemery.com/2010/12/junit-rules/
    /**
     * Does this mean that any statement passed to it can be modified
     * or any statement that is passed through it if contains any dependency,
     * it evaluates ,adds or do something like that in case of Android Activity rule*/

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        // TODO Auto-generated method stub
        System.out.println("Custom Rule apply() called");
        String className = method.getMethod().getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        return new MyOwnCustomRuleStatement(base, method, target);
        //or simply return statement
        //return base;
    }

    public class MyOwnCustomRuleStatement extends Statement {

        private Statement base;

        public MyOwnCustomRuleStatement(Statement base, FrameworkMethod method, Object target) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {
            // TODO Auto-generated method stub
            base.evaluate();
            System.out.println("MyOwnCustomRuleStatement evaluate() called");

        }

    }

}

