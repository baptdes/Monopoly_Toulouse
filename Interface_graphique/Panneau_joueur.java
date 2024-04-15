package Interface_graphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


/** Classe qui sert à créer un panneau qui présente les informations d'un joueur */
public class Panneau_joueur extends RoundedPanel {
    
    /** Méthode pour créer le JPanel d'un joueur avec son nom, son argent et ses propriétés 
     * @param nomJoueur Nom du joueur
    */
    public Panneau_joueur(String nomJoueur,int argent, String[] propriétés, String[] gares) {
        // Créer un RoundedPanel pour le joueur
        super(15,Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(6,6,6,6));
    
        // Nom du joueur
        JLabel nomJoueurLabel = new JLabel(nomJoueur);
        nomJoueurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomJoueurLabel.setOpaque(false);
        nomJoueurLabel.setFont(VuePlateau.titleFont);
        
        //Ajout et agencement des différents composants
        this.add(nomJoueurLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(créerArgentPanel(argent));
        this.add(Box.createVerticalStrut(10));
        this.add(créerPropriétéPanel(propriétés));
        this.add(Box.createVerticalStrut(10));
        this.add(créerGaresPanel(gares));
    }
    
    /**
     * Crée le Panel qui donne l'argent du joueur
     * @param argent Somme d'argent à afficher dans le panel
     * @return Le JPanel qui présente l'argent du joueur
     */
    private JPanel créerArgentPanel(int argent) {
        // Titre "Argent"
        JLabel titreArgentLabel = new JLabel("Argent");
        titreArgentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreArgentLabel.setOpaque(false);
        titreArgentLabel.setFont(VuePlateau.titleFont);

        // Montant du joueur
        JLabel montantArgentLabel = new JLabel(argent + " M$");
        montantArgentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        montantArgentLabel.setOpaque(false);
        montantArgentLabel.setFont(VuePlateau.textFont);

        // Création du Panel
        RoundedPanel argentPanel = new RoundedPanel(15,Color.white);
        argentPanel.setLayout(new BoxLayout(argentPanel, BoxLayout.Y_AXIS));
        argentPanel.setOpaque(false);
        //Ajout des éléments dans le panel
        argentPanel.add(titreArgentLabel);
        argentPanel.add(Box.createVerticalStrut(4));
        argentPanel.add(montantArgentLabel);

        return argentPanel;
    }

    /**
     * Crée le panel qui présente les propriétes du joueur
     * @param proprietes Tableau des propriétés que possédées le joueur
     * @return JPanel qui présente les propriétes du joueur
     */
    private JPanel créerPropriétéPanel(String[] proprietes) {
        // Créer un JPanel pour les propriétés
        JPanel proprietesPanel = new JPanel();
        proprietesPanel.setOpaque(false);
        proprietesPanel.setLayout(new BoxLayout(proprietesPanel, BoxLayout.Y_AXIS));

        // Titre des propriétés
        JLabel titreLabel = new JLabel("Propriétés");
        titreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreLabel.setFont(VuePlateau.titleFont);
        proprietesPanel.add(titreLabel);

        //Espace entre titre et texte
        proprietesPanel.add(Box.createVerticalStrut(4));

        //Liste des propriétés
        for (String propriete : proprietes) {
            JLabel proprieteLabel = new JLabel(propriete);
            proprieteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            proprieteLabel.setFont(VuePlateau.textFont);
            proprietesPanel.add(proprieteLabel);
        }

        return proprietesPanel;
    }

        /**
     * Crée le panel qui présente les propriétes du joueur
     * @param gares Tableau des gares que possédées le joueur
     * @return JPanel qui présente les propriétes du joueur
     */
    private JPanel créerGaresPanel(String[] gares) {
        // Créer un JPanel pour les propriétés
        JPanel garesPanel = new JPanel();
        garesPanel.setOpaque(false);
        garesPanel.setLayout(new BoxLayout(garesPanel, BoxLayout.Y_AXIS));

        // Titre des propriétés
        JLabel titreLabel = new JLabel("Gares");
        titreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreLabel.setFont(VuePlateau.titleFont);
        garesPanel.add(titreLabel);

        //Espace entre titre et texte
        garesPanel.add(Box.createVerticalStrut(4));

        //Liste des gares
        for (String gare : gares) {
            JLabel gareLabel = new JLabel(gare);
            gareLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gareLabel.setFont(VuePlateau.textFont);
            garesPanel.add(gareLabel);
        }

        return garesPanel;
    }
}