/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_chevauchee_fantastique;

/**
 *
 * @author quent
 */
/**
 * Représente une seule cellule du damier.
 * Contient ses coordonnées et son état (allumée ou éteinte).
 */
public class Case {
    private int x; 
    private int y;
    private boolean estAllumee;
    
    public Case(int x, int y, boolean estAllumee) {
        this.x = x; 
        this.y = y; 
        this.estAllumee = estAllumee;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean estAllumee() {
    return estAllumee;
    }

     public void eteindre() {
     this.estAllumee = false;
    }
}