package cat.itb.projectespringsecurity.demo.controladors;

import cat.itb.projectespringsecurity.demo.model.serveis.ServeiEmpleat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorEmpleats {
    @Autowired
    private ServeiEmpleat serveiEmpleat;

    @GetMapping("/empleats/list")
    public String llistar(Model model) {
        model.addAttribute("llistaEmpleats", serveiEmpleat.getEmpleats());
        return "llistat"; //listat.html recojera los datos del Model model
    }
}
