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
		
		public void ajouterTerrain(CaseTerrain terrain) {
			assert(terrain != null);
			if (terrain.peutacheterterrain()) {
			terrains.add(terrain);
			}else {
				System.out.println("Ce terrain appartient à un autre joueur");
			}
		}
		
		public void ajouterMaisonTerrain(CaseTerrain terrain) {
			terrain.ajouterMaison();
		}
		
		public ArrayList<String> getListeStringTerrains() {
			ArrayList<String> nomsTerrains = new ArrayList<>();
			for (CaseTerrain unTerrain : terrains) {
				nomsTerrains.add(unTerrain.getNom()); // Une méthode getNom doit être
													  //écrite dans Case.
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
		
}
