import exceptions.CompteInexistantException;

import java.util.ArrayList;
import java.util.List;

public class Banque {
    private List<Client> clients;

    private List<CompteBancaire> comptes;

    public Banque() {
        this.clients = new ArrayList<>();
        this.comptes = new ArrayList<>();
    }

    public void ajouterClient(Client client) {
        this.clients.add(client);
    }

    public void ajouterCompte(CompteBancaire compte) {
        this.comptes.add(compte);
    }

    public Client rechercherClient(int id) {
        Client clientRecherche = null;
        for (Client client : clients) {
            if (client.getId() == id) {
                clientRecherche = client;
                break;
            }
        }
        return clientRecherche;
    }

    public List<Client> rechercherClient(String nom) {
        List<Client> clientsRecherches = new ArrayList<>();
        for (Client client : clients) {
            if (client.getNom().equals(nom)) {
                clientsRecherches.add(client);
            }
        }
        return clientsRecherches;
    }

    public CompteBancaire rechercherCompte(String numero) throws CompteInexistantException {

        for (CompteBancaire compte : comptes) {
            if (compte.getNumero().equals(numero)) {
                return compte;
            }
        }
        throw new CompteInexistantException("Le compte "+ numero + "n'existe pas");
    }

    public List<CompteBancaire> rechercherCompte(Client client) {
        List<CompteBancaire> compteBancaireList = new ArrayList<>();

        for (CompteBancaire compteBancaire : comptes) {
            if (compteBancaire.getProprietaire().equals(client)) {
                compteBancaireList.add(compteBancaire);
            }
        }
        return compteBancaireList;
    }

    public List<CompteBancaire> compteWithSoldeMin(double solde) {
        return comptes.stream().filter(compteBancaire -> compteBancaire.getSolde() > solde).toList();
    }

    public List<CompteBancaire> compteWithOperationMin(int nbOperation) {
        return comptes.stream().filter(compteBancaire -> compteBancaire.getHistoriques().size() > nbOperation).toList();
    }

    public CompteBancaire topCompte(){
        CompteBancaire topCompte = comptes.getFirst();

        for (CompteBancaire compte : comptes){
            if(compte.getSolde()>topCompte.getSolde()){
                topCompte = compte;
            }
        }
        return  topCompte;
    }

    public void supprimerClient(Client client) {
        clients.remove(client);
    }

    public void afficherTousLesClients() {
        for (Client client : clients) {
            System.out.println(client.getNom() + " " + client.getPrenom());
        }
    }

    public void afficherTousLesComptes() {
        for (CompteBancaire compte : comptes) {
            System.out.println(compte.getNumero() + " " + compte.getProprietaire().getNom() + " " + compte.getProprietaire().getPrenom());
        }
    }
}
