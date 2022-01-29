package doc.raf.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Entity
@Data
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotEmpty(message = "Le champ Nom est vide!")
    private String nom;
    @NotEmpty(message = "Le champ Prenom est vide!")
    private String prenom;
    @NotEmpty(message = "Le champ Email est vide!")
    private String username;
    @NotEmpty(message = "Le champ Password est vide!")
    private String password;
    private String roles="";
    private int active;

    public List<String> getListRoles(){
        List<String> listRoles = new ArrayList<>();
        listRoles = Arrays.asList(this.roles.split(","));
        return listRoles;
    } 
    public User() {
    }
    public User(Long idUser, @NotEmpty(message = "Le champ Nom est vide!") String nom,
            @NotEmpty(message = "Le champ Prenom est vide!") String prenom,
            @Email(message = "Le champ Email est vide!") String username,
            @NotEmpty(message = "Le champ Password est vide!") String password, String roles, int active) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }
   

    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }
    
}
