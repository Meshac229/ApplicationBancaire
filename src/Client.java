import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;

    private String nom;

    private String prenom;

    private String email;

    private List<CompteBancaire> comptes = new ArrayList<>();

    public Client(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.id = (int)(Math.random()*100);
        this.nom = nom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CompteBancaire> getComptes() {
        return comptes;
    }

    public void ajouterCompte(CompteBancaire compte) {
        this.comptes.add(compte);
    }
}
