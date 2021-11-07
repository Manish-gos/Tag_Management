package com.ub.tag.TagManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class TagManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagManagementApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
}
