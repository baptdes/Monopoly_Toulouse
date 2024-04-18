package Interface_graphique;

import javax.swing.*;
import java.awt.*;

public class VuePlateau extends JPanel {

    public static Font textFont = new Font("Verdana", Font.PLAIN, 12);
    public static Font titleFont = new Font("Verdana", Font.BOLD, 15);
    final private static int lengthFrame = 850;
    final private static int lengthPion = 75;

    public VuePlateau(int[] nbCasesPions, String[] nomsJoueurs) {

        // Chargement de l'image du plateau
        ImageIcon pngPlateau = new ImageIcon("Plateau_monopoly_toulouse.png");
        ImageIcon pngPion = new ImageIcon("Pion_Monopoly_1.png");

        // Redimensionner l'image pour qu'elle prenne toute la hauteur donnée
        Image scaledPlateau = pngPlateau.getImage().getScaledInstance(lengthFrame, lengthFrame, Image.SCALE_SMOOTH);
        Image scaledPion = pngPion.getImage().getScaledInstance(lengthPion, lengthPion, Image.SCALE_SMOOTH);

        // Création d'un JLabel avec l'image redimensionnée
        JLabel plateau = new JLabel(new ImageIcon(scaledPlateau));
        plateau.setLayout(null); // Permet de positionner les éléments absolument
        this.add(plateau);

        // Ajout du bouton fin de tour
        JButton bFinTour = new ModernButton("Finir le tour",Color.darkGray,Color.white);
        bFinTour.setBounds(500-140, 490, 130, 35);
        plateau.add(bFinTour);

        // Ajout du bouton fin de la partie
        JButton bFinPartie = new ModernButton("Finir la partie",new Color(123, 36, 28),Color.white);
        bFinPartie.setBounds(500, 490, 140, 35);
        plateau.add(bFinPartie);

        // Ajout d'un pion
        for (int i = 0; i < nbCasesPions.length;i++){
            JLabel pion = new JLabel(new ImageIcon(scaledPion));
            int[] coord = getCoordonneesCase(nbCasesPions[i]);
            pion.setBounds(coord[0], coord[1], lengthPion, lengthPion);
            plateau.add(pion);
        }

        // Position horizontale initiale pour les joueurs
        int posInit = 120;

        // Création du tableau de JPanel pour les joueurs
        JPanel[] joueurPanels = new JPanel[nomsJoueurs.length];

        // Création et affichage des JPanel pour chaque joueur
        String[] propriétés = {"Rue des filatiers","Hazebrouck","Toulouse"};
        String[] gares = {"Gare Matabiau"};
        for (int i = 0; i < nomsJoueurs.length; i++) {
            joueurPanels[i] = new Panneau_joueur(nomsJoueurs[i],1000,propriétés,gares);
            joueurPanels[i].setBounds(posInit, 120, 140, 200); // Position et taille du JPanel du joueur
            plateau.add(joueurPanels[i]);
            posInit += 150; // Espacement horizontal entre les joueurs
        }
    }

    private int[] getCoordonneesCase(int nbCase){
        int x = 0; int y = 0;
        if (nbCase == 0) { //Case de départ
            x = 735; y = 735;
        }
        else if (nbCase <= 9) { //Ligne du bas
            x = 660 - 69 * (nbCase - 1); y = 750;
        }
        else if (nbCase == 10){ //Visite simple
            x = 20; y = 780;
        }
        else if (nbCase <= 19) { //Ligne de gauche
            x = 10 ; y = 660 - 69 * (nbCase - 11);
        }
        else if (nbCase == 20) { //Parking gratuit
            x = 15; y = 15;
        }
        else if (nbCase <= 29) { //Ligne du haut
            x = 105 + 69 * (nbCase - 21);; y = 10;
        }
        else if (nbCase == 30) { //Direction prison
            x = 750; y = 10;
        }
        else if (nbCase <= 39) { //Ligne de droite
            x = 760; y = 110 + + 69 * (nbCase - 31);
        }
        else if (nbCase == 40) { //Case prison
            x = 35; y = 730;
        }
        else{
            throw new CaseInvalideException();
        }
        int[] coordonnees = {x,y};
        return coordonnees;
    }

    public static void main(String[] args) {
        // Création d'une fenêtre JFrame
        JFrame frame = new JFrame("VuePlateau Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout de la VuePlateau à la fenêtre
        String[] nomsJoueurs = {"Pépé","Mémé","Papa","Maman"};
        int[] cases = {5,10};
        VuePlateau panel = new VuePlateau(cases,nomsJoueurs);
        frame.add(panel);

        // Dimension de la fenêtre
        frame.setSize(lengthFrame, lengthFrame);
        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}