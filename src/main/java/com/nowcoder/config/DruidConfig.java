package com.nowcoder.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by CycloneBoy on 2017/7/18.
 */
@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(),"/druid/*");
        // IP 白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        // IP黑名单
        servletRegistrationBean.addInitParameter("deny","192.168.1.100");
        // 控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername","root");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.png,*.jpg,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
