import exceptions.MontantInvallideException;
import exceptions.NoExistTypeCompte;
import exceptions.SoldeInsuffisantException;

import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws MontantInvallideException {
        ServiceBancaire service = new ServiceBancaire();
        Client alice = service.creerClient("Dupont", "Alice","alice@dupont.fr");
        Client bob = service.creerClient("Bob", "Martin","martin@bob.fr");

        try {
            CompteBancaire compte1 = service.ouvrirCompte(alice,TypeCompte.COURANT,0);
            CompteBancaire compte2 = service.ouvrirCompte(bob,TypeCompte.COURANT,0);
            CompteBancaire compte3 = service.ouvrirCompte(alice,TypeCompte.EPARGNE,5);
            service.effectuerDepot(compte1,2000);
            service.effectuerRetrait(compte1,300);
            service.effectuerDepot(compte3, 2000);
            service.effectuerRetrait(compte3, 300);
            try {
                service.transferer(compte1, compte2, 500);
            } catch (SoldeInsuffisantException e) {
                System.out.println(e.getMessage());
            }

            compte1.afficherHisto();
            compte2.afficherHisto();
            compte3.afficherHisto();
        } catch (NoExistTypeCompte e) {
            System.out.println(e.getMessage());
        }


    }
}