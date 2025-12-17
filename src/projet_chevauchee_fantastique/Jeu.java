/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_chevauchee_fantastique;

/**
 *
 * @author quent
 */
public class Jeu {
    private Damier damier;
    private Cavalier cavalier;

    public Jeu() {
        this.damier = new Damier();
        this.cavalier = new Cavalier(2, 2); 
    }

    public void tenterUnCoup(int xClique, int yClique) {
        
        if (cavalier.peutAllerEn(xClique, yClique)) {
            Case laCase = damier.getCase(xClique, yClique);
            
            if (laCase != null && laCase.estAllumee()) {
                cavalier.deplacer(xClique, yClique);
                laCase.eteindre();
                System.out.println("Coup réussi !");
                
                
            }else {
                System.out.println("Case déjà éteinte ou hors limite.");
            }
            
            
        } else {
            System.out.println("Ce n'est pas un mouvement en L !");
        }
    }
}
