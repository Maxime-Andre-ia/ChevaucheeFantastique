/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_chevauchee_fantastique;

import java.util.Scanner;

/**
 *
 * @author mathi
 */
public class MainConsol {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Damier damier = new Damier(5,1);
            Cavalier cavalier = new Cavalier(2, 2);
            
            
            if (damier.getCase(cavalier.getX(), cavalier.getY()).estAllumee()) {
                damier.getCase(cavalier.getX(), cavalier.getY()).eteindre();
            }
            
            boolean jeuEnCours = true;
            
            while (jeuEnCours) {
                afficherDamier(damier, cavalier);
                
                
                System.out.println("Entrez vos coordonnées de déplacement (ligne colonne) :");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                scanner.nextLine();
                
                
                if (!cavalier.peutAllerEn(x, y)) {
                    System.out.println("Déplacement invalide ! Le cavalier se déplace en L (2+1).");
                    continue;
                }
                
                
                if (x < 0 || x >= damier.getTaille() || y < 0 || y >= damier.getTaille()) {
                    System.out.println("Coordonnées hors du damier !");
                    continue;
                }
                
                
                cavalier.deplacer(x, y);
                
                
                Case caseActuelle = damier.getCase(x, y);
                if (caseActuelle.estAllumee()) {
                    caseActuelle.eteindre();
                } else {
                    System.out.println("Cette case est déjà éteinte !");
                }
                
                
                if (damier.estNiveauTermine()) {
                    afficherDamier(damier, cavalier);
                    System.out.println("Félicitations ! Toutes les cases ont été éteintes !");
                    jeuEnCours = false;
                }
            }
        }
    }

    
    public static void afficherDamier(Damier damier, Cavalier cavalier) {
        System.out.println("\nPlateau : (X = cavalier, O = allumée, . = éteinte)");
        for (int i = 0; i < damier.getTaille(); i++) {
            for (int j = 0; j < damier.getTaille(); j++) {
                if (i == cavalier.getX() && j == cavalier.getY()) {
                    System.out.print("X ");
                } else if (damier.getCase(i, j).estAllumee()) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
