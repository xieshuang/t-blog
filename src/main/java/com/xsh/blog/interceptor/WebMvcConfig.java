package com.xsh.blog.interceptor;


import com.xsh.blog.utils.TaleUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Resource;

/**
 * 向mvc中添加自定义组件
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private BaseInterceptor baseInterceptor;
    @Resource
    private RateLimitInterceptor rateLimitInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
        registry.addInterceptor(rateLimitInterceptor);
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + TaleUtils.getUplodFilePath() + "upload/");
    }
}
