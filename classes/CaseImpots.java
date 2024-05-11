public class CaseImpots implements Case {
    private String nom;
    private int montantImpot;

    /**
     * Constructeur pour initialiser le nom et le montant de l'impôt pour la case.
     * @param nom String  le nom de la case
     * @param montantImpot int  le montant à payer pour l'impôt
     */
    public CaseImpots(String nom, int montantImpot) {
        this.nom = nom;
        this.montantImpot = montantImpot;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode pour exécuter l'action de la case impôt, 
     * retirant de l'argent du joueur et l'ajoutant au parc gratuit.
     * @param joueur JoueurMonopoly  le joueur qui doit payer l'impôt
     */
    @Override
    public void action(JoueurMonopoly joueur) {
        System.out.println(joueur.getNom() + " paie " + this.montantImpot + "€ d'impôts.");

        joueur.retirerArgent(this.montantImpot);

        // Ajouter le montant payé au Parc Gratuit.
        // Supposons que Parc Gratuit est géré par une classe singleton ou par une méthode statique.
        ParcGratuit.ajouterAuPot(this.montantImpot);
    }
}
