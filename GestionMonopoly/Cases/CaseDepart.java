package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

public class CaseDepart extends Case {
	
	public CaseDepart(String nom, int id) {
        super(nom,id);
    }
	
	public void action(JoueurMonopoly joueur, Plateau plateau) {
		joueur.crediter(200);		
	}

}
