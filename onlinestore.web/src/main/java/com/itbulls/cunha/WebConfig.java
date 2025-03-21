package com.itbulls.cunha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.itbulls.cunha.security.DefaultAuthenticationFailureHandler;
import com.itbulls.cunha.security.DefaultAuthenticationProvider;
import com.itbulls.cunha.security.DefaultAuthenticationSuccessHandler;
import com.itbulls.cunha.security.DefaultUserDetailsService;

@EnableWebMvc
@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = { "com.itbulls.cunha" })
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**.css", "/fonts/**", "/images/**", "/js/**", "/vendor/**")
				.addResourceLocations("/css/", "/fonts/", "/images/", "/js/", "/vendor/");
	}

	@Bean
	public HandlerExceptionResolver errorHandler() {
		return new HandlerExceptionResolver() {
			@Override
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception ex) {
				ModelAndView model = new ModelAndView("404");
				ex.printStackTrace();
				model.addObject("exception", ex);
				model.addObject("handler", handler);
				return model;
			}
		};
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
	}

	// ===== Spring Security Configuration

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationSuccessHandler successAuthHandler() {
		return new DefaultAuthenticationSuccessHandler();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/orders-management", "/updateOrderStatus")
				.hasAnyRole(SetupDataLoader.WRITE_PRIVILEGE).requestMatchers("/admin/*").hasRole("ADMIN").anyRequest()
				.permitAll().and().formLogin().loginPage("/login").usernameParameter("email").usernameParameter("email")
				.loginProcessingUrl("/perform_login").successHandler(successAuthHandler())
				.failureHandler(failureHandler()).and().logout().logoutUrl("/signout").deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/homepage").and().rememberMe().key("supersecretkey").rememberMeParameter("remember")
				.rememberMeCookieName("rememberlogin");
		return http.build();
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new DefaultAuthenticationFailureHandler();
	}

	@Bean
	public UserDetailsService defaultUserDetailsService() {
		return new DefaultUserDetailsService();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		return new DefaultAuthenticationProvider();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authProvider());
		return authenticationManagerBuilder.build();
	}
}