package Interface_graphique;

import java.awt.*;
import javax.swing.*;

public class Pion extends JLabel {

    private int position;

    public Pion(int position,int nbPionCase){
        super();
        this.position = position;

        // Image du pion à récupéré
        ImageIcon pngPion = new ImageIcon("Pion_Monopoly_1.png");
        Image scaledPion = pngPion.getImage().getScaledInstance(VuePlateau.lengthPion, VuePlateau.lengthPion, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaledPion));

        // Donner la bonne position au pion
        setPositionPion(position, nbPionCase);
    }

    public void setPositionPion(int nbCase, int nbPionCase){
        int x = 0; int y = 0;
        if (nbCase == 0) { //Case de départ
            x = 735; y = 735;
        }
        else if (nbCase <= 9) { //Ligne du bas
            x = 660 - 69 * (nbCase - 1); y = 750;
        }
        else if (nbCase == 10){ //Visite simple
            x = 20; y = 780;
        }
        else if (nbCase <= 19) { //Ligne de gauche
            x = 10 ; y = 660 - 69 * (nbCase - 11);
        }
        else if (nbCase == 20) { //Parking gratuit
            x = 15; y = 15;
        }
        else if (nbCase <= 29) { //Ligne du haut
            x = 105 + 69 * (nbCase - 21);; y = 10;
        }
        else if (nbCase == 30) { //Direction prison
            x = 750; y = 10;
        }
        else if (nbCase <= 39) { //Ligne de droite
            x = 760; y = 110 + + 69 * (nbCase - 31);
        }
        else if (nbCase == 40) { //Case prison
            x = 35; y = 730;
        }
        else{
            throw new CaseInvalideException();
        }

        // Si il y a chevauchement
        if (nbPionCase > 0) {
            x = x - 10 * nbPionCase;
            y = y - 10 * nbPionCase;
        }

        // Obtenir les coordonées de la nouvelle case
        this.setBounds(x, y, VuePlateau.lengthPion, VuePlateau.lengthPion);
    }

    public int getPosition(){
        return position;
    }
}
