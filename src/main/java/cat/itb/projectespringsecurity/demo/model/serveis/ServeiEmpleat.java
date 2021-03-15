package cat.itb.projectespringsecurity.demo.model.serveis;

import cat.itb.projectespringsecurity.demo.model.entitats.Empleat;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ServeiEmpleat {

    private List<Empleat> repositoriArrayList = new ArrayList<>();

    public void addEmployee(Empleat e) { repositoriArrayList.add(e); }

    public void deteleEmployee (int id) { repositoriArrayList.remove(getEmpleatById(id)); }

    public void editarEmployee(Empleat e) {
        Integer position = getPositionById(e.getId());
        if (position != null)
            repositoriArrayList.set(position, e);
        else
            System.out.println("Error, no s'ha trobar l'empleat amb id=" + e.getId());
    }

    public Integer getPositionById(int id) {
        int i = 0;
        for (Empleat e: repositoriArrayList) {
            if (e.getId() == id) return i;
            i++;
        }
        return null;
    }

    public Empleat getEmpleatById(int id) {
        Empleat emp = null;
        for (Empleat e: repositoriArrayList){
            if (e.getId() == id) {
                emp = e;
                break;
            }
        }
        return  emp;
    }

    public List<Empleat> getEmpleats() { return repositoriArrayList; }

    public List<Empleat> listOrderPerNom() {
        Collections.sort(repositoriArrayList, new Comparator<Empleat>() {
            @Override
            public int compare(Empleat o1, Empleat o2) {
                return o1.getNom().compareToIgnoreCase(o2.getNom());
            }
        });

        return  repositoriArrayList;

    }

    @PostConstruct
    public void init() {
        repositoriArrayList.addAll(
                Arrays.asList(
                        new Empleat(1, "Kenneth", "kenneth@kenneth.com", "670809878", true),
                        new Empleat(2, "Pedro", "pedro@pedro.com", "698784512", false),
                        new Empleat(3, "Juan", "juan@juan.com", "658987865", false),
                        new Empleat(4, "Messi", "messi@messi.com", "698784512", true)
                )
        );
    }

}
