package Interface_graphique;

import javax.swing.*;
import javax.swing.border.Border;
import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interface_prison extends JFrame {
	public void action(JoueurMonopoly joueur, Plateau plateau) {
        joueur.addToursEnPrison();
        System.out.print("Tour " + joueur.getToursPrison()+ ": ");

        if (joueur.getToursPrison() == 3) {
            sortirJoueurPrison(joueur, plateau);
            return;
        }

        JFrame frame = new JFrame("Prison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(0, 1));

        JLabel label = new JLabel(joueur .getNom() + ",vous êtes désormais en Prison.\n");
        frame.add(label);
        JLabel label1 = new JLabel("Options disponibles :");
        frame.add(label1);
        
        ModernButton lancerDoubleButton = new ModernButton("1. Lancer un double", Color.green, Color.white);
        frame.add(lancerDoubleButton);
        
        ModernButton payerAmendeButton = new ModernButton("2. Payer 50$ pour s'échapper",Color.green, Color.white );
        frame.add(payerAmendeButton);
        
        ModernButton utiliserCarteButton = null;
        if (joueur.possedeCarteSortiePrison()) {
            utiliserCarteButton = new ModernButton("3. Utiliser carte sortie de prison",Color.green, Color.white );
            frame.add(utiliserCarteButton);
        }

        lancerDoubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Vous lancez les dés ...");
                plateau.getDes().lancer();
                System.out.println("Résultat du lancer : " + plateau.getDes().getResultat());
                if (plateau.getDes().estDouble()) {
                    System.out.println(joueur.getNom() + " a fait un double !");
                    sortirJoueurPrison(joueur, plateau);
                } else {
                    System.out.println(joueur.getNom() + " n'a pas fait de double !");
                }
                frame.dispose();
            }
        });

        payerAmendeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(joueur.getNom() + " paye l'amende pour s'échapper de la prison.");
                joueur.debiter(50); // Supposons que l'amende est de 50$
                sortirJoueurPrison(joueur, plateau);
                frame.dispose();
            }
        });

        if (utiliserCarteButton != null) {
            utiliserCarteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(joueur.getNom() + " utilise une carte sortie de prison.");
                    joueur.removeCarteSortiePrison();
                    sortirJoueurPrison(joueur, plateau);
                    frame.dispose();
                }
            });
        }

        frame.setVisible(true);
    }

	 private void sortirJoueurPrison(JoueurMonopoly joueur, Plateau plateau) {
	        System.out.println(joueur.getNom() + " sort de prison.");
	        joueur.setEstEnPrison(false);
	        joueur.resetToursEnPrison();
	        plateau.deplacerJoueur(joueur, Plateau.ID_CASE_VISITE_SIMPLE);
	    }
	 public static void main(String[] args) {
	        JoueurMonopoly joueur = new JoueurMonopoly("Hamza", 5, 1500, Color.red);
	        joueur.addCarteSortiePrison();
	        ArrayList<JoueurMonopoly> joueurs = new ArrayList<JoueurMonopoly>();
	        joueurs.add(joueur);
	        Plateau plateau = new Plateau(joueurs);
	        new Interface_prison().action(joueur, plateau);
	    }
}

