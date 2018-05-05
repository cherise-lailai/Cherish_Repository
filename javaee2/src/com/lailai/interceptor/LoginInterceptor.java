package com.lailai.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor  extends AbstractInterceptor{
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		Object currentPeople = sessionMap.get("currentPeople");
		if(currentPeople!=null){
			return invocation.invoke();
		}
		logger.error("未登入，回到主页");
		return "index";
	}

}
