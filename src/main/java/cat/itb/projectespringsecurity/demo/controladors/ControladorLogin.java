package cat.itb.projectespringsecurity.demo.controladors;

import cat.itb.projectespringsecurity.demo.model.entitats.Usuari;
import cat.itb.projectespringsecurity.demo.model.serveis.ServeiUsuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ControladorLogin {
    @Autowired
    private ServeiUsuari serveiUsuari;


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping("/registre")
    public String registre(Model model) {
        model.addAttribute("userForm", new Usuari());
        return "registre";
    }

    @PostMapping("/registre")
    public String submitUser(@ModelAttribute("userForm") Usuari u) {
        serveiUsuari.add(u);
        return "redirect:/login";
    }
}
