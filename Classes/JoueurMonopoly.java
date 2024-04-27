package Classes;
import java.util.ArrayList;

public class JoueurMonopoly{
	
		private String nom;
		private int id;
		private int position;
		private int argent;
		private int toursEnPrison;
		private int nombreGaresPossedees;
		private int nombreServicesPossedees;
		private boolean estBanqueroute;
		private boolean estPrison;
		private boolean possedeCarteSortiePrison;
		private ArrayList<CaseTerrain> terrains; 
		private ArrayList<String> couleurs;
		private int nbrmarron; // nbr de propriété de la même couleur
		private int nbrturquoise;
		private int nbrviolet;
		private int nbrorange;
		private int nbrrouge;
		private int nbrjaune;
		private int nbrvert;
		private int nbrbleu;
		
		public JoueurMonopoly(String nom, int id, int argent) {
			assert(nom != null && id > 0 && argent > 0);
			this.nom = nom;
			this.id = id;
			this.argent = argent;
		}
		
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
		
		public void setNom(String nom) {
			assert(nom != null);
			this.nom = nom;
		}
		
		public void setID( int id) {
			assert(id > 0);
			this.id = id;
		}
		
		public void setArgent( int argent) {
			assert(argent > 0);
			this.argent = argent;
		}
		
		// La position peut être égale à 0, ( case de départ ) ?
		public void setPosition(int pos) {
			assert(pos >= 0);
			this.position = pos;
		}
		
		public void setToursEnPrison(int toursEnPrison) {
			assert(toursEnPrison >= 0);
			this.toursEnPrison = toursEnPrison;
		}
		
		public void setEstPrison(boolean prison) {
			this.estPrison = prison;			
		}
		
		public void setCarteSortiePrison(boolean b) {
			this.possedeCarteSortiePrison = b;
		}
		
		public void setEstBanqueroute(boolean banqueroute) {
			this.estBanqueroute = banqueroute;
		}
		
		public void setNbGares(int nb) {
			assert( nb >= 0);
			this.nombreGaresPossedees = nb;
		}
		
		public void setNbServices(int nb) {
			assert( nb >= 0);
			this.nombreServicesPossedees = nb;
		}
		public void actualisercouleurachat(String couleur) {
			if ("Marron".equals(couleur)) {
				this.nbrmarron += 1;
			} else if ("Turquoise".equals(couleur)) {
				this.nbrturquoise += 1;
			}else if ("Violet".equals(couleur)) {
				this.nbrviolet += 1;
			}else if ("Orange".equals(couleur)) {
				this.nbrorange += 1;
			}else if ("Rouge".equals(couleur)) {
				this.nbrrouge += 1;
			}else if ("Jaune".equals(couleur)) {
				this.nbrjaune += 1;
			}else if ("Vert".equals(couleur)) {
				this.nbrvert += 1;
			}else if ("Bleu".equals(couleur)) {
				this.nbrbleu += 1;
			}
		}
		
		public void actualisercouleurvente(String couleur) {
			if ("Marron".equals(couleur)) {
				this.nbrmarron -= 1;
			} else if ("Turquoise".equals(couleur)) {
				this.nbrturquoise -= 1;
			}else if ("Violet".equals(couleur)) {
				this.nbrviolet -= 1;
			}else if ("Orange".equals(couleur)) {
				this.nbrorange -= 1;
			}else if ("Rouge".equals(couleur)) {
				this.nbrrouge -= 1;
			}else if ("Jaune".equals(couleur)) {
				this.nbrjaune -= 1;
			}else if ("Vert".equals(couleur)) {
				this.nbrvert -= 1;
			}else if ("Bleu".equals(couleur)) {
				this.nbrbleu -= 1;
			}
		}
		public void ajouterTerrain(CaseTerrain terrain) {
			assert(terrain != null && this.argent > terrain.getprixachat());
			if (terrain.peutacheterterrain()) {
				terrains.add(terrain);
				actualisercouleurachat(terrain.getcouleur());
				this.argent -= terrain.getprixachat();
			}else {
				System.out.println("Ce terrain appartient à un autre joueur");
			}
		}
		public boolean Verifpropcomplete(String couleur) {
			if ("Marron".equals(couleur)) {
				return this.nbrmarron == 2;
			} else if ("Turquoise".equals(couleur)) {
				return this.nbrturquoise == 3;
			}else if ("Violet".equals(couleur)) {
				return this.nbrviolet == 3;
			}else if ("Orange".equals(couleur)) {
				return this.nbrorange == 3;
			}else if ("Rouge".equals(couleur)) {
				return this.nbrrouge == 3;
			}else if ("Jaune".equals(couleur)) {
				return this.nbrjaune == 3;
			}else if ("Vert".equals(couleur)) {
				return this.nbrvert == 3;
			}else if ("Bleu".equals(couleur)) {
				return this.nbrbleu == 2;
			}
			return false;
		}
		public void ajouterMaisonTerrain(CaseTerrain terrain) {
			if (Verifpropcomplete(terrain.getcouleur())) {
				terrain.ajouterMaison();
				this.argent -= terrain.getprixmaison();
			} else {
				System.out.println("Veuillez completez le groupe de la propriété avant de construire une maison");
			}
			
		}
		
		public void vendreTerrain(CaseTerrain terrain) {
		    assert(terrain != null && terrains.contains(terrain) && terrain.getnbrmaison() == 0); 
		    terrains.remove(terrain); 
		    actualisercouleurvente(terrain.getcouleur());
		    this.argent += terrain.getprixachat(); // vendre à la banque avec le même prix d'achat
		}
		
		public void VendreMaisonTerrain(CaseTerrain terrain) {
			terrain.vendremaison();
			this.argent += terrain.getprixmaison()/2; //Vendre une maison à 50% du prix d'achat.
		}
		
		public ArrayList<String> getListeStringTerrains() {
			ArrayList<String> nomsTerrains = new ArrayList<>();
			for (CaseTerrain unTerrain : terrains) {
				nomsTerrains.add(unTerrain.getNom()); 													  
			}
			return nomsTerrains;
		}
		
		public ArrayList<CaseTerrain> getTerrains() {
			return this.terrains;
		}
		
		public ArrayList<String> getListeCouleur() {
			return this.couleurs;
		}
		
		public void ajouterArgent(int montant) {
			assert(montant >= 0);
			this.argent += montant;
		}
		
		public void retirerArgent(int montant) {
			assert(montant >= 0);
			this.argent -= montant;
		}
		
		public void payerloyer(CaseTerrain terrain) {
			assert(this.position == terrain.getId() && ( terrain.getprop() != null && terrains.contains(terrain)));
			ArrayList<Integer> Loyer = terrain.getLoyer();
			if (terrain.getnbrmaison() == 0 && !terrain.getprop().Verifpropcomplete(terrain.getcouleur())) {
				this.argent -= Loyer.get(0);
			}else if (terrain.getnbrmaison() == 0 && !terrain.getprop().Verifpropcomplete(terrain.getcouleur())) {
				this.argent -= Loyer.get(1);
			}else if (terrain.getnbrmaison() == 1) {
				this.argent -= Loyer.get(2);
			}else if (terrain.getnbrmaison() == 2) {
				this.argent -= Loyer.get(3);
			}else if (terrain.getnbrmaison() == 3) {
				this.argent -= Loyer.get(4);
			}else if (terrain.getnbrmaison() == 4) {
				this.argent -= Loyer.get(5);
			}else if (terrain.getnbrmaison() == 5) { // 5 maisons == 1 hôtel 
				this.argent -= Loyer.get(6);
			}		
		}
		
}
