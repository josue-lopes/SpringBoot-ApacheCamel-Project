package org.apache.camel;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootCamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCamelApplication.class, args);
	}
	
	@Bean
	ServletRegistrationBean servletRegistrationBean()
	{
		ServletRegistrationBean servlet = 
				new ServletRegistrationBean(new CamelHttpTransportServlet(), "/data/*");
		servlet.setName("CamelServlet");
		
		return servlet;
	}
}
