package com.x2021.mycloud.multidatasoucexa.aop;


import com.x2021.mycloud.multidatasoucexa.dao.DB1Selector;
import com.x2021.mycloud.multidatasoucexa.dao.DB2Selector;
import com.x2021.mycloud.multidatasoucexa.holder.DatabaseContextHolder;
import com.x2021.mycloud.multidatasoucexa.type.DatabaseType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAOP {

    @Pointcut("execution(* com.x2021.mycloud.multidatasoucexa.dao.*.*(..))")
    public void declareJointPointExpression() {

    }

    @Before("declareJointPointExpression()")
    public void setDataSource(JoinPoint joinPoint) {
        if (joinPoint.getTarget() instanceof DB1Selector) {
            DatabaseContextHolder.set(DatabaseType.DB1);
        } else if (joinPoint.getTarget() instanceof DB2Selector) {
            DatabaseContextHolder.set(DatabaseType.DB2);
        } else {
            DatabaseContextHolder.set(DatabaseType.DB1);
        }
    }

    @After("declareJointPointExpression()")
    public void afterReturn() {
        DatabaseContextHolder.remove();
    }
}
