package Interface_graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VuePlateau extends JPanel {

    // Constantes publiques pour le style des textes
    final public static Font argentFont = new Font("DejaVu Sans", Font.PLAIN, 18);
    final public static Font textFont = new Font("DejaVu Sans", Font.PLAIN, 14);
    final public static Font titleFont = new Font("DejaVu Sans", Font.BOLD, 18);
    final public static Font nomJoueurFont = new Font("Loma", Font.BOLD, 25);

    // Constante
    final public static int lengthPion = 75;

    //Attributs
    private int[] cases_occupées = new int[40];
    private ArrayList<?> joueurs;
    private Pion[] pions;

    public VuePlateau(Pion[] pions, Panneau_joueur[] joueurs) {
        this.pions = pions;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.decode("#fdf2e9"));

        // Chargement de l'image du plateau
        ImageIcon pngPlateau = new ImageIcon("Plateau_monopoly_toulouse.png");

        // Redimensionner l'image pour qu'elle prenne toute la hauteur donnée
        Image scaledPlateau = pngPlateau.getImage().getScaledInstance(850, 850, Image.SCALE_SMOOTH);

        // Création d'un JLabel avec l'image redimensionnée
        JLabel plateau = new JLabel(new ImageIcon(scaledPlateau));
        plateau.setLayout(null); // Permet de positionner les éléments absolument
        this.add(plateau);

        // Ajout du bouton fin de tour
        JButton bFinTour = new ModernButton("Finir le tour",Color.darkGray,Color.white);
        bFinTour.setBounds(540-140, 525, 130, 35);
        plateau.add(bFinTour);
        bFinTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joueurs[0].addPropriété("Ca marche !");
            }
        });

        // Ajout du bouton fin de la partie
        JButton bFinPartie = new ModernButton("Finir la partie",new Color(123, 36, 28),Color.white);
        bFinPartie.setBounds(540, 525, 140, 35);
        plateau.add(bFinPartie);

        // Ajout d'un pion
        for (int i = 0; i < pions.length;i++){
            plateau.add(pions[i]);
            cases_occupées[pions[i].getPosition()]++;
        }

        // Position horizontale initiale pour les joueurs
        int posInit = 120;

        // Affichage des panneaux pour chaque joueur
        for (int i = 0; i < joueurs.length; i++) {
            this.add(Box.createHorizontalStrut(10));
            this.add(joueurs[i]);
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

        // Création du tableau de JPanel pour les joueurs
        String[] nomsJoueurs = {"Pépé","Mémé","Papa","Maman"};
        Panneau_joueur[] joueurPanels = new Panneau_joueur[nomsJoueurs.length];
        // Création et affichage des JPanel pour chaque joueur
        String[] propriétés = {"Rue des filatiers","Hazebrouck","Toulouse","Rue des filatiers","Hazebrouck","Toulouse","Rue des filatiers","Hazebrouck","Toulouse","Rue des filatiers","Hazebrouck","Toulouse","Rue des filatiers","Hazebrouck","Toulouse","Rue des filatiers","Hazebrouck","Toulouse","Rue des filatiers","Hazebrouck","Toulouse"};
        String[] gares = {"Gare Matabiau"};
        for (int i = 0; i < nomsJoueurs.length; i++) {
            joueurPanels[i] = new Panneau_joueur(nomsJoueurs[i],1000,propriétés,gares);
        }

        Pion pion1 = new Pion(1,0);
        Pion pion2 = new Pion(1,1);
        Pion[] liste_pions = {pion1,pion2};
        VuePlateau panel = new VuePlateau(liste_pions,joueurPanels);
        frame.add(panel);

        // Dimension de la fenêtre
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}