package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe {@code CasePrison} représente la case "Prison" dans le jeu de Monopoly.
 * Lorsqu'un joueur atterrit sur cette case, il peut être mis en prison et doit suivre les règles associées à la prison.
 */
public class CasePrison extends Case {

    private int amende;

    /**
     * Constructeur pour créer une instance de la classe {@code CasePrison}.
     * @param nom le nom de la case
     * @param id l'identifiant de la case
     * @param amende le montant de l'amende pour sortir de prison
     */
    public CasePrison(String nom, int id, int amende) {
        super(nom, id);
        this.amende = amende;
    }

    /**
     * Méthode pour envoyer un joueur en prison.
     * @param joueur le joueur à envoyer en prison
     */
    public void entrerEnPrison(JoueurMonopoly joueur) {
        joueur.setEstEnPrison(true);
        joueur.resetToursEnPrison();
        System.out.println(joueur.getNom() + " est maintenant en prison pour les 3 prochains tours.");
        joueur.setPosition(Plateau.ID_CASE_PRISON);
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        joueur.addToursEnPrison();
        System.out.print("Tour " + joueur.getToursPrison());

        if (joueur.getToursPrison() == 3) {
            sortirJoueurPrison(joueur, plateau);
        }

        System.out.println("Option disponible :");
        System.out.println("1. Lancer un double");
        System.out.println("2. Payer 50M$ pour s'échapper");

        int choix = 1;
        System.out.println("Choix par défaut : Lancer un double");

        switch (choix) {
            case 1:
                System.out.println("Vous lancez les dés ...");
                plateau.getDes().lancer();
                System.out.println("Résultat du lancer : " + plateau.getDes().getResultat());
                if (plateau.getDes().estDouble()) {
                    System.out.println(joueur.getNom() + " a fait un double !");
                    sortirJoueurPrison(joueur, plateau);
                } else {
                    System.out.println(joueur.getNom() + " n'a pas fait de double !");
                }
                break;

            case 2:
                System.out.println(joueur.getNom() + " paye l'amende pour s'échapper de la prison.");
                joueur.debiter(amende);
                sortirJoueurPrison(joueur, plateau);
                break;

            default:
                System.out.println("Option invalide !");
        }
    }

    private void sortirJoueurPrison(JoueurMonopoly joueur, Plateau plateau) {
        System.out.println(joueur.getNom() + " sort de prison.");
        joueur.setEstEnPrison(false);
        joueur.resetToursEnPrison();
        plateau.deplacerJoueur(joueur, Plateau.ID_CASE_VISITE_SIMPLE);
    }
}
