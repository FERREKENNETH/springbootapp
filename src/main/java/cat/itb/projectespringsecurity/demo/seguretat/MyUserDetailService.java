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

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private ServeiUsuari serveiUsuari;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuari u = serveiUsuari.getUserByUsername(username);
        User.UserBuilder builder ;
        if (u != null) {
            builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(u.getPassword());
           //builder.authorities(new SimpleGrantedAuthority("ADMIN"));
            builder.roles(u.getRol());
        } else {
            throw new UsernameNotFoundException("Usuari no trobat");
        }
        return builder.build();
    }




}
