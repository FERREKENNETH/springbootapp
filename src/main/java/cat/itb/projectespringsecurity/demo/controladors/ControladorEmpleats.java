package cat.itb.projectespringsecurity.demo.controladors;

import cat.itb.projectespringsecurity.demo.model.entitats.Empleat;
import cat.itb.projectespringsecurity.demo.model.serveis.ServeiEmpleat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorEmpleats {
    @Autowired
    private ServeiEmpleat serveiEmpleat;

    @GetMapping("/empleats/list")
    public String llistar(Model model) {
        model.addAttribute("llistaEmpleats", serveiEmpleat.getEmpleats());
        return "llistat"; //listat.html recojera los datos del Model model
    }

    @GetMapping("/empleats/list/orderbyname")
    public String llistarOrderByNom(Model model) {
        model.addAttribute("llistaEmpleats", serveiEmpleat.listOrderPerNom());
        return "llistat"; //listat.html recojera los datos
    }

    //vista para añadir un usuario por formulario
    @GetMapping("/empleats/new")
    public String afegirEmpleat(Model model) {
        //le pasamos una instancia para tipar el formulario html
        model.addAttribute("empleatForm", new Empleat());
        return "afegir";
    }

    @PostMapping("empleats/new/submit")
    // empleatForm es el nombre del objeto que se recoje en el formulario  de afegir(.html)
    //recoje el objeto Empleat por parametro, lo añade al array y redirije a list
    public String submitEmpleat(@ModelAttribute("empleatForm") Empleat e) {
        serveiEmpleat.addEmployee(e);
        return "redirect:/empleats/list";
    }

    @GetMapping("/empleats/edit/{id}")
    public String editar(Model model,
                         @PathVariable(value="id") int id) {
        Empleat e = serveiEmpleat.getEmpleatById(id);
        model.addAttribute("empleatForm", e);
        return "afegir";
    }

    @PostMapping("empleats/edit/submit")
    // empleatForm es el nombre del objeto que se recoje en el formulario  de afegir(.html)
    public String submitEditEmpleat(@ModelAttribute("empleatForm") Empleat e) {
        //serveiEmpleats.editar(e);
        serveiEmpleat.editarEmployee(e);
        return "redirect:/empleats/list";
    }

    @GetMapping("/empleats/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") int id) {
        serveiEmpleat.deteleEmployee(id);
        return "redirect:/empleats/list";
    }







}
