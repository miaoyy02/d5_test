package com.myy.d5_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ClassName: SecurityConfig
 * Package: com.example.demo_d5.config
 * Description:
 *
 * @Author: miaoyy
 * @Create: 2024-08-11 - 2:32
 */
//替换掉Spring Security 默认配置
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 定义密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 定义内存中的用户详情管理器
    @Bean
    public UserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("test")
            .passwordEncoder(encoder::encode)
            .password("123456") // 密码使用编码器进行编码
            .roles("USER").build();
        //此处可以多角色管理
//        UserDetails admin = User.withUsername("test")
//            .passwordEncoder(encoder::encode)
//            .password("123456") // 密码使用编码器进行编码
//            .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user);
    }

    // 定义SecurityFilterChain Bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            // 使用Lambda表达式配置授权规则
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/").permitAll() // 允许所有用户访问根路径
                .anyRequest().authenticated() // 其他请求需要认证
            )

            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
