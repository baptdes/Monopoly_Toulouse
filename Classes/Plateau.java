package Classes;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private List<Case> cases;

    public Plateau() {
        this.cases = new ArrayList<>();
        creerCases();
        lierCases();
    }
    private ArrayList<Integer> Loyer(int a, int b, int c, int d, int e, int f, int g) {
    	 ArrayList<Integer> loyers = new ArrayList<>();
    	 loyers.add(a);
    	 loyers.add(b);
    	 loyers.add(c);
    	 loyers.add(d);
    	 loyers.add(e);
    	 loyers.add(f);
    	 loyers.add(g);
    	 return loyers;   	 
    }
    
    private void creerCases() {
    	//Case de départ
        cases.add(new Casedepart("START", 1));
        cases.add(new CaseTerrain("Rue Bayard", 60 , Loyer(2,4,10,30,90,160,250), "Marron", 50, 2));
        cases.add(new CaseCommunaute("Case Communauté", 3));
        cases.add(new CaseTerrain("Rue Matabiau", 60, Loyer(4,8,20,60,180,320,450), "Marron", 50, 4));
        cases.add(new CaseImpots("IMPOTS SUR LE REVENU", 5, 200));
        cases.add(new CaseGare("Gare Matabiau", 200, Loyer(25,50,100,200,0,0,0), 6));
        cases.add(new CaseTerrain("Rue De La Colombette", 100, Loyer(6,12,30,90,270,400,550), "Bleu Ciel", 50, 7));
        cases.add(new CaseChance("Case Chance", 8));
        cases.add(new CaseTerrain("Rue Saint-Rome", 100, Loyer(6,12,30,90,270,400,550), "Bleu Ciel", 50, 9));
        cases.add(new CaseTerrain("Rue des Filatiers", 120, Loyer(8,16,40,100,300,450,600), "Bleu Ciel", 50, 10));
        //cases.add(new CasePrison("Visite Simple", 0,11));
        cases.add(new CaseTerrain("Place Esquirol", 140, Loyer(10,20,50,150,450,625,750), "Violet", 100, 12));
        //cases.add(new CaseService("Compagnie de distribution d'électricité", 150,13));
        cases.add(new CaseTerrain("Avenue Honoré Serres", 140, Loyer(10,20,50,150,450,625,750), "Violet", 100,14));
        cases.add(new CaseTerrain("Place Wilson", 160, Loyer(12,24,60,180,500,700,900), "Violet", 100,15));
        cases.add(new CaseGare("Réseau de métro", 200, Loyer(25,50,100,200,0,0,0),16));
        cases.add(new CaseTerrain("Rue Gambetta", 180, Loyer(14,28,70,200,550,750,950), "Orange", 100,17));
        cases.add(new CaseCommunaute("Case Communauté", 18));
        cases.add(new CaseTerrain("Rue de la République", 180, Loyer(14,28,70,200,550,750,950), "Orange", 100,19));
        cases.add(new CaseTerrain("Boulevard Lazare-Carnot", 200, Loyer(16,32,80,220,600,800,1000), "Orange", 100,20));
        //Case Parking
        cases.add(new CaseImpots("Parking Gratuit", 21, 0));
        cases.add(new CaseTerrain("Quai de la Daurade", 220, Loyer(18,36,90,250,700,875,1050), "Rouge", 150,22));
        cases.add(new CaseChance("Case Chance",23));
        cases.add(new CaseTerrain("Place Victor-Hugo", 220, Loyer(18,36,90,250,700,875,1050), "Rouge", 150,24));
        cases.add(new CaseTerrain("Place Occitanie", 240, Loyer(20,40,100,300,750,925,1100), "Rouge", 150,25));
        cases.add(new CaseGare("Gare St-Agne", 200, Loyer(25,50,100,200,0,0,0),26));
        cases.add(new CaseTerrain("Allé Jean-Jaurès", 260, Loyer(22,44,110,330,800,975,1150), "Jaune", 150,27));
        cases.add(new CaseTerrain("Rue du Taur", 260, Loyer(22,44,110,330,800,975,1150), "Jaune", 150, 28));
        // cases.add(new CaseCompagnie("Compagnie de distribution des eaux", 150,29))));
        cases.add(new CaseTerrain("Place Saint-Georges", 280, Loyer(24,48,120,360,850,1025,1200), "Jaune", 150,30));
        //cases.add(new CasePrison("Direction La Prison", 0, false, false, 31));
        cases.add(new CaseTerrain("Rue de Metz", 300, Loyer(26,52,130,390,900,1100,1275), "Vert", 200,32));
        cases.add(new CaseTerrain("Quai de Tounis", 300, Loyer(26,52,130,390,900,1100,1275), "Vert", 200,33));
        cases.add(new CaseCommunaute("Case Communauté", 34));
        cases.add(new CaseTerrain("Rue Croix Baragnon", 320, Loyer(28,56,150,450,1000,1200,1400), "Vert", 200,35));
        cases.add(new CaseGare("Téléo téléphérique", 200,Loyer(25,50,100,200,0,0,0) ,36));
        cases.add(new CaseChance("Case Chance",37)));
        cases.add(new CaseTerrain("Rue d'Alsace-Lorraine", 350, Loyer(35,70,175,500,1100,1300,1500), "Bleu Roi", 200,38));
        cases.add(new CaseImpots("TAXE DE LUXE", 39, 100));
        cases.add(new CaseTerrain("Rue du Capitole", 400, Loyer(50,100,200,600,1400,1700,2000), "Bleu Roi", 200,40));

    }

    private void lierCases() {
    }

    public Case getCase(int position) {
        return cases.get(position % cases.size());
    }

}
