
public class CaseCommunaute implements Case {
    private String nom;

    public CaseCommunaute() {
        this.nom = "Communauté";
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void action(JoueurMonopoly joueur) {
        /**
        *Supposons que nous avons une méthode pour tirer une carte
        */
        Carte carte = tirerCarteCommunaute();
        System.out.println(joueur.getNom() + " tire la carte " + carte.getNom());

        /**
	* Supposons que les cartes ont une méthode pour exécuter une action
	*/
        carte.executerAction(joueur);

        /**
	* Affichez les informations sur la carte tirée
        */
	afficherCarte(carte);
    }

    private Carte tirerCarteCommunaute() {
        
        return new CarteCommunaute();
    }

    private void afficherCarte(Carte carte) {
        
        System.out.println("Carte Communauté: " + carte.getNom() + " - " + carte.getDescription());
    }

   }
