/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_chevauchee_fantastique;

/**
 *
 * @author quent
 */
public final class Jeu {

    private Damier damier;
    private Cavalier cavalier;
    private int niveauActuel = 1;
    private int nbCoups = 0;

    public Jeu() {
        chargerNiveau(1);
    }

    public void chargerNiveau(int n) {
        this.niveauActuel = n;
        this.nbCoups = 0;
        if (n == 1) {
            this.damier = new Damier(5, 1);
            this.cavalier = new Cavalier(2, 2);
        } else if (n == 2) {
            this.damier = new Damier(8, 2);
            this.cavalier = new Cavalier(3, 3);
        }
    }

    public int getNiveauActuel() {
        return niveauActuel;
    }

    public void tenterUnCoup(int xClique, int yClique) {

        if (cavalier.peutAllerEn(xClique, yClique)) {
            Case laCase = damier.getCase(xClique, yClique);

            if (laCase != null && laCase.estAccessible()) {

                cavalier.deplacer(xClique, yClique);
                laCase.eteindre();
                nbCoups++;
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

            
            if (laCase != null && laCase.estAccessible()) {
                return false; 
            }
        }
        return true; 
    }

    public Damier getDamier() {
        return this.damier; 
    }

    public Cavalier getCavalier() {
        return this.cavalier; 
    }

    public int getNbCoups() {
        return nbCoups;
    }
}
