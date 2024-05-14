package GestionMonopoly.Cases;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GroupeProprietes {

    private List<Propriete> proprietes;
    private Color couleur;

    GroupeProprietes(Color couleur){
        this.proprietes = new ArrayList<>();
        this.couleur = couleur;
    }

    public void addPropriete(Propriete propriete){
        this.proprietes.add(propriete);
    }

    public int getNbProprietes(){
        return this.proprietes.size();
    }

    public Color getCouleur(){
        return this.couleur;
    }
}
