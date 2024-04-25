package Classes;

import java.util.ArrayList;

public class CaseTerrain implements Case {
	
	private JoueurMonopoly proprietaire;
	private String nom;
	private String couleur;
	private int id;
	private int valeurachat;
	private ArrayList<Integer> Loyer;
	private int prixmaison;
	private int nbrmaison;
	
	
	public CaseTerrain(String nom, int valeurachat, ArrayList<Integer> Loyer, String couleur, int prixMaison, int id){
		assert(nom != null && valeurachat > 0 && couleur != null && prixMaison > 0 && id > 0);
		this.nom = nom;
		this.valeurachat = valeurachat;
		this.Loyer = Loyer;
		this.couleur = couleur;
		this.prixmaison = prixMaison;
		this.id = id;
		this.nbrmaison = 0;
	}
	@Override
	public String getNom() {
		return this.nom;
	}
	@Override
	public int getId() {
		return this.id;
	}
	public String getcouleur() {
		return this.couleur;
	}
	public int getnbrmaison() {
		return this.nbrmaison;
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
	
	//public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
	// Je garde cette méthode en commentaire en attendant de voir ce qu'elle peut faire..	
	//}
	public boolean peutacheterterrain() {
		return (this.proprietaire == null);
	}
	public boolean peutMettreMaison() {
		return (this.nbrmaison < 5); 
		// ici je vérifie que la possibilité d'ajouter une maison en termes
		// de capacité maximale de maison ( 5 maisons max = 4 maisons + 1 maison = 5 hotel, je garde que 
		//le terme maison, par contre faut vérifier aussi une autre condition qui est d'avoir tout les
		//terrains de la même couleur avant de pouvoir ajouter une maison, je vais réfléchir après comment
	}
	public void ajouterMaison() {
		if (peutMettreMaison()) {
			this.nbrmaison++;
		} else {
			System.out.println("Nombre maximum de maisons atteint.");
		}
	}
	 public ArrayList<Integer> getLoyer(){
		 return this.Loyer;
	 }
}
