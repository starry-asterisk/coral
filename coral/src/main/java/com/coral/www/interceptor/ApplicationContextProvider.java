package com.coral.www.interceptor;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware{
    
    private static ApplicationContext applicationContext;
    
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = ctx;
	}
 
}
