package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

public class CaseVisiteSimple extends Case {
	

    
    /**
     * Constructeur pour initialiser le nom et l'id de la case
     * @param nom String  le nom de la case
     * @param id int de la case
     */
    public CaseVisiteSimple(String nom, int id) {
    	super(nom, id);

    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        
    }
}
