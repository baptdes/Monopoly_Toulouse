package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe {@code CaseDepart} représente la case de départ dans le jeu de Monopoly.
 * Lorsqu'un joueur passe par cette case, il reçoit un montant d'argent.
 */
public class CaseDepart extends Case {

	private int montant;

    public CaseDepart(String nom, int id,int montant) {
        super(nom, id);
		this.montant = montant;
	}

    public void action(JoueurMonopoly joueur, Plateau plateau) {
		System.out.println(joueur.getNom() + " se voit crediter de " + this.montant);
        joueur.crediter(this.montant);
    }
}
