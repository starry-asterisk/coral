/* 
 * ApplicationContextProvider.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www.application;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware{
	/* 의존성 주입을 위한 context provider */
    
	/** spring 의존성 관리 객체 */
    private static ApplicationContext applicationContext;
    
    /** spring 의존성 관리 객체 getter */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /** spring 의존성 관리 객체 setter */
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = ctx;
	}
 
}
