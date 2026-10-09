import exceptions.MontantInvallideException;
import exceptions.SoldeInsuffisantException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class CompteBancaire {
    private String numero;

    private double solde;

    private Client proprietaire;

    private LocalDate dateCreation;

    private List<Operation> historiques =new ArrayList<>();

    private boolean isActive;

    public CompteBancaire(Client proprietaire) {
        this.numero = UUID.randomUUID().toString();
        this.solde = 0;
        this.proprietaire = proprietaire;
        this.dateCreation = LocalDate.now();
        isActive = true;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    public double getSolde() {
        return solde;
    }

    protected void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setHistoriques(List<Operation> historiques) {
        this.historiques = historiques;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Operation> getHistoriques() {
        return historiques;
    }

    public void deposer(double montant) throws MontantInvallideException {
        if(montant<=0){
            throw new MontantInvallideException("Le montant n'est pas valide", new IllegalArgumentException());
        }else{
            setSolde(this.solde + montant);
        }

    }

    protected void verifierMontant(double montant)
            throws MontantInvallideException {

        if (montant <= 0) {
            throw new MontantInvallideException(
                    "Le montant doit être supérieur à 0"
            );
        }
    }

    public void retirer(double montant) throws MontantInvallideException, SoldeInsuffisantException {
        if(montant<=0){
            throw new MontantInvallideException("Le montant n'est pas valide", new IllegalArgumentException());
        }else {
            setSolde(this.solde - montant);
            //addHistoriqueCompte(TypeOperation.RETRAIT,montant);
        }
    }

    public void addHistoriqueCompte(TypeOperation tyOp, double montant){
        Operation operation = new Operation(tyOp,montant, LocalDate.now(),"");
        historiques.add(operation);
    }

    public void afficherHisto(){
        System.out.println("COMPTE"+ getProprietaire().getNom() + getNumero());
        for(Operation operation : getHistoriques()){
            System.out.println("["+operation.getDate()+"]" +operation.getType().toString()+"         "+operation.getMontant());
        }
    }
}
