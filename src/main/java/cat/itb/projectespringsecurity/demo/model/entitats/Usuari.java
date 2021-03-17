package cat.itb.projectespringsecurity.demo.model.entitats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuari {
    private String username;
    private String password;
    private String rol;

    public Usuari(String username, String password) {
        this.username = username;
        this.password = password;
        this.rol = "USER"; //ADMIN O USER
    }
}
