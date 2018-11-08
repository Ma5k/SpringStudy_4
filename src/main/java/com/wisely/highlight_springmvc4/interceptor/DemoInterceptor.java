package com.wisely.highlight_springmvc4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 1、继承HandlerInterceptorAdapter类来实现自定义拦截器
 * 2、重写preHandle方法，在请求发生前执行。
 * 3、重写postHandle方法，在请求完成后执行。
 * 
 * 拦截器（Interceptor）实现对每一个请求处理前后进行相关的业务处理，类似于Servlet的Filter。
 * 可让普通的Bean实现HandlerInterceptor接口或者继承HandlerInterceptorAdapter类来实现自定义拦截器。
 * 通过重写WebMvcConfigurerAdapter的addInterceptors方法来注册自定义的拦截器，本节演示一个简单的拦截器的开发和配置，
 * 业务含义为计算每一次请求的处理时间。
 * @author Mask
 *
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {	//1

	@Override
	public boolean preHandle(HttpServletRequest request, 	//2
			HttpServletResponse response, Object handler) throws Exception{
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, 	//3
			HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception{
		long startTime = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		System.out.println("本次请求处理时间为：" + new Long(endTime - startTime) + "ms");
		request.setAttribute("handlingTime", endTime - startTime);
	}
}
