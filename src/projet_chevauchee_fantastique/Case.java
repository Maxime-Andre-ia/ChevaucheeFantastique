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
    private final int x;
    private final int y;
    private boolean estAllumee;
    private final boolean cible; // C'est cette variable qui définit le type de case

    public Case(int x, int y, boolean estCible) {
        this.x = x;
        this.y = y;
        this.cible = estCible; // On stocke si c'est une cible ou pas
        
        // Si c'est une cible, elle commence allumée. 
        // Si c'est une case neutre, elle est considérée comme éteinte dès le départ.
        this.estAllumee = estCible;
    }

    public boolean estAllumee() {
        return estAllumee;
    }

    // Cette méthode permet de savoir si c'est une case neutre
    public boolean estNeutre() {
        return !this.cible;
    }

    public void eteindre() {
        this.estAllumee = false;
    }
    
    // Une case est "jouable" si elle est neutre OU si elle est encore rouge
    public boolean estAccessible() {
        return estNeutre() || estAllumee;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}