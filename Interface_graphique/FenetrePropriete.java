package Interface_graphique.FenetreCases;

import javax.swing.*;
import java.awt.*;
import GestionMonopoly.*;
import GestionMonopoly.Cases.Propriete;
import Interface_graphique.ModernButton;

public class FenetrePropriete extends JFrame {
    private JoueurMonopoly joueur;
    private Propriete caseTerrain;

    public FenetrePropriete(JoueurMonopoly joueur, Propriete caseTerrain) {
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
                "Voulez-vous acheter " + caseTerrain.getNom() + " pour " + caseTerrain.getValeurAchat() + " ?");
        label.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.CENTER);

        // Utilisation de ModernButton
        JButton buyButton = new ModernButton("Acheter", Color.WHITE, Color.BLACK);
        JButton cancelButton = new ModernButton("Annuler", Color.WHITE, Color.BLACK);

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
        if (caseTerrain.estAchetable()) {
            joueur.acheterPropriete(caseTerrain);
            JOptionPane.showMessageDialog(this, "Vous avez acheté " + caseTerrain.getNom(), "Achat réussi",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Achat impossible. Fonds insuffisants ou propriété déjà achetée.",
                    "Achat échoué", JOptionPane.ERROR_MESSAGE);
        }
        dispose(); // Ferme la fenêtre après l'interaction
    }
}
