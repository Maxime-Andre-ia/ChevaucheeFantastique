/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projet_chevauchee_fantastique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author mathi
 */
public final class Interface_graphique_chevauchee_fantastique extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Interface_graphique_chevauchee_fantastique.class.getName());

    /**
     * Creates new form Interface_graphique_chevauchee_fantastique
     */
    private final Jeu jeu;
    private JButton[][] grilleBoutons;
    private final javax.swing.JLabel labelEtat;
    private javax.swing.JLabel labelCoups;
    private final javax.swing.JPanel panneauGrille;
    private final javax.swing.JButton btnRecommencer;

    public Interface_graphique_chevauchee_fantastique() {
        initComponents();
        this.jeu = new Jeu();
        this.grilleBoutons = new javax.swing.JButton[5][5];

        this.setTitle("La Chevauchée Fantastique");
        this.setSize(600, 650);

        // On définit la disposition globale (Etape 7 enrichie)
        this.getContentPane().setLayout(new java.awt.BorderLayout());

        // --- ÉTAPE 12 : CRÉATION DU LABEL D'ÉTAT ---
        labelEtat = new javax.swing.JLabel("Bonne chance !", javax.swing.SwingConstants.CENTER);
        labelEtat.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        this.getContentPane().add(labelEtat, java.awt.BorderLayout.SOUTH); // Placé en bas

        JPanel panneauHaut = new JPanel(new java.awt.GridLayout(2, 1));

// 2. On configure le bouton et on l'ajoute au panneauHaut
        btnRecommencer = new javax.swing.JButton("Recommencer le niveau");
        btnRecommencer.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        btnRecommencer.addActionListener(e -> {
            jeu.chargerNiveau(jeu.getNiveauActuel());
            reinitialiserInterface();
        });
        panneauHaut.add(btnRecommencer); // Ajout au petit panneau

// 3. On crée le label des coups et on l'ajoute au panneauHaut
        labelCoups = new javax.swing.JLabel("Nombre de coups : 0", javax.swing.SwingConstants.CENTER);
        labelCoups.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panneauHaut.add(labelCoups); // Ajout au petit panneau

// 4. On place le tout en haut de la fenêtre principale
        this.getContentPane().add(panneauHaut, java.awt.BorderLayout.NORTH);

        this.panneauGrille = new javax.swing.JPanel(new java.awt.GridLayout(5, 5));
        this.getContentPane().add(panneauGrille, java.awt.BorderLayout.CENTER); // Au centre

        initialiserPlateau(panneauGrille);
        actualiserAffichage();
    }

    private void initialiserPlateau(JPanel panneauGrille) {
        int taille = jeu.getDamier().getTaille(); // Utilise bien la taille dynamique !
        for (int i = 0; i < taille; i++) { // Lignes
            for (int j = 0; j < taille; j++) { // Colonnes
                JButton btn = new JButton();
                this.grilleBoutons[i][j] = btn; // i = x, j = y

                final int x = i;
                final int y = j;

                btn.addActionListener(e -> {
                    this.jeu.tenterUnCoup(x, y); // x et y doivent correspondre à la grille
                    actualiserAffichage();
                });
                panneauGrille.add(btn);
            }
        }
    }

    private void reinitialiserInterface() {
        // 1. On vide le panneau
        panneauGrille.removeAll();

        // 2. On récupère la taille actuelle (5 ou 8)
        int taille = jeu.getDamier().getTaille();
        panneauGrille.setLayout(new java.awt.GridLayout(taille, taille));

        // 3. On recrée le tableau de boutons
        this.grilleBoutons = new javax.swing.JButton[taille][taille];

        // 4. On relance la création des boutons et l'affichage
        initialiserPlateau(panneauGrille);

        panneauGrille.revalidate();
        panneauGrille.repaint();

        labelEtat.setText("Niveau " + jeu.getNiveauActuel() + " - Recommencé");
        labelEtat.setForeground(java.awt.Color.BLACK);
    }

    private void passerAuNiveauSuivant() {
        // 1. On passe au niveau 2 dans le moteur de jeu
        int niveauSuivant = jeu.getNiveauActuel() + 1;
        jeu.chargerNiveau(niveauSuivant);

        // 2. On vide visuellement le panneau actuel (enlève les 25 boutons du niv 1)
        if (panneauGrille != null) {
            panneauGrille.removeAll();
        }

        // 3. On récupère la nouvelle taille (ex: 8)
        int nouvelleTaille = jeu.getDamier().getTaille();

        // 4. On change la grille visuelle pour accepter 8x8 boutons
        panneauGrille.setLayout(new java.awt.GridLayout(nouvelleTaille, nouvelleTaille));

        // 5. On recrée le tableau de stockage des boutons à la bonne taille
        this.grilleBoutons = new javax.swing.JButton[nouvelleTaille][nouvelleTaille];

        // 6. On recrée les nouveaux boutons et on les ajoute au panneau
        initialiserPlateau(panneauGrille);

        // 7. On rafraîchit l'interface pour afficher les changements
        panneauGrille.revalidate();
        panneauGrille.repaint();
        actualiserAffichage();
        // 8. On met à jour le texte
        labelEtat.setText("Niveau " + niveauSuivant + " : Bonne chance !");
    }

    /**
     * ÉTAPE 11 : Feedback Visuel Cette méthode redessine le plateau sans
     * recréer les boutons.
     */
    public void actualiserAffichage() {
        Damier damier = this.jeu.getDamier();
        Cavalier cavalier = this.jeu.getCavalier();
        int taille = damier.getTaille(); // On récupère la vraie taille (5 ou 8)

        for (int i = 0; i < taille; i++) { // Remplacé 5 par taille
            for (int j = 0; j < taille; j++) { // Remplacé 5 par taille
                JButton btn = this.grilleBoutons[i][j];
                Case laCase = damier.getCase(i, j);

                if (laCase != null && laCase.estAllumee()) {
                    btn.setBackground(java.awt.Color.RED);
                } else {
                    btn.setBackground(java.awt.Color.DARK_GRAY);
                }

                if (cavalier.getX() == i && cavalier.getY() == j) {
                    btn.setText("C");
                    btn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 40));
                    btn.setForeground(java.awt.Color.WHITE);
                } else {
                    btn.setText("");
                }
                labelCoups.setText("Nombre de coups : " + jeu.getNbCoups());
            }
        }

        if (jeu.getDamier().estNiveauTermine()) {
            labelEtat.setText("NIVEAU TERMINÉ ! BRAVO !");
            labelEtat.setForeground(java.awt.Color.GREEN);
            Timer timer = new Timer(2000, e -> passerAuNiveauSuivant());
            timer.setRepeats(false);
            timer.start();
        } else if (jeu.estBloque()) {
            labelEtat.setText("PARTIE PERDUE : VOUS ÊTES COINCÉ !");
            labelEtat.setForeground(java.awt.Color.RED);
        } else {
            labelEtat.setText("Partie en cours...");
            labelEtat.setForeground(java.awt.Color.BLACK);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Interface_graphique_chevauchee_fantastique().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
