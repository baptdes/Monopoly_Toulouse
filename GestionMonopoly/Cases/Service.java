package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe CaseService représente une case de type service public (ou compagnie) sur le plateau du Monopoly.
 * Elle hérite de la classe Case.
 */
public class Service extends Case {
    
    private final int[] multiplicateurServives = {4, 10}; // Les multiplicateurs de loyer pour les services
    private JoueurMonopoly proprietaire; // Le joueur qui possède le service
    private int valeurAchat; // Le prix d'achat du service
    
    /**
     * Constructeur de la classe CaseService.
     * @param nom Le nom du service.
     * @param id L'identifiant du service sur le plateau.
     * @param valeurAchat Le prix d'achat du service.
     */
    public Service(String nom, int id, int valeurAchat) {
        super(nom, id);
        assert (valeurAchat > 0 && id > 0);
        this.valeurAchat = valeurAchat;
    }
    

    /**
     * Obtenir le prix d'achat du service.
     * @return Le prix d'achat du service.
     */
    public int getValeurAchat() {
        return this.valeurAchat;
    }
    
    /**
     * Obtenir le propriétaire du service.
     * @return Le joueur qui possède le service.
     */
    public JoueurMonopoly getProprietaire() {
        return this.proprietaire;
    }
    
    /**
     * Vérifier si le service est achetable.
     * @return true si le service est achetable (sans propriétaire), false sinon.
     */
    public boolean estAchetable() {
        return (this.proprietaire == null);
    }

    /**
     * Calculer le loyer à payer en fonction du résultat du lancer de dés
     * et du nombre de services possédés par le propriétaire.
     * @param resultatDes Le résultat du lancer de dés.
     * @return Le loyer à payer.
     */
    public int getLoyer(int resultatDes) {
        if (proprietaire != null && proprietaire.getNbServices() == 2) {
            return resultatDes * multiplicateurServives[1];
        }
        return resultatDes * multiplicateurServives[0];
    }

    /**
     * Définir le propriétaire du service.
     * @param proprietaire Le joueur qui devient propriétaire du service.
     */
    public void setProprietaire(JoueurMonopoly proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * Retirer le propriétaire du service, le rendant disponible à l'achat.
     */
    public void removeProprietaire() {
        this.proprietaire = null;
    }

    /**
     * Méthode héritée de la classe Case mais non implémentée pour un service.
     * La méthode est destinée à être définie dans les sous-classes selon le comportement souhaité.
     */
    public void action(JoueurMonopoly joueur, Plateau plateau) {
        // À implémenter selon les besoins spécifiques de chaque type de service
    }
}
