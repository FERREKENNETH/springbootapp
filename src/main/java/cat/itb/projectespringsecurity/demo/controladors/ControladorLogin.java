package cat.itb.projectespringsecurity.demo.controladors;

import cat.itb.projectespringsecurity.demo.model.entitats.Empleat;
import cat.itb.projectespringsecurity.demo.model.entitats.Usuari;
import cat.itb.projectespringsecurity.demo.model.serveis.ServeiUsuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorLogin {
    @Autowired
    private ServeiUsuari serveiUsuari;


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/registre")
    public String registre(Model model) {
        model.addAttribute("userForm", new Usuari());
        //model.addAttribute("repeatpassword", new String());
        return "registre";
    }

    @PostMapping("registre/submit")
    // empleatForm es el nombre del objeto que se recoje en el formulario  de afegir(.html)
    //recoje el objeto Empleat por parametro, lo añade al array y redirije a list
    public String submitUser(@ModelAttribute("userForm") Usuari u) {
        serveiUsuari.add(u);
        return "redirect:/empleats/list"; //TODO: ver a donde redirigir
    }
}
