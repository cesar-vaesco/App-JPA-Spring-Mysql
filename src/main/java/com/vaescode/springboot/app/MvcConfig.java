package com.vaescode.springboot.app;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/*
	 * private static final Logger log = LoggerFactory.getLogger(MvcConfig.class);
	 * 
	 * 
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * 
	 * WebMvcConfigurer.super.addResourceHandlers(registry);
	 * 
	 * String resourcePath =
	 * Paths.get("uploads").toAbsolutePath().toUri().toString();
	 * 
	 * log.info("resourcePath: " + resourcePath);
	 * 
	 * registry.addResourceHandler("/uploads/**")
	 * .addResourceLocations(resourcePath); }
	 */

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error_403");
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Donde guardar el parametro de nuestro locale/lenguaje Guardar en sesion el
	// lenguaje
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("es", "ES"));

		return localeResolver;
	}

	// Interceptor para cambiar el locale para modificar el idioma de la app añ ásar
	// el parametro por url
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localInterceptor = new LocaleChangeInterceptor();
		localInterceptor.setParamName("lang");
		return localInterceptor;

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	// Método que se encarga de serializar
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(new Class[] { com.vaescode.springboot.app.view.xml.ClienteList.class });
		return marshaller;
	}

}
