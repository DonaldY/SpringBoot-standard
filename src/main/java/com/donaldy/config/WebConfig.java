package com.donaldy.config;

import com.donaldy.filter.TimeFilter;
import com.donaldy.interceptor.TimeInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 可加入第三方过滤器
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(timeInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }*/

    @Bean
    public TimeInterceptor timeInterceptor() {
        return new TimeInterceptor();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    @Bean
    public Validator validator(){
        // failFast(true)启动快速失败
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).
                configure().
                failFast(true).
                buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
