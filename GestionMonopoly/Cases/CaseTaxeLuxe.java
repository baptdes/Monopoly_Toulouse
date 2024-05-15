package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

public class CaseTaxeLuxe extends Case {
	
    private int montanttaxe;
    
    /**
     * Constructeur pour initialiser le nom et le montant de l'impôt pour la case.
     * @param nom String  le nom de la case
     * @param montantImpot int  le montant à payer pour l'impôt
     */
    public CaseTaxeLuxe(String nom, int id , int montantImpot) {
    	super(nom, id);
    	assert(montantImpot >= 0);
        this.montanttaxe = montantImpot;
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        System.out.println(joueur.getNom() + " paie " + this.montanttaxe + "€ de taxe de luxe.");
        joueur.debiter(this.montanttaxe);
    }
}