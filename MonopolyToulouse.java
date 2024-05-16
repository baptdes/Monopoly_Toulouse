import java.awt.Color;
import java.util.ArrayList;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

public class MonopolyToulouse {
    public static void main(String[] args) {
        // ----------------- Set-up du jeu ---------------------    
            // Création des joueurs
            ArrayList<JoueurMonopoly> joueurs = new ArrayList<>();
            joueurs.add(new JoueurMonopoly("Alice", 0, 1500,Color.pink));
            joueurs.add(new JoueurMonopoly("Bob", 1, 1500,Color.green));
            joueurs.add(new JoueurMonopoly("Charlie", 2, 1500,Color.red));

            Plateau plateau = new Plateau(joueurs);
            plateau.afficheFenetre();

        // ------------------Jouer la partie -------------------
        
        while (!plateau.partieFinie()){
            // 1 - Le joueur actif lance les dés
            plateau.getDes().lancer();
            
            // 2 - Le joueur actif avance
            plateau.deplacerJoueurActif(plateau.getDes().getResultat());

            // 3 - Activation de l'effet de la case sur laquelle il se trouve
            plateau.getCaseJoueurActif().action(plateau.getJoueurActif(), plateau);

            // TODO : Veut-il vendre ?

            // 5 - Son tour est fini
            
        }
        
    }
}