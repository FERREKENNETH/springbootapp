package cat.itb.projectespringsecurity.demo.seguretat;

import cat.itb.projectespringsecurity.demo.model.entitats.Usuari;
import cat.itb.projectespringsecurity.demo.model.serveis.ServeiUsuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//per fer autenticacio (con un servicio)

//se utiliza en Canfiguracio extends WebSecurityConfigurerAdapter!!
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private ServeiUsuari serveiUsuari;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuari u = serveiUsuari.consultaPerId(username);
        User.UserBuilder builder = null;
        if (u != null) {
            builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(u.getPassword());
            //builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
            builder.roles(u.getRol());//+ o - lo mismo que builder.authorities
        } else {
            throw new UsernameNotFoundException("Usuari no trobat");
        }
        return builder.build();
    }

}
