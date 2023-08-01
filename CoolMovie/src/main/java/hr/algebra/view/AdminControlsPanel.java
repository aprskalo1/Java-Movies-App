/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.AdminControlsRepoFactory;
import hr.algebra.dal.IAdminControlsRepo;
import hr.algebra.dal.IMovieRepo;
import hr.algebra.dal.MovieRepoFactory;
import hr.algebra.parser.rss.MovieParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Ante Prskalo
 */
public class AdminControlsPanel extends javax.swing.JPanel {

    private static String assetsPath = "assets";
    private static String dirPath = "dir";

    /**
     * Creates new form AdminControlsPanel
     */
    public AdminControlsPanel() {
        initComponents();
        initRepo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnClearAllData = new javax.swing.JButton();
        btnParseAndLoadMovies = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("Clear all data!");

        btnClearAllData.setBackground(new java.awt.Color(255, 51, 51));
        btnClearAllData.setText("CLEAR DATA");
        btnClearAllData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllDataActionPerformed(evt);
            }
        });

        btnParseAndLoadMovies.setText("LOAD");
        btnParseAndLoadMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParseAndLoadMoviesActionPerformed(evt);
            }
        });

        jLabel2.setText("Load movies");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnParseAndLoadMovies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnClearAllData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(924, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearAllData)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnParseAndLoadMovies)
                .addContainerGap(476, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearAllDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllDataActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all data?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                adminRepo.clearAllData();
            } catch (Exception ex) {
                Logger.getLogger(AdminControlsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearAssetsDirectory(assetsPath);
            clearAssetsDirectory(dirPath);
        }
    }//GEN-LAST:event_btnClearAllDataActionPerformed

    private void btnParseAndLoadMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParseAndLoadMoviesActionPerformed

        try {
            MovieParser.parse();
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(AdminControlsPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminControlsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnParseAndLoadMoviesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearAllData;
    private javax.swing.JButton btnParseAndLoadMovies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    private static IAdminControlsRepo adminRepo;
    private static IMovieRepo movieRepo;

    private void initRepo() {
        adminRepo = AdminControlsRepoFactory.getRepository();
        movieRepo = MovieRepoFactory.getRepository();
    }

    public static void clearAssetsDirectory(String path) {
        try {
            Path directory = Paths.get(path);
            if (Files.exists(directory) && Files.isDirectory(directory)) {
                Files.walk(directory)
                        .sorted(java.util.Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the assets directory: " + e.getMessage());
        }
    }
}
