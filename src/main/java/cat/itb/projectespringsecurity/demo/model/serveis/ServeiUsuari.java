package cat.itb.projectespringsecurity.demo.model.serveis;

import cat.itb.projectespringsecurity.demo.model.entitats.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ServeiUsuari implements UserDetails {

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private List<Usuari> repositoriArrayList = new ArrayList<>();

    public Usuari consultaPerId(String username) {
        Usuari user = null;
        for (Usuari u: repositoriArrayList){
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return user;
    }

    //antes de añadir el usuario, cifrar el password
    public void add(Usuari u) {
        u.setPassword(passwordEncoder().encode(u.getPassword()));
        repositoriArrayList.add(u);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
