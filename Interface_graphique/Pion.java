package Interface_graphique;

import java.awt.*;
import javax.swing.*;

public class Pion extends JLabel {

    final ImageIcon pngPion = new ImageIcon("Pion_Monopoly_1.png");
    final int lengthPionAbsolute = 70;
    private int position;
    private double scaleFactor;
    private int lengthPion = 70;

    public Pion(int position,int nbPionsCase, int plateauWidth){
        super();
        this.position = position;
        this.scaleFactor = (double) plateauWidth / 850.0;

        // Image du pion à récupéré
        Image scaledPion = pngPion.getImage().getScaledInstance(lengthPion, lengthPion, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaledPion));

        int[] cases_occupées = new int[41];
        cases_occupées[position] = nbPionsCase;
        // Donner la bonne position au pion
        setPositionPion(position, cases_occupées);
    }

    static private int[] getPositionPionAbsolute850(int nbCase, int[] cases_occupées){
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
        if (cases_occupées[nbCase] > 0) {
            x = x - 10 * cases_occupées[nbCase];
            y = y - 10 * cases_occupées[nbCase];
        }

        int[] res = {x,y};
        return res;
    }

    public void setPositionPion(int nbCase, int[] cases_occupées){
        int[] pos850 = getPositionPionAbsolute850(nbCase, cases_occupées);
        int x = (int) (pos850[0] * scaleFactor);
        int y = (int) (pos850[1] * scaleFactor);
        this.position = nbCase;
        this.setBounds(x, y, lengthPion, lengthPion);
    }

    public void updatePlateauWidth(int plateauWidth, int[] cases_occupées){
        this.scaleFactor = (double) plateauWidth / 850.0;
        this.lengthPion = (int) (lengthPionAbsolute * scaleFactor);
        Image scaledPion = pngPion.getImage().getScaledInstance(lengthPion, lengthPion, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaledPion));
        setPositionPion(position, cases_occupées);
    }

    public int getPosition(){
        return position;
    }
}
