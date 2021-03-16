package cat.itb.projectespringsecurity.demo.seguretat;

import cat.itb.projectespringsecurity.demo.model.serveis.ServeiUsuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//per fer autenticacio (con un servicio)

//se utiliza en Canfiguracio extends WebSecurityConfigurerAdapter!!
@Service
public class DetailsServiceCustom implements UserDetailsService {
    @Autowired
    private ServeiUsuari serveiUsuari;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


}
