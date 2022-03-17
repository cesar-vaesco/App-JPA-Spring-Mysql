package com.vaescode.springboot.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
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

}
