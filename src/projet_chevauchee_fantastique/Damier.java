/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_chevauchee_fantastique;

/**
 *
 * @author quent    
 */

public class Damier {
    private final Case[][] grille;
    private final int TAILLE = 5; 

    
    public Damier() {
    this.grille = new Case[TAILLE][TAILLE];
    initialiserNiveau1();
    }


    private void initialiserNiveau1() {
        boolean[][] configInitiale = {
            {false, true, false, true, false}, 
            {true, false, false, false, true}, 
            {false, false, false, false, false}, 
            {true, false, false, false, true}, 
            {false, true, false, true, false} 
        };

        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                boolean etat = configInitiale[i][j];
                this.grille[i][j] = new Case(i, j, etat);
            }
        }
    }

   
    public Case getCase(int x, int y) {
        if (x >= 0 && x < TAILLE && y >= 0 && y < TAILLE) {
            return grille[x][y];
        }
        return null;
    }

   
    public boolean estNiveauTermine() {
           for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (grille[i][j].estAllumee()) {
                    return false; 
                }
            }
        }
        return true;
    }

   
    public int getTaille() {
        return TAILLE;
    }
}
