public class GestionEvenementsMonopoly {
    private Plateau plateau;
    private Des des;

    public GestionEvenementsMonopoly(Plateau plateau, Des des) {
        this.plateau = plateau;
        this.des = des;
    }

    public void jouerTour(JoueurMonopoly joueur) {
        int[] resultatsDes = des.lancerLesDes();
        int totalDes = resultatsDes[0] + resultatsDes[1];

        // Vérifie si le joueur fait un double
        if (resultatsDes[0] == resultatsDes[1]) {
            joueur.incrementerDoubles();
            if (joueur.getNbDoubles() == 3) {
                envoyerEnPrison(joueur);
                return;
            }
        } else {
            joueur.resetDoubles();
        }

        // Déplacement du joueur
        joueur.deplacer(totalDes);
        Case caseCourante = plateau.getCase(joueur.getPosition());

        // Gestion des événements de la case
        gererCase(joueur, caseCourante);

        // Gestion du passage par la case départ
        if (joueur.isPasseParDepart()) {
            joueur.recevoirArgent(200);
        }

        // Si double, le joueur rejoue
        if (resultatsDes[0] == resultatsDes[1] && !joueur.estEnPrison()) {
            jouerTour(joueur);
        }
    }

    private void gererCase(JoueurMonopoly joueur, Case caseCourante) {
        if (caseCourante instanceof CaseDepart) {
            joueur.recevoirArgent(200);
        } else if (caseCourante instanceof CaseAllerEnPrison) {
            envoyerEnPrison(joueur);
        } else if (caseCourante instanceof CaseTaxe) {
            int taxe = ((CaseTaxe) caseCourante).getTaxe();
            joueur.payer(taxe);
        } else if (caseCourante instanceof CaseTerrain) {
        }
    }

    private void envoyerEnPrison(JoueurMonopoly joueur) {
        joueur.setEnPrison(true);
        joueur.setPosition(plateau.getPositionPrison());
        joueur.resetDoubles();
    }
}
