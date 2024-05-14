package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

public class CaseImpots extends Case {
	
    private int montantImpot;
    
    /**
     * Constructeur pour initialiser le nom et le montant de l'impôt pour la case.
     * @param nom String  le nom de la case
     * @param montantImpot int  le montant à payer pour l'impôt
     */
    public CaseImpots(String nom, int id , int montantImpot) {
    	super(nom, id);
    	assert(montantImpot >= 0);
        this.montantImpot = montantImpot;
    }

    /**
     * Méthode pour exécuter l'action de la case impôt, 
     * retirant de l'argent du joueur et l'ajoutant au parc gratuit.
     * @param joueur JoueurMonopoly  le joueur qui doit payer l'impôt
     */
    public void action(JoueurMonopoly joueur, Plateau plateau) {
        System.out.println(joueur.getNom() + " paie " + this.montantImpot + "€ d'impôts.");
        joueur.debiter(this.montantImpot);
    }
}
