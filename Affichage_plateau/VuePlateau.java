package Affichage_plateau;

import javax.swing.*;
import java.awt.*;

public class VuePlateau extends JPanel {

    public VuePlateau(int frameWidth, int frameHeight) {
        // Chargement de l'image du plateau
        ImageIcon jpgPlateau = new ImageIcon("monopoly-classique-plateau.jpg");
        Image image = jpgPlateau.getImage(); // Chargement de l'image

        // Redimensionner l'image pour qu'elle prenne toute la hauteur donnée
        int imageWidth = (int) (frameHeight * ((double) jpgPlateau.getIconWidth() / jpgPlateau.getIconHeight()));
        Image scaledImage = image.getScaledInstance(imageWidth, frameHeight, Image.SCALE_SMOOTH);

        // Création d'un JLabel avec l'image redimensionnée
        JLabel plateau = new JLabel(new ImageIcon(scaledImage));
        this.add(plateau);

        // Ajout d'un bouton au milieu de l'image
        JButton bFinTour = new JButton("Finir le tour");
        int bFinTourX = frameWidth - frameWidth/2;
        int bFinTourY = frameHeight - frameWidth/5;
        bFinTour.setBounds(bFinTourX, bFinTourY, 125, 50);
        plateau.setLayout(null); // Permet de positionner le bouton absolument
        plateau.add(bFinTour);
    }

    public static void main(String[] args) {
        // Création d'une fenêtre JFrame
        JFrame frame = new JFrame("VuePlateau Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Dimension de la fenêtre
        int frameWidth = 1000;
        int frameHeight = 1000;

        // Ajout de la VuePlateau à la fenêtre
        VuePlateau panel = new VuePlateau(frameWidth,frameHeight);
        frame.add(panel);

        // Dimension de la fenêtre
        frame.setSize(frameWidth, frameHeight);
        // Rendre la fenêtre visible
        frame.setVisible(true);
        //test
    }
}