import java.awt.Color;
import java.util.ArrayList;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import GestionMonopoly.Cases.GroupeProprietes;
import GestionMonopoly.Cases.Propriete;

public class Partie {
    public static void main(String[] args) {
        // ----------------- Set-up du jeu ---------------------    
            // Création des joueurs
            ArrayList<JoueurMonopoly> joueurs = new ArrayList<>();
            JoueurMonopoly Alice = new JoueurMonopoly("Alice", 0, 1500,Color.pink);
            joueurs.add(Alice);
            joueurs.add(new JoueurMonopoly("Bob", 1, 1500,Color.green));
            joueurs.add(new JoueurMonopoly("Charlie", 2, 1500,Color.red));

            Plateau plateau = new Plateau(joueurs);
            //Alice.acheterPropriete((Propriete)plateau.getCase(1));
            //Alice.acheterPropriete((Propriete)plateau.getCase(3));
            // plateau.deplacerJoueur(Alice, 17); Ici pour tester les cases communautés
            // plateau.deplacerJoueur(Alice, 7); Ici pour tester les cases chances
            // plateau.deplacerJoueur(Alice, 30); Ici pour tester l'interface de prison
            plateau.afficheFenetre();
            

        // ------------------Jouer la partie -------------------
        
        while (!plateau.partieFinie()){
            plateau.jouerTour();
        }
        
    }
}