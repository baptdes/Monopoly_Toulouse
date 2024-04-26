package Interface_graphique;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class ListeLabel extends JPanel {

    private ArrayList<JLabel> liste = new ArrayList<JLabel>();

    public ListeLabel(String title, JLabel[] liste) {
        super();

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Titre de la liste
        JLabel titre = new JLabel(title);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setFont(VuePlateau.titleFont);
        this.add(titre);

        // Espace entre titre et liste
        this.add(Box.createVerticalStrut(4));

        //Liste des propriétés
        for (JLabel label : liste) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(VuePlateau.textFont);
            this.liste.add(label);
            this.add(label);
        }
    }

    public ListeLabel(String title, String[] stringListe) {     
        this(title,toLabels(stringListe));
    }

    private static JLabel[] toLabels(String[] stringListe) {
        JLabel[] labels = new JLabel[stringListe.length];
        for (int i = 0; i < stringListe.length; i++) {
            labels[i] = new JLabel(stringListe[i]);
        }
        return labels;
    }

    public void removeList(String nom) {
        for (JLabel label : this.liste) {
                if (label.getText().equals(nom)) {
                    this.remove(label);
                    this.liste.remove(label);
                    break;
                }
        }
    }

    public void addList(String nom) {
        JLabel label = new JLabel(nom);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(VuePlateau.textFont);
        this.liste.add(label);
        this.add(label);
        this.revalidate();
        this.repaint();
    }
}
