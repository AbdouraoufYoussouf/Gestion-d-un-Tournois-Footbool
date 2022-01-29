package doc.raf.secuirity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
     * @Autowired javax.sql.DataSource dataSource;
     */
    @Autowired
    MyUserDetailsService myuserdetailsservice;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myuserdetailsservice);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/userLogin", "/userSave", "resources/**", "/Css/**").permitAll()
            .antMatchers("/userRegister", "/userSave", "resources/**", "/Css/**").permitAll()
            .antMatchers("/add**/**", "/register**/**", "/edit**/**", "/delete**/**").hasRole("ADMIN")
            .antMatchers("/userLogin").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/userLogin")
               
                .and()
                .oauth2Login()
                .loginPage("/userLogin")
            .defaultSuccessUrl("/success")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/userLogin").permitAll();
    } 
}
