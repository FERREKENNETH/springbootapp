package cat.itb.projectespringsecurity.demo.model.serveis;

import cat.itb.projectespringsecurity.demo.model.entitats.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServeiUsuari {

    @Autowired// no faria falta
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private List<Usuari> repositoriArrayList = new ArrayList<>();

    public Usuari consultaPerId(String username) {
        Usuari user = null;
        for (Usuari u: repositoriArrayList){
            if (u.getUsername() == username) {
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

}
