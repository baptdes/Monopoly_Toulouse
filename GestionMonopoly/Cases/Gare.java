package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe CaseGare représente une case de type gare sur le plateau du Monopoly.
 * Elle hérite de la classe Case.
 */
public class Gare extends Case {
    
    private JoueurMonopoly proprietaire; // Le joueur qui possède la gare
    private int valeurAchat; // Le prix d'achat de la gare
    private int[] loyer; // Les loyers en fonction du nombre de gares possédées
    
    /**
     * Constructeur de la classe CaseGare.
     * @param nom Le nom de la gare.
     * @param id L'identifiant de la gare sur le plateau.
     * @param valeurAchat Le prix d'achat de la gare.
     * @param loyer Les loyers en fonction du nombre de gares possédées.
     */
    public Gare(String nom, int id, int valeurAchat, int[] loyer) {
        super(nom, id);
        assert (valeurAchat > 0 && id > 0);
        this.valeurAchat = valeurAchat;
        this.loyer = loyer;
    }
    

    /**
     * Obtenir le prix d'achat de la gare.
     * @return Le prix d'achat de la gare.
     */
    public int getValeurAchat() {
        return this.valeurAchat;
    }
    
    /**
     * Obtenir le propriétaire de la gare.
     * @return Le joueur qui possède la gare.
     */
    public JoueurMonopoly getProprietaire() {
        return this.proprietaire;
    }
    
    /**
     * Vérifier si la gare est achetable.
     * @return true si la gare est achetable (sans propriétaire), false sinon.
     */
    public boolean estAchetable() {
        return (this.proprietaire == null);
    }

    /**
     * Obtenir le loyer actuel de la gare en fonction du nombre de gares possédées par le propriétaire.
     * @return Le loyer actuel de la gare.
     */
    public int getLoyer() {
        if (proprietaire != null)
            return this.loyer[proprietaire.getNbGares()];
        return 0; // Retourne 0 si la gare n'a pas de propriétaire
    }

    /**
     * Définir le propriétaire de la gare.
     * @param proprietaire Le joueur qui devient propriétaire de la gare.
     */
    public void setProprietaire(JoueurMonopoly proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * Retirer le propriétaire de la gare, la rendant disponible à l'achat.
     */
    public void removeProprietaire() {
        this.proprietaire = null;
    }

    /**
     * Méthode héritée de la classe Case mais non implémentée pour une gare.
     * La méthode est destinée à être définie dans les sous-classes selon le comportement souhaité.
     */
    public void action(JoueurMonopoly joueur, Plateau plateau) {
		// TODO : Ecrire l'action associé à la gare
    }
}
