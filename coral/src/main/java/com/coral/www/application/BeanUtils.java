/* 
 * BeanUtils.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www.application;

import org.springframework.context.ApplicationContext;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
public class BeanUtils {
	/* 의존성 주입을 제공 */
	
    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
}
