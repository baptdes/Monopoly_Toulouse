package Interface_graphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


/** Classe qui sert à créer un panneau qui présente les informations d'un joueur */
public class Panneau_joueur extends RoundedPanel {

    private JLabel argent;
    private ListeLabel proprietesPanel;
    private ListeLabel compagniesPanel;
    private ListeLabel garesPanel;
    
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

        this.proprietesPanel = new ListeLabel("Propriétés",propriétés);
        this.garesPanel = new ListeLabel("Gares",gares);
        
        //Ajout et agencement des différents composants
        this.add(nomJoueurLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(créerArgentPanel(argent));
        this.add(Box.createVerticalStrut(10));
        this.add(proprietesPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(garesPanel);
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
        this.argent = new JLabel(argent + " M$");
        this.argent.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.argent.setOpaque(false);
        this.argent.setFont(VuePlateau.textFont);

        // Création du Panel
        RoundedPanel argentPanel = new RoundedPanel(15,Color.white);
        argentPanel.setLayout(new BoxLayout(argentPanel, BoxLayout.Y_AXIS));
        argentPanel.setOpaque(false);
        //Ajout des éléments dans le panel
        argentPanel.add(titreArgentLabel);
        argentPanel.add(Box.createVerticalStrut(4));
        argentPanel.add(this.argent);

        return argentPanel;
    }

    public void updateArgent(int argent) {
        this.argent.setText("" + argent);
    }

    public void addPropriété(String propriété){
        this.proprietesPanel.addList(propriété);
    }

    public void removePropriété(String propriété){
        this.proprietesPanel.removeList(propriété);
    }
}