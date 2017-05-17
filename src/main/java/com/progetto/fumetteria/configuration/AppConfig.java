/*

* AppConfig : file per specificare impostazioni del progetto; impostazione di percorso, tipi, codificazione e utilizzo dei file
*

* version 1.0

*

* 29/07/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.progetto.fumetteria.util.LoginInterceptor;

@ComponentScan("com.progetto.fumetteria")
@Configuration
@EnableWebMvc
@ImportResource({ "classpath:hibernate.xml", "classpath:mail.xml" })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/**");
		registry.addResourceHandler("/bootstrap/**").addResourceLocations("/bootstrap/**");
		registry.addResourceHandler("/files/**").addResourceLocations("/files/**");
		registry.addResourceHandler("/datepicker/**").addResourceLocations("/datepicker/**");
	}

	@Bean(name = { "messageSource" })
	public ResourceBundleMessageSource setupMessageResourceBundle() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("ValidationMessages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public ViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setCache(false);
		resolver.setContentType("text/html;charset=UTF-8");
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor());
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB
		return multipartResolver;
	}
}
