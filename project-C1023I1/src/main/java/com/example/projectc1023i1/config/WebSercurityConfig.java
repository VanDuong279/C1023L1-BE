package com.example.projectc1023i1.config;

import com.example.projectc1023i1.filter.JwtTokenFilter;
import com.example.projectc1023i1.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSercurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtTokenFilter jwtTokenFilter;
    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
    // thàng này chính là choost giao thông chặn cửa xem  thử đã đủ giấy tờ hay chưa

    /**
     *  cau hinh bao mat cho ung dung
     * @param http doi tuong HttpSecurity de bao mat cho cac yeu cua cho HTTP
     * @return tra ve cau hinh bao mat da tuy chinh
     * @throws Exception nem ra ngoai le neu xay ra loi trong qua trinh bao mat
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer:: disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request   -> {
                    request.requestMatchers(
                                     "**")

                            .permitAll()
                            .requestMatchers(POST,"/api/product/product/**").hasAnyRole(Roles.ADMIN,Roles.USER)

                            .requestMatchers(POST,"/api/orders/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(POST,"/api/users/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE,"/api/users/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(GET,"/api/users/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(PUT,"/api/users/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(POST,"/api/product/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(POST,"api/category/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE,"/api/product/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(PATCH,"/api/product/**").hasAnyRole(Roles.ADMIN)
                            .requestMatchers(GET,"/api/product/checkProductName").hasAnyRole(Roles.ADMIN)


                            .requestMatchers(POST,"/api/orders/**").hasAnyRole(Roles.ADMIN,Roles.USER)
                            .requestMatchers(DELETE,"/api/orders/**").hasAnyRole(Roles.ADMIN,Roles.USER)
                            .requestMatchers(GET,"/api/orders/**").hasAnyRole(Roles.ADMIN,Roles.USER)
                            .requestMatchers(PUT,"/api/orders/**").hasAnyRole(Roles.ADMIN,Roles.USER)
                            .requestMatchers(PATCH,"/api/orders/**").hasAnyRole(Roles.ADMIN,Roles.USER)
                            .requestMatchers(GET,"/api/feedbacks/**").hasAnyRole(Roles.ADMIN, Roles.USER)

                            .anyRequest().authenticated()
                    ;
                })
                .csrf(AbstractHttpConfigurer::disable);
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {// dùng để tùy chỉnh cấu hình Cors
            /**
             * cau hinh thiet lap CORS cho ung dung
             * @param httpSecurityCorsConfigurer doi tuong CorsConfigurer de cau hinh bao mat CORS cho ung dung
             */
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*")); // Cho phép tất cả các domain gửi yêu cầu
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // Cho phép các phương thức HTTP nhất định
                configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token")); // Thêm "token" vào danh sách các header được phép
                configuration.setExposedHeaders(List.of("x-auth-token")); // Liệt kê các header mà frontend có thể truy cập từ phản hồi
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                // tạo 1 nguồn cấu hình CORS dựa trên URl
                source.registerCorsConfiguration("/**", configuration);// dùng đ cấu hình CORS cho tất cả đường dẫn
                httpSecurityCorsConfigurer.configurationSource(source); // áp dụng cấu hình để bỏ vào httpSecurityCorsConfigurer
            }
        }) ;
            return http.build();
    }


}
