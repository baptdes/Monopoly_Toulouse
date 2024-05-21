package Interface_graphique;

import javax.swing.*;
import javax.swing.border.Border;
import GestionMonopoly.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VuePlateau extends JFrame {
    // Constantes publiques pour le style des textes
    public static final Font argentFont = new Font("DejaVu Sans", Font.PLAIN, 16);
    public static final Font textFont = new Font("DejaVu Sans", Font.PLAIN, 14);
    public static final Font titleFont = new Font("DejaVu Sans", Font.BOLD, 16);
    public static final Font nomJoueurFont = new Font("Loma", Font.BOLD, 20);

    // Données sur l'image du plateau
    private final ImageIcon pngPlateau = new ImageIcon("Plateau_monopoly_toulouse.png");
    private final int originalWidth = pngPlateau.getIconWidth();
    private final int originalHeight = pngPlateau.getIconHeight();

    public final int refTaille = 850;

    // Attributs
    private int[] cases_occupées = new int[41];
    private ArrayList<JoueurMonopoly> joueurs;
    private Pion[] pions;
    private JLabel plateau;
    private JButton bFinTour;
    private JButton bFinPartie;
    private Plateau plateauDeJeu;

    private double scaleFactor;

    public VuePlateau(Pion[] pions, JoueurMonopoly[] joueurs, Plateau plateauDeJeu) {
        super("Monopoly Toulouse");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        this.setBackground(Color.decode("#fdf2e9"));
        this.setSize(new Dimension(500, 500));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.pions = pions;
        this.plateauDeJeu = plateauDeJeu;
        this.joueurs = new ArrayList<>();
        for (JoueurMonopoly joueur : joueurs) {
            this.joueurs.add(joueur);
        }

        setupPlateau();
        setupButtons();
        setupPionsEtJoueurs();
    }

    private void setupPlateau() {
        Image scaledPlateau = pngPlateau.getImage().getScaledInstance(850, 850, Image.SCALE_SMOOTH);
        JLabel plateauLabel = new JLabel(new ImageIcon(scaledPlateau));
        plateauLabel.setLayout(null);
        this.plateau = plateauLabel;
        this.add(plateauLabel);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePlateauWidth();
            }
        });

        // Ajout des boutons pour chaque case propriété
        for (int i = 0; i < 40; i++) {
            final int caseIndex = i; // Déclarez une nouvelle variable locale finale
            JButton caseButton = new JButton();
            caseButton.setBounds(getCaseBounds(caseIndex)); // Remplacez getCaseBounds par une méthode pour obtenir les
                                                            // coordonnées de chaque case
            caseButton.setContentAreaFilled(false);
            caseButton.setBorderPainted(false);
            caseButton.addActionListener(e -> showProprieteWindow(caseIndex));
            plateau.add(caseButton);
        }
    }

    private void showProprieteWindow(int caseIndex) {
        JoueurMonopoly joueur = getJoueurActuel();
        Case caseActuelle = plateauDeJeu.getCase(caseIndex);

        if (caseActuelle instanceof CaseTerrain) {
            CaseTerrain caseTerrain = (CaseTerrain) caseActuelle;
            new FenetrePropriete(joueur, caseTerrain).setVisible(true);
        }
    }

    private JoueurMonopoly getJoueurActuel() {
        // Ici, nous retournons toujours le premier joueur pour simplifier
        return joueurs.get(0);
    }

    private Rectangle getCaseBounds(int caseIndex) {
        // Cette méthode pour retourner les coordonnées et dimensions de
        // chaque case
        return new Rectangle(0, 0, 100, 100); // Exemple simplifié
    }

    private void setupButtons() {
        bFinTour = new ModernButton("Finir le tour", Color.darkGray, Color.white);
        bFinTour.setBounds(400, 525, 130, 35);
        plateau.add(bFinTour);
        bFinTour.addActionListener(e -> {
            deplacerPion(0, pions[0].getPosition() + 1);
        });

        bFinPartie = new ModernButton("Finir la partie", new Color(123, 36, 28), Color.white);
        bFinPartie.setBounds(540, 525, 140, 35);
        plateau.add(bFinPartie);
        bFinPartie.addActionListener(e -> {
            deplacerPion(0, pions[0].getPosition() - 1);
        });
    }

    private void setupPionsEtJoueurs() {
        for (int i = 0; i < pions.length; i++) {
            plateau.add(pions[i]);
            cases_occupées[pions[i].getPosition()]++;
        }

        JPanel joueursPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        Border border = BorderFactory.createEmptyBorder(5, 10, 5, 5);
        joueursPanel.setBorder(border);
        for (JoueurMonopoly joueur : joueurs) {
            Panneau_joueur panneauJoueur = joueur.getPanel();
            joueursPanel.add(panneauJoueur);
        }
        this.add(joueursPanel);
    }

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

    private void updatePlateauWidth() {
        Image scaledPlateau = resizeImage(pngPlateau.getImage(), getWidth() / 2, getHeight());
        plateau.setIcon(new ImageIcon(scaledPlateau));
        for (int i = 0; i < pions.length; i++) {
            cases_occupées[pions[i].getPosition()] = 0;
        }
        for (int i = 0; i < pions.length; i++) {
            pions[i].updatePlateauWidth(scaleFactor, cases_occupées[pions[i].getPosition()]);
            cases_occupées[pions[i].getPosition()]++;
        }
        bFinPartie.setBounds((int) (400 * scaleFactor), (int) (525 * scaleFactor), (int) (130 * scaleFactor),
                (int) (35 * scaleFactor));
        bFinTour.setBounds((int) (540 * scaleFactor), (int) (525 * scaleFactor), (int) (140 * scaleFactor),
                (int) (35 * scaleFactor));
    }

    public void deplacerPion(int nbPion, int nbCase) {
        if (nbPion >= pions.length || nbPion < 0) {
            throw new CaseInvalideException();
        }
        if (nbCase > 40) {
            nbCase = nbCase % 40;
        }
        cases_occupées[pions[nbPion].getPosition()]--;
        if (cases_occupées[nbCase] > 0) {
            cases_occupées[nbCase] = 0;
            for (Pion pion : pions) {
                if (pion.getPosition() == nbCase) {
                    pion.updatePlateauWidth(scaleFactor, cases_occupées[pion.getPosition()]);
                    cases_occupées[nbCase]++;
                }
            }
        }
        pions[nbPion].setPositionPion(nbCase, cases_occupées[nbCase]);
        cases_occupées[pions[nbPion].getPosition()]++;
        verifierCasePion(nbCase);
    }

    private void verifierCasePion(int position) {
        Case caseActuelle = plateauDeJeu.getCase(position);
        if (caseActuelle instanceof CaseTerrain) {
            JoueurMonopoly joueur = getJoueurActuel();
            CaseTerrain caseTerrain = (CaseTerrain) caseActuelle;
            new FenetrePropriete(joueur, caseTerrain).setVisible(true);
        }
    }
}
