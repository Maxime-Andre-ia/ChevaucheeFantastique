/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_chevauchee_fantastique;

/**
 *
 * @author maxim
 */
public class Projet_chevauchee_fantastique {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("--- Demarrage du jeu ---");
        
        Jeu monJeu = new Jeu();
        
        System.out.println("\nTest 1 : Tentative de triche (mouvement interdit)");
        monJeu.tenterUnCoup(2, 3);
        
        System.out.println("\nTest 2 : Vrai coup valide vers (0, 1)");
        monJeu.tenterUnCoup(0, 1);
        
        System.out.println("\nTest 3 : Sauter sur une case eteinte");
        monJeu.tenterUnCoup(2, 2);
    }
    
}
    
