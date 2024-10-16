package com.example.projectc1023i1.config;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SercurityConfig {
    @Autowired
    private IUserRepo userRepo;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * kiem tra xem tai khoan co ton tai tkhong
     * @return tra ve lop UserDetailsService neu khong nem ra ngoai le
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Users existingUser = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User" + username + " not found"));
            return existingUser; // phan nay giup tim xem tai khoan co ton tai hay khong neu co tra ve nguoc lai quang ra excetion
        };
    }

    /**
     * tim kiem mat khau va ma hoa mat khau sau do so sanh roi tra ve
     * @return tra ve doi tuong daoAuthenticationProvider da duoc cau hinh de xac thuc nguoi dung dua tren co so du lieu
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); // thang nay dc su dung userDetailsService de tim kiem mat khau va tra ve
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // dung de ma hoa mat khau roi so sanh voi mat khau trong database da duoc ma hoa
        return daoAuthenticationProvider;
    }

    /**
     *
     * @param config tra ve 1 lop AuthenticationConfiguration
     * @return tra ve  doi tuong AuthenticationConfiguration cau hinh tao ra AuthenticationManager dang nhap cai nay se tra ve authentication neu no duoc dang nhap thanh cong
     * @throws Exception nem ra ngoai le neu xay ra qua trinh xu ly du lieu bi loi
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}