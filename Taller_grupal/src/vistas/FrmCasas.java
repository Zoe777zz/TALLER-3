/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.utilidades.Utiles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import javax.swing.JOptionPane;
import vistasmodeloTabla.MTableCasas;

/**
 *
 * @author InnoVausuario
 */
public class FrmCasas extends javax.swing.JFrame {

    /**
     * Creates new form FrmCasas
     */
    public FrmCasas() {
        initComponents();
    }
    private Casas[] casas = new Casas[10]; // 1 arreglo
    private int fila = -1;
    private MTableCasas mTableCasas = new MTableCasas();

    private void registrar() {
        if (verificartxt()) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos ");
        } else {
            if (Utiles.validate(jTextField1.getText().trim()) && Utiles.validate(jTextField2.getText().trim()) && Utiles.validate(jTextField3.getText().trim())) { // VALIDAR NUMEROS

                Float largo = Utiles.transformarFloat(jTextField1.getText().trim()); // defino 
                Float ancho = Utiles.transformarFloat(jTextField2.getText().trim());
                int piso = Utiles.transformarInt(jTextField3.getText().trim());
                if (fila == -1) { // -1 no selecciona nada 
                    if (guardar(largo, ancho, piso)) {
                        JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "OK!", JOptionPane.INFORMATION_MESSAGE);
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hubo un error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {

                    if (modificar(largo, ancho, piso, fila)) {
                        JOptionPane.showMessageDialog(null, "Se ha modificado correctamente!!", "OK!", JOptionPane.INFORMATION_MESSAGE);
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar", "ERROR", JOptionPane.ERROR);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private Integer verificar() {
        Integer pos = -1;
        for (int i = 0; i < casas.length; i++) { // . length todo el tamaño de esa fila
            if (casas[i] == null) { // este
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    private Boolean guardar(Float largo, Float ancho, int pisos) {
        int pos = verificar();
        if (pos >= 0 && pos < casas.length) { // menor a la cantidad de elemntos del arreglo 
            casas[pos] = new Casas(ancho, largo, pisos);
            return true;
        }
        return false;
    }
     public Boolean modificar(Float largo, Float ancho, int piso, Integer pos) {
        if (pos >= 0 && pos < casas.length) { // este
            casas[pos].setAncho(ancho); // set modifica 
            casas[pos].setLargo(largo);
            casas[pos].setPisos(piso);
            return true;
        }
        return false;
    }
      private void limpiar() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        cargarTabla();
        jTable1.clearSelection();
    }
    private void cargarTabla() {
        mTableCasas.setCasa(casas);
        jTable1.setModel(mTableCasas);
        jTable1.updateUI();
    }
    private boolean verificartxt() {
        return (jTextField1.getText().trim().isEmpty() || jTextField2.getText().trim().isEmpty() || jTextField3.getText().isEmpty());

    }
     private String Comparar() {
        String cadena = "";
        String aux = "";
        Boolean comp;
        for (Integer i = 0; i < casas.length; i++) {
            for (Integer j = i + 1; j < casas.length; j++) {
                if (casas[j] != null) {
                    // Primitivo no se pueden comparar los valores 
                    // Entonces se compara los objetos 
                    // Objects se compara 
                    comp = (((Objects.equals(casas[i].getAncho(), casas[j].getAncho())) && 
                            (Objects.equals(casas[i].getLargo(), casas[j].getLargo()))) || 
                            ((Objects.equals(casas[i].getAncho(), casas[j].getLargo())) && 
                            (Objects.equals(casas[i].getLargo(), casas[j].getAncho()))));
                    // se a guarda 
                    comp = (comp && (Objects.equals(casas[i].getPisos(), casas[j].getPisos())));
                    // acumulador (
                    if (comp && (!(aux.contains(i.toString())) || !(aux.contains(j.toString())))) {
                        // se concatenan las dos pos
                        aux += i + j;
                        cadena += "Las casas " + (i + 1) + " y " + (j + 1) + " Son homónimas \n";// las casa que son iguales
                    }
                }
            }
        }
        return cadena;
    }
     public Boolean generar_Archivo() {
        if (casas != null) {
            String pathInforme = "src" + File.separatorChar + "data" + File.separatorChar + "casas" + File.separatorChar + "casas.txt";
            try {
                FileWriter file_registro = new FileWriter(pathInforme);
                for (int i = 0; i < casas.length; i++) {
                    if (casas[i] != null) {
                        // modificar el texto FileWriter
                        file_registro.write(casas[i].getAncho().toString() + "\t" + casas[i].getLargo().toString() + "\t" + casas[i].getPisos().toString() + "\n");
                        file_registro.flush();
                    }
                }
                file_registro.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
     public Boolean cargarArchivo() {
         // direccion del archivo
        String pathCasas = "src" + File.separatorChar + "data" + File.separatorChar + "casas" + File.separatorChar + "casas.txt";
        try {

            FileReader fre = new FileReader(pathCasas);
            BufferedReader file_datos = new BufferedReader(fre);
            fre.close();
            file_datos.close();
            fre = new FileReader(pathCasas);
            file_datos = new BufferedReader(fre);

            String linea;
            int contador = 0;
            while ((linea = file_datos.readLine()) != null) {
                if (contador < casas.length) {
                    String[] dato = linea.split("\t"); // separamos tabulador 
                    casas[contador] = new Casas(Float.parseFloat(dato[0]), Float.parseFloat(dato[1]), Integer.parseInt(dato[2])); // casteo y envio para crear el arreglo (si existe y se puede trabajr)
                    contador++;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
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

        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("REGISTRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("COMPARAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("GENERAR ARCHIVO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("CARGAR ARCHIVO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setBackground(new java.awt.Color(255, 51, 102));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CASAS ");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setOpaque(true);

        jLabel2.setText("ANCHO:");

        jLabel3.setText("LARGO: ");

        jLabel4.setText("PISOS:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 17, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel3)
                        .addGap(105, 105, 105)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(12, 12, 12)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(133, 133, 133))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        registrar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String cadena = Comparar();
        if (cadena.isBlank()){
            jLabel1.setText("No hay casas homónimas");
          
        } else {
            jLabel1.setText("<Html>"+cadena+ "</Html>");
        }
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         if (generar_Archivo()) { // cateo objeto - entero crea un archivo con el item
            JOptionPane.showMessageDialog(null, "Se ha registrado correctamente!!", "OK!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error", "ERROR", JOptionPane.ERROR);
        }
        cargarTabla();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (cargarArchivo()) {
            cargarTabla();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCasas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCasas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCasas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCasas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCasas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
