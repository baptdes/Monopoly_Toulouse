package Classes;

import java.util.ArrayList;

public class CaseGare implements Case {
	
	private JoueurMonopoly proprietaire;
	private String nom;
	private int id;
	private int valeurachat;
	private ArrayList<Integer> Loyer;
	
	public CaseGare(String nom, int valeurachat, ArrayList<Integer> Loyer, int id){
		assert(nom != null && valeurachat > 0  && id > 0);
		this.nom = nom;
		this.valeurachat = valeurachat;
		this.Loyer = Loyer;
		this.id = id;
	}
	@Override
	public String getNom() {
		return this.nom;
	}
	@Override
	public int getId() {
		return this.id;
	}

	public int getprixaachat() {
		return this.valeurachat;
	}
	public JoueurMonopoly getprop() {
		return this.proprietaire;
	}
	
	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public boolean peutacheterterrain() {
		return (this.proprietaire == null);
	}
	
	 public ArrayList<Integer> getLoyer(){
		 return this.Loyer;
	 }
}
