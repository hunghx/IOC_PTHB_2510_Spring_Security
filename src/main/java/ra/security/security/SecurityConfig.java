package ra.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // bật cấu hình mặc định của security
public class SecurityConfig {
    // Bean thông tin người dùng
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("hunghx")
                .password("123456")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails); //lưu lại thông tin xác thực
    }

    // Bean cấu hình bộ lọc request

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                authorize -> authorize.anyRequest().permitAll() // đều dược phép truy cập
        ) // phân quyền cho các request
                .httpBasic(Customizer.withDefaults()) // mặc định theo username và password
                .formLogin(Customizer.withDefaults()); // form mặc định
        return http.build();
    }
}
