/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projet_chevauchee_fantastique;

import java.awt.Color;
import javax.swing.JButton;
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
    private final javax.swing.JLabel labelCoups;
    private final javax.swing.JPanel panneauGrille;
    private final javax.swing.JButton btnRecommencer;
    
    private void afficherRegles() {
    String message = """
                     \u2728 BIENVENUE DANS LA CHEVAUCH\u00c9E FANTASTIQUE \u2728
                     
                     Votre mission : \u00c9teindre toutes les cases ROUGES du damier.
                     
                     \ud83d\udd79\ufe0f R\u00c8GLES DU JEU :
                     1. Le Cavalier (\u265e) se d\u00e9place uniquement en 'L' (2 cases puis 1).
                     2. Cliquez sur une case pour vous y d\u00e9placer.
                     3. Une fois \u00e9teinte, une case devient BLEUE.
                     
                     Saurez-vous terminer le Niveau 2 avec le moins de coups possible ?
                     Bon courage ! """;

    javax.swing.JOptionPane.showMessageDialog(this, message, "Règles du Jeu", javax.swing.JOptionPane.INFORMATION_MESSAGE);
}

    public Interface_graphique_chevauchee_fantastique() {
        initComponents();
        this.jeu = new Jeu();
        this.grilleBoutons = new javax.swing.JButton[5][5];

        this.setTitle("La Chevauchée Fantastique");
        this.setSize(600, 650);

        this.getContentPane().setLayout(new java.awt.BorderLayout());

        labelEtat = new javax.swing.JLabel("Bonne chance !", javax.swing.SwingConstants.CENTER);
        labelEtat.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        this.getContentPane().add(labelEtat, java.awt.BorderLayout.SOUTH); // Placé en bas

        JPanel panneauHaut = new JPanel(new java.awt.GridLayout(2, 1));

        btnRecommencer = new javax.swing.JButton("Recommencer le niveau");
        btnRecommencer.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        btnRecommencer.addActionListener(e -> {
            jeu.chargerNiveau(jeu.getNiveauActuel());
            reinitialiserInterface();
        });
        panneauHaut.add(btnRecommencer); 

        labelCoups = new javax.swing.JLabel("Nombre de coups : 0", javax.swing.SwingConstants.CENTER);
        labelCoups.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panneauHaut.add(labelCoups);

        this.getContentPane().add(panneauHaut, java.awt.BorderLayout.NORTH);

        this.panneauGrille = new javax.swing.JPanel(new java.awt.GridLayout(5, 5));
        this.getContentPane().add(panneauGrille, java.awt.BorderLayout.CENTER); 

        initialiserPlateau(panneauGrille);
        actualiserAffichage();
        java.awt.EventQueue.invokeLater(() -> afficherRegles());
    }

    private void initialiserPlateau(JPanel panneauGrille) {
        int taille = jeu.getDamier().getTaille(); 
        for (int i = 0; i < taille; i++) { 
            for (int j = 0; j < taille; j++) { 
                JButton btn = new JButton();
                this.grilleBoutons[i][j] = btn; 

                final int x = i;
                final int y = j;

                btn.addActionListener(e -> {
                    this.jeu.tenterUnCoup(x, y); 
                    actualiserAffichage();
                });
                panneauGrille.add(btn);
            }
        }
    }

    private void reinitialiserInterface() {
        panneauGrille.removeAll();

        int taille = jeu.getDamier().getTaille();
        panneauGrille.setLayout(new java.awt.GridLayout(taille, taille));

        this.grilleBoutons = new javax.swing.JButton[taille][taille];

        initialiserPlateau(panneauGrille);

        panneauGrille.revalidate();
        panneauGrille.repaint();

        labelEtat.setText("Niveau " + jeu.getNiveauActuel() + " - Recommencé");
        labelEtat.setForeground(java.awt.Color.BLACK);
    }

    private void passerAuNiveauSuivant() {
        int niveauSuivant = jeu.getNiveauActuel() + 1;
        jeu.chargerNiveau(niveauSuivant);

        if (panneauGrille != null) {
            panneauGrille.removeAll();
        }

        int nouvelleTaille = jeu.getDamier().getTaille();

        panneauGrille.setLayout(new java.awt.GridLayout(nouvelleTaille, nouvelleTaille));

        this.grilleBoutons = new javax.swing.JButton[nouvelleTaille][nouvelleTaille];

        initialiserPlateau(panneauGrille);

        panneauGrille.revalidate();
        panneauGrille.repaint();
        actualiserAffichage();
        labelEtat.setText("Niveau " + niveauSuivant + " : Bonne chance !");
    }

    /**
     * ÉTAPE 11 : Feedback Visuel Cette méthode redessine le plateau sans
     * recréer les boutons.
     */
    public void actualiserAffichage() {
        Damier damier = this.jeu.getDamier();
        Cavalier cavalier = this.jeu.getCavalier();
        int taille = damier.getTaille();

        Color rougeCorail = new Color(231, 76, 60);    
        Color bleuNuit = new Color(44, 62, 80);

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                JButton btn = this.grilleBoutons[i][j];
                Case laCase = damier.getCase(i, j);

                btn.setFocusPainted(false);
                btn.setBorder(javax.swing.BorderFactory.createLineBorder(bleuNuit, 1));

                if (laCase != null && laCase.estAllumee()) {
                    btn.setBackground(rougeCorail);
                } else {
                    btn.setBackground(bleuNuit);
                }

                if (cavalier.getX() == i && cavalier.getY() == j) {
                    btn.setText("♞"); 
                    btn.setFont(new java.awt.Font("Segoe UI Symbol", java.awt.Font.BOLD, 50));
                    btn.setForeground(Color.WHITE);
                } else {
                    btn.setText("");
                }
            }
        }

        labelCoups.setText("Nombre de coups : " + jeu.getNbCoups());

        if (jeu.getDamier().estNiveauTermine()) {
            labelEtat.setText("✨ NIVEAU TERMINÉ ! ✨");
            labelEtat.setForeground(new Color(46, 204, 113)); // Vert Émeraude

            Timer timer = new Timer(2000, e -> passerAuNiveauSuivant());
            timer.setRepeats(false);
            timer.start();
        } else if (jeu.estBloque()) {
            labelEtat.setText("❌ COINCÉ ! ESSAYEZ ENCORE...");
            labelEtat.setForeground(rougeCorail);
        } else {
            labelEtat.setText("Niveau " + jeu.getNiveauActuel() + " en cours...");
            labelEtat.setForeground(Color.BLACK);
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
