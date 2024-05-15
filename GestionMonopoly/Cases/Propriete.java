package GestionMonopoly.Cases;

import java.awt.Color;
import java.util.ArrayList;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe Propriete représente une case porpriété du Monopoly qui peut être achetée et possédée par un joueur.
 * Elle hérite de la classe Case.
 */
public class Propriete extends Case {
    
    private JoueurMonopoly proprietaire = null; // Le joueur qui possède la propriété
    private GroupeProprietes groupe; // La groupe auquel la propriété appartient
    private int valeurAchat; // Le prix d'achat de la propriété
    private int[] loyer; // Les loyers en fonction du nombre de maisons sur la propriété
    private int prixMaison; // Le prix d'une maison sur la propriété
    private int nbMaison = 0; // Le nombre de maisons actuellement sur la propriété

    /**
     * Constructeur de la classe Propriete.
     * @param nom Le nom de la propriété.
     * @param valeurAchat Le prix d'achat de la propriété.
     * @param loyer Les loyers en fonction du nombre de maisons sur la propriété.
     * @param groupe Le groupe de la propriété.
     * @param prixMaison Le prix d'une maison sur la propriété.
     * @param id L'identifiant de la propriété sur le plateau.
     */
    public Propriete(String nom, int id, GroupeProprietes groupe, int valeurAchat, int[] loyer, int prixMaison) {
        super(nom, id);
        assert (valeurAchat > 0 && groupe != null && prixMaison > 0);
        this.valeurAchat = valeurAchat;
        this.loyer = loyer;
        this.groupe = groupe;
        groupe.addPropriete(this);
        this.prixMaison = prixMaison;
    }

    /**
     * Obtenir le prix d'une maison sur la propriété.
     * @return Le prix d'une maison sur la propriété.
     */
    public int getPrixMaison() {
        return this.prixMaison;
    }

    /**
     * Obtenir la groupe de la propriété.
     * @return La groupe de la propriété.
     */
    public GroupeProprietes getGroupe() {
        return this.groupe;
    }

	/**
     * Obtenir la couleur de la propriété.
     * @return La couleur de la propriété.
     */
    public Color getCouleur() {
        return this.groupe.getCouleur();
    }

    /**
     * Obtenir le nombre de maisons sur la propriété.
     * @return Le nombre de maisons sur la propriété.
     */
    public int getNbMaison() {
        return this.nbMaison;
    }

    /**
     * Obtenir le prix d'achat de la propriété.
     * @return Le prix d'achat de la propriété.
     */
    public int getValeurAchat() {
        return this.valeurAchat;
    }

    /**
     * Obtenir le propriétaire de la propriété.
     * @return Le propriétaire de la propriété.
     */
    public JoueurMonopoly getProprietaire() {
        return this.proprietaire;
    }

    /**
     * Définir le propriétaire de la propriété.
     * @param proprietaire Le joueur qui devient propriétaire de la propriété.
     */
    public void setProprietaire(JoueurMonopoly proprietaire) {
        this.proprietaire = proprietaire;
    }

	/**
     * Enleve le propriétaire de la propriété.
     */
    public void removeProprietaire() {
        this.proprietaire = null;
    }

    /**
     * Vérifier si la propriété est achetable.
     * @return true si la propriété est achetable (sans propriétaire), false sinon.
     */
    public boolean estAchetable() {
        return (this.proprietaire == null);
    }

    /**
     * Ajouter une maison sur la propriété si possible.
     * @return true si la maison a été ajoutée avec succès, false sinon.
     */
    public boolean addMaison() {
        if (this.nbMaison < 5) {
            this.nbMaison++;
            return true;
        }
        return false;
    }

    /**
     * Vendre une maison de la propriété si possible.
     * @return true si la maison a été vendue avec succès, false sinon.
     */
    public boolean vendreMaison() {
        if (this.nbMaison > 0) {
            this.nbMaison--;
            return true;
        }
        return false;
    }

    /**
     * Obtenir le loyer actuel de la propriété en fonction du nombre de maisons.
     * @return Le loyer actuel de la propriété.
     */
    public int getLoyer() {
        return this.loyer[nbMaison];
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        // TODO : Ecrire l'action associé la propriété
        System.out.println("Le joueur est tombé sur la propiété : " + this.getNom());
    }

	// |||||||||||||||||||| Annexe ||||||||||||||||||||||||||

    public boolean peutMettreMaison() {
        return (this.nbMaison < 5);
    }
}
