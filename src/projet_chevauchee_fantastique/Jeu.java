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
    private int niveauActuel=1;

    public Jeu() {
        chargerNiveau(1);
    }
    
    public void chargerNiveau(int n) {
        this.niveauActuel = n;
        if (n == 1) {
            this.damier = new Damier(5, 1);
            this.cavalier = new Cavalier(2, 2);
        } else if (n == 2) {
            this.damier = new Damier(8, 2); // Grille plus grande
            this.cavalier = new Cavalier(3, 3);
        }
    }
    
    public int getNiveauActuel() {
        return niveauActuel;
    }

    public void tenterUnCoup(int xClique, int yClique) {

        if (cavalier.peutAllerEn(xClique, yClique)) {
            Case laCase = damier.getCase(xClique, yClique);

            // On vérifie si la case est jouable
            if (laCase != null && laCase.estAccessible()) {

                cavalier.deplacer(xClique, yClique);
                laCase.eteindre();
                System.out.println("Coup reussi !");

                if (damier.estNiveauTermine()) {
                    System.out.println("GAGNE !");
                } else if (this.estBloque()) {
                    System.out.println("PERDU ! Vous etes coince.");
                }

            } else {
                System.out.println("Case deja eteinte ou hors limite.");
            }

        } else {
            System.out.println("Ce n'est pas un mouvement en L !");
        }
    }

    public boolean estBloque() {
        int[][] sauts = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };

    for (int i = 0; i < 8; i++) {
        int cibleX = cavalier.getX() + sauts[i][0];
        int cibleY = cavalier.getY() + sauts[i][1];

        Case laCase = damier.getCase(cibleX, cibleY);

        // Si la case existe et qu'elle est soit NEUTRE, soit ALLUMÉE
        if (laCase != null && laCase.estAccessible()) {
            return false; // Il y a encore un chemin possible !
        }
    }
    return true; // Vraiment coincé
    }

    public Damier getDamier() {
    return this.damier; // On renvoie l'objet damier au lieu de l'erreur
}

public Cavalier getCavalier() {
    return this.cavalier; // On renvoie l'objet cavalier au lieu de l'erreur
}
}