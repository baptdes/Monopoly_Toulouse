package Interface_graphique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VuePlateau extends JPanel {

    // Constantes publiques pour le style des textes
    final public static Font textFont = new Font("Verdana", Font.PLAIN, 12);
    final public static Font titleFont = new Font("Verdana", Font.BOLD, 15);

    // Constante
    final private static int lengthFrame = 850;
    final public static int lengthPion = 75;

    //Attributs
    private int[] cases_occupées = new int[40];
    private ArrayList<?> joueurs;
    private Pion[] pions;

    public VuePlateau(Pion[] pions, String[] nomsJoueurs) {
        this.pions = pions;


        // Chargement de l'image du plateau
        ImageIcon pngPlateau = new ImageIcon("Plateau_monopoly_toulouse.png");

        // Redimensionner l'image pour qu'elle prenne toute la hauteur donnée
        Image scaledPlateau = pngPlateau.getImage().getScaledInstance(lengthFrame, lengthFrame, Image.SCALE_SMOOTH);

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
        for (int i = 0; i < pions.length;i++){
            plateau.add(pions[i]);
            cases_occupées[pions[i].getPosition()]++;
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

    void deplacerPion(int nbPion,int nbCase){
        cases_occupées[pions[nbPion].getPosition()]--;
        pions[nbPion].setPositionPion(nbCase, cases_occupées[nbCase]);
        cases_occupées[pions[nbPion].getPosition()]++;
    }

    public static void main(String[] args) {
        // Création d'une fenêtre JFrame
        JFrame frame = new JFrame("VuePlateau Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout de la VuePlateau à la fenêtre
        String[] nomsJoueurs = {"Pépé","Mémé","Papa","Maman"};
        Pion pion1 = new Pion(5,0);
        Pion pion2 = new Pion(5,1);
        Pion[] liste_pions = {pion1,pion2};
        VuePlateau panel = new VuePlateau(liste_pions,nomsJoueurs);
        frame.add(panel);

        // Dimension de la fenêtre
        frame.setSize(lengthFrame, lengthFrame);
        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}