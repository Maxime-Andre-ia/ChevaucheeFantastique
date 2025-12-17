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

    private final Damier damier;
    private final Cavalier cavalier;

    public Jeu() {
        this.damier = new Damier();
        this.cavalier = new Cavalier(2, 2);
    }

    public void tenterUnCoup(int xClique, int yClique) {

        if (cavalier.peutAllerEn(xClique, yClique)) {
            Case laCase = damier.getCase(xClique, yClique);

            // On vérifie si la case est jouable
            if (laCase != null && laCase.estAllumee()) {

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

            if (laCase != null && laCase.estAllumee()) {
                return false;
            }
        }
        return true;
    }
}
