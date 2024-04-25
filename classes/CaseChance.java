
public class CaseChance implements Case {
    private String nom;

    public CaseChance() {
        this.nom = "Chance";
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void action(JoueurMonopoly joueur) {
        // Supposons que nous avons une méthode pour tirer une carte de chance
        Carte carte = tirerCarteChance();

        // Log les informations de la carte tirée
        System.out.println(joueur.getNom() + " tire la carte " + carte.getNom());

        // Exécute l'action de la carte tirée
        carte.actionCarte(joueur);

        
    }

    private Carte tirerCarteChance() {
        // La logique pour tirer une carte chance du jeu
        // Cette méthode devrait interagir avec votre gestionnaire de cartes
        // ou la classe qui gère la pile de cartes de chance
        return new CarteChance(); // Supposons que c'est une classe concrète
    }
}
