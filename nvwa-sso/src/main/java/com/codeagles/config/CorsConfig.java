package com.codeagles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created with IntelliJ IDEA.
 * User: Codeagles
 * Date: 2020/1/6
 * Time: 10:49 PM
 * <p>
 * Description:
 */
@Configuration
public class CorsConfig {
    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        //1.添加cors配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("http://www.mtv.com");
        configuration.addAllowedOrigin("http://www.mtv.com:8080");
        configuration.addAllowedOrigin("http://www.music.com");
        configuration.addAllowedOrigin("http://www.music.com:8080");

        //设置是否发送cookie信息
        configuration.setAllowCredentials(true);
        //设置允许请求的方式
        configuration.addAllowedMethod("*");
        //设置允许的header
        configuration.addAllowedHeader("*");
        //2. 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", configuration);
        //3. 返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
