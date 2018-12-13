package app.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"app"})
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

	/*@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/info/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
				.allowedHeaders("X-Auth-Token", "Content-Type")
				.exposedHeaders("token")
				.allowCredentials(false)
				.maxAge(4800);
	}*/

}
