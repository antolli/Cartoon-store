/*

* Initializer : impostazione iniziale della sezione e contesto dell'applicazione
*

* version 1.0

*

* 29/07/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.LongConverter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		FilterRegistration.Dynamic openSessionInView = container.addFilter("openSessionInViewFilter",
				new OpenSessionInViewFilter());
		openSessionInView.addMappingForUrlPatterns(null, false, "/*");

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		container.addListener(new ContextLoaderListener(context));
		container.setInitParameter("defaultHtmlEscape", "false");

		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);

		ConvertUtils.register(new LongConverter(null), Long.class);
	}

}
