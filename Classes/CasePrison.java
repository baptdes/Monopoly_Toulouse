package Classes;

public class CasePrison extends Case {

   private static final int AMENDE_PRISON = -50;  


    public CasePrison(String nom, int id, boolean estPrisonnier, int toursEnPrison) {
    	super(nom, id);
       
    }
   
    public int gettoursEnPrison(JoueurMonopoly joueur) {
        return joueur.gettoursprison();
    }
    
    
    public void entrerEnPrison(JoueurMonopoly joueur) {
        joueur.getenprison() = true;
        this.toursEnPrison = 0; 
        System.out.println("Vous êtes maintenant en prison pour les 3 prochaines tours.");
        joueur.setPosition(this.id); 
    }


    public boolean gererTourEnPrison(JoueurMonopoly joueur,int toursEnPrison) {
        toursEnPrison++;
        System.out.print("Tour " + toursEnPrison);
        
        if (toursEnPrison == 3) {
            this.estPrisonnier = false; 
            this.ajouterArgent(AMENDE_PRISON); 

        }

        System.out.println("Option disponible :");
        System.out.println("1. Lancer un double");
        System.out.println("2. Payer 50 euros pour s'echapper");
        
        int choix = Input.getIntegerInput("Choisissez une option : ");
        switch (choix) {

            case 1:
                System.out.println("Vous lancez les dés ...");
                int[] resultatDes = Des.lancer2des(); //methode pour lancer 2 des
                int totalDes = resultatDes[0] + resultatDes[1];
                System.out.println("Resultat du lancer : " + totalDes);
                if (totalDes % 2 == 0) {
                    System.out.println("Vous avez fait un double ! Vous sortez de prison.");
                    this.estPrisonnier = false;
                    this.setId(this.getId() + totalDes);
                } else {
                    System.out.println("Vous n'avez pas fait de double !");
                }
                break;

            case 2:
                System.out.println("Vous payez l'amende pour vous echapper de la prison.");
                this.ajouterArgent(AMENDE_PRISON);
                this.estPrisonnier = false;
                break;

            default:
                System.out.println("Option invalide !");

        }
        return this.estPrisonnier; //retourne true si le joueur est toujours en prison

 
}  

} 