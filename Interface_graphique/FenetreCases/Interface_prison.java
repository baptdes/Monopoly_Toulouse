package Interface_graphique.FenetreCases;

import javax.swing.*;
import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.Utilitaires.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface_prison extends JFrame {

	public Interface_prison(JoueurMonopoly joueur, Plateau plateau, int amende) {
        super("Prison");
        joueur.addToursEnPrison();
        System.out.print("Tour " + joueur.getToursPrison()+ ": ");

        if (joueur.getToursPrison() == 3) {
            sortirJoueurPrison(joueur, plateau);
            return;
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLayout(new GridLayout(0, 1));

        JLabel label = new JLabel(joueur .getNom() + ",vous êtes désormais en Prison.\n");
        this.add(label);
        JLabel label1 = new JLabel("Options disponibles :");
        this.add(label1);
        
        ModernButton lancerDoubleButton = new ModernButton("1. Lancer un double", Color.green, Color.white);
        this.add(lancerDoubleButton);
        
        ModernButton payerAmendeButton = new ModernButton("2. Payer 50$ pour s'échapper",Color.green, Color.white );
        this.add(payerAmendeButton);
        
        ModernButton utiliserCarteButton = null;
        if (joueur.possedeCarteSortiePrison()) {
            utiliserCarteButton = new ModernButton("3. Utiliser carte sortie de prison",Color.green, Color.white );
            this.add(utiliserCarteButton);
        }

        lancerDoubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lancerDes(plateau, joueur);
            }
        });

        payerAmendeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payerAmende(plateau,joueur,amende);
            }
        });

        if (utiliserCarteButton != null) {
            utiliserCarteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cartePrison(plateau, joueur);
                }
            });
        }
    }

    private void payerAmende(Plateau plateau, JoueurMonopoly joueur, int amende){
        JOptionPane.showMessageDialog(this, joueur.getNom() + " paye l'amende pour s'échapper de la prison.",
            "Résultat dés",
            JOptionPane.INFORMATION_MESSAGE);
        joueur.debiter(amende); // Supposons que l'amende est de 50$
        sortirJoueurPrison(joueur, plateau);
        this.dispose();
    }

    private void cartePrison(Plateau plateau, JoueurMonopoly joueur){
        JOptionPane.showMessageDialog(this, joueur.getNom() + " utilise une carte sortie de prison.",
            "Utilisation carte prison",
            JOptionPane.INFORMATION_MESSAGE);
        joueur.removeCarteSortiePrison();
        sortirJoueurPrison(joueur, plateau);
        this.dispose();
    }

    private void lancerDes(Plateau plateau, JoueurMonopoly joueur) {
        System.out.println("Vous lancez les dés ...");
        plateau.getDes().lancer();
        JOptionPane.showMessageDialog(this, "Vous avez fait " + plateau.getDes().getDe1() + " et " +  plateau.getDes().getDe2(),
            "Résultat dés",
            JOptionPane.INFORMATION_MESSAGE);
        plateau.attendre(1000);
        if (plateau.getDes().estDouble()) {
            JOptionPane.showMessageDialog(this, "Bien joué vous a fait un double !",
            "Bien joué !",
            JOptionPane.INFORMATION_MESSAGE);
            sortirJoueurPrison(joueur, plateau);
        } else {
            JOptionPane.showMessageDialog(this, joueur.getNom() + " n'a pas fait un double !",
            "Dommage",
            JOptionPane.INFORMATION_MESSAGE);
        }
        this.dispose();
    }

	private void sortirJoueurPrison(JoueurMonopoly joueur, Plateau plateau) {
	        System.out.println(joueur.getNom() + " sort de prison.");
	        joueur.setEstEnPrison(false);
	        joueur.resetToursEnPrison();
	        plateau.setPositionJoueur(joueur, Plateau.ID_CASE_VISITE_SIMPLE);
	}
}

