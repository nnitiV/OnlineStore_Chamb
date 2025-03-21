package com.itbulls.cunha;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class MainWebAppInitializer implements WebApplicationInitializer {
 
	@Override
	public void onStartup(final ServletContext sc) throws ServletException {
		var ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebConfig.class);
		sc.addListener(new ContextLoaderListener(ctx));
		
		ServletRegistration.Dynamic appServlet = sc.addServlet("mvc",
				new DispatcherServlet(new GenericWebApplicationContext()));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");
		appServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
		sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");
	}
}