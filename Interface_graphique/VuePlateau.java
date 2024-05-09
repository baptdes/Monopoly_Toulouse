package Interface_graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VuePlateau extends JFrame {

    // Constantes publiques pour le style des textes
    final public static Font argentFont = new Font("DejaVu Sans", Font.PLAIN, 18);
    final public static Font textFont = new Font("DejaVu Sans", Font.PLAIN, 14);
    final public static Font titleFont = new Font("DejaVu Sans", Font.BOLD, 18);
    final public static Font nomJoueurFont = new Font("Loma", Font.BOLD, 25);

    // Constante
    final private ImageIcon pngPlateau = new ImageIcon("Plateau_monopoly_toulouse.png");
    private int originalWidth = pngPlateau.getIconWidth(); 
    private int originalHeight = pngPlateau.getIconHeight();

    //Attributs
    private int[] cases_occupées = new int[41];
    private ArrayList<?> joueurs;
    private Pion[] pions;
    private JLabel plateau;
    private JButton bFinTour;
    private JButton bFinPartie;    

    public VuePlateau(Pion[] pions, Panneau_joueur[] joueurs) {
        // ---- Création de la fenêtre + paramètrages ----
        super("Monopoly Toulouse");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        this.setBackground(Color.decode("#fdf2e9"));
        this.setSize(new Dimension(500,500));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // ---------- Compléter les attributs ------------
        this.pions = pions;

        // ------------- Remplissage Jframe --------------

        //////// Image du plateau /////////
        // Redimmensioner le plateau
        Image scaledPlateau = pngPlateau.getImage().getScaledInstance(850, 850, Image.SCALE_SMOOTH);
        JLabel plateauLabel = new JLabel(new ImageIcon(scaledPlateau));
        plateauLabel.setLayout(null); // Permet de positionner les éléments absolument
        this.plateau = plateauLabel;
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
        plateau.add(bFinTour);
        bFinTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deplacerPion(0,pions[0].getPosition() + 1);
            }
        });

        // 2 -- Bouton de fin de partie
        this.bFinPartie = new ModernButton("Finir la partie",new Color(123, 36, 28),Color.white);
        bFinPartie.setBounds(540, 525, 140, 35);
        plateau.add(bFinPartie);
        bFinPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deplacerPion(1,pions[1].getPosition() + 1);
            }
        });


        /////////// Les pions ///////////
        for (int i = 0; i < pions.length;i++){
            plateau.add(pions[i]);
            cases_occupées[pions[i].getPosition()]++;
        }

        /////// Les panneaux joueurs ////////
        JPanel joueursPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        for (int i = 0; i < joueurs.length; i++) {
            joueursPanel.add(joueurs[i]);
        }
        this.add(Box.createHorizontalStrut(15));
        this.add(joueursPanel);
        this.add(Box.createHorizontalStrut(15));
    }

    /** Fonction pour redimensionner une image tout en conservant ses proportions d'origine  */ 
    private Image resizeImage(Image image, int width, int height) {
        int newWidth, newHeight;
        if (width * originalHeight < height * originalWidth) {
            newWidth = width;
            newHeight = newWidth * originalHeight / originalWidth;
        } else {
            newWidth = height * originalWidth / originalHeight;
            newHeight = height;
        }
        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    private void updatePlateauWidth(){
        Image scaledPlateau = resizeImage(pngPlateau.getImage(), getWidth()/2, getHeight());
        plateau.setIcon(new ImageIcon(scaledPlateau));
        for (int i = 0; i < pions.length;i++){
            pions[i].updatePlateauWidth(getWidth()/2,cases_occupées);
        }
        double factor = (double) (getWidth()/2) / 850;
        this.bFinPartie.setBounds((int) (400 * factor),(int) (525 * factor),(int) (130 * factor),(int) (35 * factor));
        this.bFinTour.setBounds((int) (540 * factor),(int) (525 * factor),(int) (140* factor),(int) (35 * factor));
    }

    void deplacerPion(int nbPion,int nbCase){
        if (nbPion > 40 || nbPion < 0){
            throw new CaseInvalideException();
        }
        cases_occupées[pions[nbPion].getPosition()]--;
        pions[nbPion].setPositionPion(nbCase, cases_occupées);
        cases_occupées[pions[nbPion].getPosition()]++;
    }

    public static void main(String[] args) {
        // Création du tableau de JPanel pour les joueurs
        String[] nomsJoueurs = {"Pépé","Mémé","Papa","Maman"};
        Panneau_joueur[] joueurPanels = new Panneau_joueur[nomsJoueurs.length];
        // Création et affichage des JPanel pour chaque joueur
        String[] propriétés = {"Rue des filatiers","Hazebrouck"};
        String[] gares = {"Gare Matabiau"};
        for (int i = 0; i < nomsJoueurs.length; i++) {
            joueurPanels[i] = new Panneau_joueur(nomsJoueurs[i],1000,propriétés,gares);
        }

        Pion pion1 = new Pion(1,0,850);
        Pion pion2 = new Pion(1,1,850);
        Pion[] liste_pions = {pion1,pion2};
        VuePlateau frame = new VuePlateau(liste_pions,joueurPanels);
        // Dimension de la fenêtre
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}