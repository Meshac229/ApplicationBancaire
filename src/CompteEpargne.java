import java.time.LocalDate;

public class CompteEpargne extends CompteBancaire{
    public CompteEpargne(Client proprietaire, double taux){
        super(proprietaire);
        this.taux=taux;
    }

    double taux;

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public void appliquerInterets(){
        setSolde(getSolde()+getSolde()*getTaux()/100);
    }
}
