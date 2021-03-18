package cat.itb.projectespringsecurity.demo.seguretat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class ConfiguracioSeguretatWeb extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUserDetailService userDetailsService() {
        return new MyUserDetailService();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //autenticacio
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("kenneth")
                .password(passwordEncoder().encode("kenneth"))
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("b").password(passwordEncoder()
                .encode("b"))
                .roles("USER");


        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //autoritzacio
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/login",
                        "/registre",
                        "/empleats/list/**",
                        "/empleats/edit/submit").permitAll()

                .antMatchers(
                        "/empleats/new",
                        "/empleats/new/submit",
                        "/empleats/edit/**",
                        "/empleats/eliminar",
                        "/empleats/eliminar/{id}").hasRole("ADMIN")


                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().permitAll();
    }
}