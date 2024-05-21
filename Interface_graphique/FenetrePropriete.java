package Interface_graphique;

import javax.swing.*;
import java.awt.*;
import GestionMonopoly.*;

public class FenetrePropriete extends JFrame {
    private JoueurMonopoly joueur;
    private CaseTerrain caseTerrain;

    public FenetrePropriete(JoueurMonopoly joueur, CaseTerrain caseTerrain) {
        super("Propriété - " + caseTerrain.getNom());
        this.joueur = joueur;
        this.caseTerrain = caseTerrain;

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setupComponents();
    }

    private void setupComponents() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JLabel label = new JLabel(
                "Voulez-vous acheter " + caseTerrain.getNom() + " pour " + caseTerrain.getprixachat() + " ?");
        JButton buyButton = new JButton("Acheter");
        JButton cancelButton = new JButton("Annuler");

        buyButton.addActionListener(e -> {
            acheterPropriete();
        });
        cancelButton.addActionListener(e -> dispose());

        panel.add(label);
        panel.add(buyButton);
        panel.add(cancelButton);
        add(panel);
    }

    private void acheterPropriete() {
        if (joueur.peutAcheter(caseTerrain)) {
            joueur.ajouterTerrain(caseTerrain);
            JOptionPane.showMessageDialog(this, "Vous avez acheté " + caseTerrain.getNom(), "Achat réussi",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Achat impossible. Fonds insuffisants ou propriété déjà achetée.",
                    "Achat échoué", JOptionPane.ERROR_MESSAGE);
        }
        dispose(); // Ferme la fenêtre après l'interaction
    }
}
