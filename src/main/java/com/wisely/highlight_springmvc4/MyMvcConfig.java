package com.wisely.highlight_springmvc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.wisely.highlight_springmvc4.interceptor.DemoInterceptor;

/**
 * 此处无任何特别，只是普通的Spring配置类。这里我们配置了一个JSP的ViewResolver，用来映射路径和实际页面的位置，
 * 其中，@EnableWebMvc注解会开启一些默认配置，如一些ViewResolver或者MmessageConverter。
 * 
 * 在此处要特别解释一下SpringMVC的ViewResolver，这是SpringMVC视图（JSP下就是html）渲染的核心机制；
 * SpringMVC里有一个接口叫做ViewResolver（我们的ViewResolver都实现该接口），实现这个接口要重写方法resolveViewName(),
 * 这个方法的返回值是接口View,而View的职责就是使用model、request、response对象，并将渲染的视图（不一定是html，可能是json、xml、pdf）
 * 返回给浏览器。
 * 
 * 可能读者对路径前缀配置为/WEB-INF/classes/views/有些奇怪，怎么和我开发的目录不一致？
 * 因为看到的页面效果是运行时而不是开发时的代码，运行时代码会将我们的页面自动编译到/WEB-INF/classes/views/下，
 * 在SpringBoot中，我们将使用Thymeleaf作为模板，因而不需要这样的配置。
 * @author Mask
 *
 */
@Configuration
@EnableWebMvc	//1、@EnableWebMvc开启SpringMvc支持，若无此句，重写WebMvcConfigurerAdapter方法无效
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter{	//继承WebMvcConfigurerAdapter类，重写期方法可以对SpringMvc进行配置
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");	//addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径
	}
	
	@Bean	//配置拦截器的Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}
	
	@Override	//重写addInterceptors方法，注册拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}
}
