package Classes;

import java.util.ArrayList;

public class CaseGare extends Case {
	
	private JoueurMonopoly proprietaire;
	private int valeurachat;
	private ArrayList<Integer> Loyer;
	
	public CaseGare(String nom, int valeurachat, ArrayList<Integer> Loyer, int id){
		super(nom, id);
		assert(valeurachat > 0  && id > 0);
		this.valeurachat = valeurachat;
		this.Loyer = Loyer;
	}
	

	public int getprixachat() {
		return this.valeurachat;
	}
	public JoueurMonopoly getprop() {
		return this.proprietaire;
	}
	
	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public boolean peutachetergare() {
		return (this.proprietaire == null);
	}
	
	 public ArrayList<Integer> getLoyer(){
		 return this.Loyer;
	 }
	 
	 public void action() {
			
		}
}
