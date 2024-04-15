package Interface_graphique;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class VuePlateau extends JPanel {

    public static Font textFont = new Font("Verdana", Font.PLAIN, 12);
    public static Font titleFont = new Font("Verdana", Font.BOLD, 15);
    final private static int lengthFrame = 800;
    final private int[][] positionCases = {{1, 2}, {3, 4}, {5, 6}};
    private int casePion;
    

    public VuePlateau(int casePion, String[] nomsJoueurs) {

        // Chargement de l'image du plateau
        ImageIcon jpgPlateau = new ImageIcon("Plateau_monopoly_toulouse.png");
        Image image = jpgPlateau.getImage(); // Chargement de l'image

        // Redimensionner l'image pour qu'elle prenne toute la hauteur donnée
        int imageWidth = (int) (lengthFrame * ((double) jpgPlateau.getIconWidth() / jpgPlateau.getIconHeight()));
        Image scaledImage = image.getScaledInstance(imageWidth, lengthFrame, Image.SCALE_SMOOTH);

        // Création d'un JLabel avec l'image redimensionnée
        JLabel plateau = new JLabel(new ImageIcon(scaledImage));
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

    public static void main(String[] args) {
        // Création d'une fenêtre JFrame
        JFrame frame = new JFrame("VuePlateau Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout de la VuePlateau à la fenêtre
        String[] nomsJoueurs = {"Pépé","Mémé","Papa","Maman"};
        VuePlateau panel = new VuePlateau(2,nomsJoueurs);
        frame.add(panel);

        // Dimension de la fenêtre
        frame.setSize(lengthFrame, lengthFrame);
        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}