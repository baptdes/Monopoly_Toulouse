package GestionMonopoly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interface_graphique.Panneau_joueur;

public class JoueurMonopoly{

	private String nom;
	private int id;
	private int position;
	private int argent;
	private int toursEnPrison;
	private boolean enPrison;
	private int carteSortiePrison;
	private ArrayList<CaseGare> gares;
	private ArrayList<CaseService> service;
	private Map<String, List<CaseTerrain>> proprietes;
	private Panneau_joueur affichage;

	
	public JoueurMonopoly(String nom, int id, int argent) {
		assert(nom != null && id > 0 && argent > 0);
		this.nom = nom;
		this.id = id;
		this.argent = argent;
		this.position = 0;
		this.toursEnPrison = 0;
		this.carteSortiePrison = 0;
		this.gares = new ArrayList<CaseGare>();
		this.service = new ArrayList<CaseService>();
		this.proprietes = new HashMap<String, List<CaseTerrain>>();
		this.enPrison = false;
		this.affichage = new Panneau_joueur(nom,argent);
	}

	// |||||||||||||||||||| Requêtes ||||||||||||||||||||||
	
	public String getNom() {
		return this.nom;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public int getSolde() {
		return this.argent;
	}

	public int getToursPrison() {
		return this.toursEnPrison;
	}

	public boolean estEnPrison() {
		return this.enPrison;
	}

	public Panneau_joueur getPanel(){
		return this.affichage;
	}

	public boolean possedeGroupe(CaseTerrain propriete) {
		boolean Groupecomplet = false;
		List<CaseTerrain> terrains = proprietes.get(propriete.getcouleur());
		int nbrprop = terrains.size(); //nbr de propriété pour la couleur, pour les marrons et bleu roi
		// il faut que ce nbr = 2 et pour les autres couleurs = 3 pour completer le groupe
		if ( propriete.getcouleur() == "Marron" || propriete.getcouleur() == "Bleu Roi") {
			if (nbrprop == 2) {
				Groupecomplet = true;
			}
		} else {
			if (nbrprop == 3) { 
				Groupecomplet = true;
			}
		}
		return Groupecomplet;
	}

	// |||||||||||||||||||| Commande ||||||||||||||||||||||

	public void setNom(String nom) {
		assert(nom != null);
		this.nom = nom;
	}
	
	public void setID( int id) {
		assert(id > 0);
		this.id = id;
	}
	
	public void crediter(int montant) {
		assert(montant >= 0);
		this.argent += montant;
	}
	
	public void debiter(int montant) {
		assert(montant >= 0);
		this.argent -= montant;
	}
	
	public void setPosition(int pos) {
		assert(pos >= 0);
		this.position = pos;
	}

	public void deplacer(int x) {
		assert(x >= 0);
		this.position = this.position + x;
	}


	// --------- Pour la prison ----------

	public void addToursEnPrison() {
		assert(this.toursEnPrison < 3);
		this.toursEnPrison++;
	}

	public void resetToursEnPrison() {
		this.toursEnPrison = 0;
	}

	public void addCarteSortiePrison(){
		this.carteSortiePrison++;
	}
	
	public void setEstEnPrison(boolean estPrisonnier) {
		this.enPrison = estPrisonnier;			
	}
	
	// --------- Pour les cases possédées ----------

	public void addPropriete(CaseTerrain propriete) {
		assert(propriete != null && this.argent > propriete.getprixachat());
		if (propriete.peutacheterterrain()) {
			if (proprietes.containsKey(propriete.getcouleur())) {
				List<CaseTerrain> terrains = proprietes.get(propriete.getcouleur());
				terrains.add(propriete);
				affichage.addPropriété(propriete.getNom());
				proprietes.replace(propriete.getcouleur(),terrains);
			} else {
				List<CaseTerrain> terrains = new ArrayList<CaseTerrain>();
				terrains.add(propriete);
				affichage.addPropriété(propriete.getNom());
				proprietes.put(propriete.getcouleur(), terrains);
			}				
			this.argent -= propriete.getprixachat();				
		}else {
			System.out.println("Ce propriete appartient à un autre joueur");
		}
	}
	
	public void addMaisonPropriete(CaseTerrain propriete) {		
		if (possedeGroupe(propriete) == true) {
			propriete.ajouterMaison();
			this.argent -= propriete.getprixmaison();
		} else {
			System.out.println("Veuillez completez le groupe de la propriété avant de construire une maison");
		}			
	}
	
	public void vendrePropriete(CaseTerrain propriete) {
		assert(propriete != null && propriete.getnbrmaison() == 0); 
		// Tout d'abord on vérifie que ce propriete nous appartient
		boolean Nousappartient = false;
		for (List<CaseTerrain> liste : proprietes.values()) {
			if (liste.contains(propriete)) {
				Nousappartient = true;
			}
		}
		if (Nousappartient = true) {
			List<CaseTerrain> terrains = proprietes.get(propriete.getcouleur());
			terrains.remove(propriete);  // on supprime ce propriete de la liste associé à sa couleur
			proprietes.replace(propriete.getcouleur(),terrains);
			this.argent += propriete.getprixachat(); // vendre à la banque avec le même prix d'achat
		} else {
			System.out.println("Ce propriete ne vous appartient pas");
		}
	}
	
	public void vendreMaisonPropriete(CaseTerrain propriete) {
		propriete.vendremaison();
		this.argent += propriete.getprixmaison()/2; //Vendre une maison à 50% du prix d'achat.
	}
	
	public void addGare(CaseGare gare) {
		assert(gare != null && this.argent > gare.getprixachat());
		if (gare.peutachetergare()) {
			gares.add(gare);
			affichage.addGare(gare.getNom());
			this.argent -= gare.getprixachat();
		}else {
			System.out.println("Cette gare appartient à un autre joueur");
		}
	}
	
	public void vendreGare(CaseGare gare) {
		assert(gare != null && gares.contains(gare)); 
		gares.remove(gare); 
		this.argent += gare.getprixachat(); // vendre à la banque avec le même prix d'achat
	}
	
	/** public void vendreService(CaseService service) {
		assert(service != null && service.contains(service)); 
		service.remove(service); 
		this.argent += service.getprixachat(); // vendre à la banque avec le même prix d'achat
	}
	
	public void addService(CaseService serv) {
		assert(serv != null && this.argent > serv.getprixachat());
		if (serv.peutacheterservice()) {
			service.add(serv);
			this.argent -= serv.getprixachat();
		}else {
			System.out.println("Cette compagnie de distribution appartient à un autre joueur");
	}
	*/

	// ||||||||||||||||||| ANNEXE ||||||||||||||||||||||
	
	/**
	public void payerloyergare(CaseGare gare) {
		assert(this.position == gare.getId() &&  gare.getprop() != null && gare.getprop().getNom() != this.nom );
		int[] loyer = gare.getLoyer();
		if (gares.size() == 1) {
			this.argent -= loyer[0];
		} else if ( gares.size() == 2) {
			this.argent -= loyer[1];
		} else if ( gares.size() == 3) {
			this.argent -= loyer[2];
		} else if ( gares.size() == 4) {
			this.argent -= loyer[3];
		}
	}

		public void payerloyer(CaseTerrain propriete) {
		assert(this.position == propriete.getId() &&  propriete.getprop() != null && propriete.getprop().getNom() != this.nom );
		int[] loyer = propriete.getLoyer();
		if (propriete.getnbrmaison() == 0 && !possedeGroupe(propriete)) { //loyer de la prop seule
			this.argent -= loyer[0];
		}else if (propriete.getnbrmaison() == 0 && possedeGroupe(propriete)) { //loyer pour le groupe complet
			this.argent -= loyer[1];
		}else if (propriete.getnbrmaison() == 1) { //loyer pour 1 maison
			this.argent -= loyer[2];
		}else if (propriete.getnbrmaison() == 2) {//loyer pour 2 maison
			this.argent -= loyer[3];
		}else if (propriete.getnbrmaison() == 3) {//loyer pour 3 maison
			this.argent -= loyer[4];
		}else if (propriete.getnbrmaison() == 4) {//loyer pour 4 maison
			this.argent -= loyer[5];
		}else if (propriete.getnbrmaison() == 5) { // 5 maisons == 1 hôtel 
			this.argent -= loyer[6];
		}		
	}
	}*/
	
}
