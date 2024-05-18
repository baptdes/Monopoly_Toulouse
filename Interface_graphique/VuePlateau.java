package Interface_graphique;

import javax.swing.*;
import javax.swing.border.Border;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import GestionMonopoly.Cases.Gare;
import GestionMonopoly.Cases.Propriete;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VuePlateau extends JFrame {

    // Constantes publiques pour le style des textes
    final public static Font argentFont = new Font("DejaVu Sans", Font.PLAIN, 16);
    final public static Font textFont = new Font("DejaVu Sans", Font.PLAIN, 14);
    final public static Font titleFont = new Font("DejaVu Sans", Font.BOLD, 16);
    final public static Font nomJoueurFont = new Font("Loma", Font.BOLD, 20);

    // Données sur l'image du imagePlateau
    final private ImageIcon pngPlateau = new ImageIcon("Plateau_monopoly_toulouse.png");
    final private int originalWidth = pngPlateau.getIconWidth(); 
    final private int originalHeight = pngPlateau.getIconHeight();

    /** Taille de référence pour calculer l'emplacement des éléments */
    final public int refTaille = 850; 

    //Attributs
    private Plateau plateau;
    private Pion[] pions;
    private JLabel imagePlateau;
    private JButton bFinTour;
    private JButton bFinPartie;

    /** Facteur pour adapter la taille des éléments à la taille de la fenêtre */
    private double scaleFactor;

    public VuePlateau(Plateau plateau) {
        // ---- Création de la fenêtre + paramètrages ----
        super("Monopoly Toulouse");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        this.setBackground(Color.decode("#fdf2e9"));
        this.setSize(new Dimension(500,500));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // ------------- Remplissage Jframe --------------

        //////// Image du imagePlateau /////////
        // Redimmensioner le imagePlateau
        Image scaledPlateau = pngPlateau.getImage().getScaledInstance(850, 850, Image.SCALE_SMOOTH);
        JLabel plateauLabel = new JLabel(new ImageIcon(scaledPlateau));
        plateauLabel.setLayout(null); // Permet de positionner les éléments absolument
        this.imagePlateau = plateauLabel;
        this.add(plateauLabel);

        // Création de l'écouteur de redimensionnement
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePlateauWidth();
            }
        });

        /////////// Les Boutons ///////////
        // 1 -- Bouton de fin de tour
        this.bFinTour = new ModernButton("Finir le tour",Color.darkGray,Color.white);
        bFinTour.setBounds(400, 525, 130, 35);
        imagePlateau.add(bFinTour);
        bFinTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.tourEstFini();
            }
        });

        // 2 -- Bouton de fin de partie
        this.bFinPartie = new ModernButton("Finir la partie",new Color(123, 36, 28),Color.white);
        bFinPartie.setBounds(540, 525, 140, 35);
        imagePlateau.add(bFinPartie);
        bFinPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.getCase(0).action(plateau.getJoueurActif(), plateau);;
            }
        });

        /////////// Les pions et panneaux joueurs ///////////
        JoueurMonopoly joueurActuel;
        this.pions = new Pion[plateau.getNbJoueurs()];
        JPanel joueursPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        // Création d'une bordure avec des marges de 10 pixels
        Border border = BorderFactory.createEmptyBorder(5, 10, 5, 5);
        joueursPanel.setBorder(border);
        for (int i = 0; i < plateau.getNbJoueurs();i++){
            joueurActuel = plateau.getJoueur(i);
            pions[i] = joueurActuel.getPion();
            imagePlateau.add(pions[i]);
            joueursPanel.add(joueurActuel.getPanel());
        }
        this.add(joueursPanel);
    }

    /** Fonction pour redimensionner une image tout en conservant ses proportions d'origine 
     * @param image Image à redimensionner
     * @param width Largeur voulue
     * */ 
    private Image resizeImage(Image image, int width, int height) {
        int newWidth, newHeight;
        if (width * originalHeight < height * originalWidth) {
            newWidth = width;
            newHeight = newWidth * originalHeight / originalWidth;
            this.scaleFactor = (double) newHeight / refTaille;
        } else {
            newWidth = height * originalWidth / originalHeight;
            this.scaleFactor = (double) newWidth / refTaille;
            newHeight = height;
        }
        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    /** Met à jour la taille de tous les éléments de la fenêtre */
    private void updatePlateauWidth(){
        Image scaledPlateau = resizeImage(pngPlateau.getImage(), getWidth()/2, getHeight());
        imagePlateau.setIcon(new ImageIcon(scaledPlateau));
        int[] nbPionsParCases = new int[Plateau.NB_CASES];
        for (int i = 0; i < pions.length;i++){
            nbPionsParCases[pions[i].getPosition()]++;
            pions[i].updatePlateauWidth(scaleFactor,nbPionsParCases[pions[i].getPosition()]);
        }
        this.bFinPartie.setBounds((int) (400 * scaleFactor),(int) (525 * scaleFactor),(int) (130 * scaleFactor),(int) (35 * scaleFactor));
        this.bFinTour.setBounds((int) (540 * scaleFactor),(int) (525 * scaleFactor),(int) (140* scaleFactor),(int) (35 * scaleFactor));
    }

    /** Mettre à jour l'emplacement d'un pion sur l'imagePlateau dans la VuePlateau
     * @param pion pion qu'il faut mettre à jour
    */
    public void updatePositionPion(Pion pion){
        int position = pion.getPosition();
        int nbPionsCase = -1;
        for (int i = 0; i < pions.length;i++){
            if (pions[i].getPosition() == position){
                nbPionsCase++;
            }
        }
        pion.updatePosition(nbPionsCase);
    }
}