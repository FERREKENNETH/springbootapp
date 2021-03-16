package cat.itb.projectespringsecurity.demo.model.serveis;

import cat.itb.projectespringsecurity.demo.model.entitats.Empleat;
import cat.itb.projectespringsecurity.demo.model.entitats.Usuari;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServeiUsuari {

    private List<Usuari> repositoriArrayList = new ArrayList<>();

    public Usuari consultaPerId(String username) {
        Usuari user = null;
        for (Usuari u: repositoriArrayList){
            if (u.getUsername() == username) {
                return u;
                //TODO: CREAR LA ENTIDAD User, que despues spring boot la utilizara
                //https://docs.google.com/presentation/d/1hH8jbsmHENEYdZhS406nUSc4DXSaTOOvNM4Y1HtSYaQ/edit#slide=id.gc421573388_0_54
            }
        }
        return user;

    }

}
