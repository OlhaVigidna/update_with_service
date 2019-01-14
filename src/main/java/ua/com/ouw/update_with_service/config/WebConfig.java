package ua.com.ouw.update_with_service.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:mesages");
        return reloadableResourceBundleMessageSource;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource());
        return factoryBean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathToFolder = System.getProperty("user.home") + File.separator + "images" + File.separator;

        String s = "file:///" + pathToFolder;
        System.out.println(s);
        registry.addResourceHandler("/ava/**").addResourceLocations(s);

        String pathToFolder2 = System.getProperty("user.home") + File.separator + "staticx" + File.separator;
        registry.addResourceHandler("/css/**").addResourceLocations("file:///" + pathToFolder2);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/login").setViewName("loginPage");
    }


}
