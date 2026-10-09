import exceptions.MontantInvallideException;
import exceptions.NoExistTypeCompte;
import exceptions.SoldeInsuffisantException;

import java.util.Scanner;

public class ServiceBancaire {
    public ServiceBancaire() {}
    Banque banque = new Banque();

    public Client creerClient(String nom,String prenom, String email){
        Client client = new Client(prenom,nom,email);
        banque.ajouterClient(client);
        return client;
    }
    public CompteBancaire ouvrirCompte(Client client, TypeCompte typeCompte, double taux) throws NoExistTypeCompte {
        //creation du compte bancaire
        CompteBancaire compteBancaire = switch (typeCompte) {
            case TypeCompte.COURANT -> new CompteCourant(client, 500);
            case TypeCompte.EPARGNE -> new CompteEpargne(client, taux);
            default -> throw new NoExistTypeCompte("ce type de compte n'existe pas");
        };
        banque.ajouterCompte(compteBancaire);
        client.ajouterCompte(compteBancaire);
        System.out.println("Votre compte a été crée avec succès : "+ compteBancaire.getNumero());
        return compteBancaire;
    }

    public void fermerCompte(CompteBancaire compteBancaire){
        compteBancaire.setActive(false);
    }

    public void effectuerDepot(CompteBancaire compte, double montant){
        try {
            compte.deposer(montant);
            compte.addHistoriqueCompte(TypeOperation.DEPOT,montant);
        } catch (MontantInvallideException e) {
            System.out.println(e.getMessage());
        }
    }

    public void effectuerRetrait(CompteBancaire compte, double montant){
        try {
            compte.retirer(montant);
            compte.addHistoriqueCompte(TypeOperation.RETRAIT,montant);
        } catch (MontantInvallideException | SoldeInsuffisantException e) {
            System.out.println(e.getMessage());
        }
    }

    public void appliquerInterets(CompteEpargne compte){
        compte.appliquerInterets();
    }

    public void transferer(CompteBancaire source, CompteBancaire destination, double montant) throws SoldeInsuffisantException, MontantInvallideException {
        if (source.equals(destination)){
            throw new RuntimeException("Vous ne pouvez pas transferer de l'argent de votre compte vers votre compte");
        }
        else{
            if(montant<=0){
                throw new MontantInvallideException("ce montant n'est pas transferable");
            }
            else if(source.getSolde()<montant) throw new SoldeInsuffisantException("Solde Insuffisant",new IllegalArgumentException());
            else {
                try{
                    source.retirer(montant);
                    source.addHistoriqueCompte(TypeOperation.VIREMENT_SORTANT,montant);
                    destination.deposer(montant);
                    destination.addHistoriqueCompte(TypeOperation.VIREMENT_ENTRANT,montant);

                } catch (MontantInvallideException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
