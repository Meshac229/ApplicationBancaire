import exceptions.MontantInvallideException;
import exceptions.SoldeInsuffisantException;

import java.time.LocalDate;

public class CompteCourant extends CompteBancaire{
    private final double decouvertAutorise;
    public CompteCourant(Client proprietaire,double decouvertAutorise) {
        super(proprietaire);
        this.decouvertAutorise = decouvertAutorise;
    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    @Override
    public void retirer(double montant)
            throws MontantInvallideException, SoldeInsuffisantException {

        verifierMontant(montant);

        if (getSolde() - montant < -decouvertAutorise) {
            throw new SoldeInsuffisantException("Solde insuffissant");
        }

        setSolde(getSolde() - montant);
    }

}
