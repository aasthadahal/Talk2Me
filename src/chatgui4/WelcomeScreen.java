package chatgui4;

import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;

public class WelcomeScreen extends javax.swing.JFrame {

    DatabaseFunctions dfm;

    public WelcomeScreen() {
        initComponents();
        getContentPane().setBackground(Color.ORANGE);
        jPanel1.setBackground(new Color(0xcA1996));
        jPanel2.setBackground(new Color(0xAA1996));
        jButton1.setBackground(Color.orange);
        jButton3.setBackground(Color.orange);
        jButton3.setBackground(Color.orange);
    }
    //WelcomeScreen ws;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 0));
        setBounds(new java.awt.Rectangle(0, 0, 100, 100));
        setForeground(new java.awt.Color(255, 51, 0));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loginxxx.png"))); // NOI18N
        jLabel4.setMinimumSize(new java.awt.Dimension(27, 7));
        jLabel4.setPreferredSize(new java.awt.Dimension(27, 7));
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 10, 150, 40);

        jTextField1.setBackground(new java.awt.Color(255, 255, 153));
        jTextField1.setText("a");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(20, 70, 128, 30);

        jPasswordField1.setBackground(new java.awt.Color(255, 255, 153));
        jPasswordField1.setText("a");
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(20, 110, 128, 30);

        jButton1.setBackground(new java.awt.Color(51, 153, 0));
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(50, 200, 60, 30);

        jTextField3.setBackground(new java.awt.Color(255, 255, 153));
        jTextField3.setText("localhost");
        jTextField3.setAlignmentX(1.0F);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField3);
        jTextField3.setBounds(20, 150, 130, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 170, 250);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/registerxxx.png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 10, 150, 50);

        jTextField2.setBackground(new java.awt.Color(255, 255, 153));
        jTextField2.setText("NewUserName");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jPanel2.add(jTextField2);
        jTextField2.setBounds(30, 70, 111, 30);

        jPasswordField2.setBackground(new java.awt.Color(255, 255, 153));
        jPasswordField2.setText("jPasswordField2");
        jPasswordField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField2MouseClicked(evt);
            }
        });
        jPanel2.add(jPasswordField2);
        jPasswordField2.setBounds(30, 110, 111, 30);

        jPasswordField3.setBackground(new java.awt.Color(255, 255, 153));
        jPasswordField3.setText("jPasswordField3");
        jPasswordField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField3MouseClicked(evt);
            }
        });
        jPanel2.add(jPasswordField3);
        jPasswordField3.setBounds(30, 150, 111, 30);

        jButton3.setText("Register");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(30, 200, 80, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(190, 10, 180, 250);

        setSize(new java.awt.Dimension(395, 306));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String user = jTextField1.getText();
        String strpass = jPasswordField1.getText().toString();
        String host = jTextField3.getText();
        System.out.println(host);
        dfm = new DatabaseFunctions(host);
        try {
            String query = "SELECT password FROM login where uname=?";
            ResultSet rs = dfm.getResultSet(query, user);
            if (rs.next()) {
                String dbpass = rs.getString(1);
                if (dbpass.equals(strpass)) {

                    JOptionPane.showMessageDialog(null, "Login Successful! ", "Success", JOptionPane.PLAIN_MESSAGE);
                    ClientGUI.uname = user;
                    this.setVisible(false);
                    String[] arguments = new String[]{"123"};
                    (new Sound()).jpt1();
                    ClientGUI.main(arguments);
                } else {
                    JOptionPane.showMessageDialog(null, "Login Unsuccessful!", "Error", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username not found", "Error", 1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        jTextField1.setText(null);
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
        jPasswordField1.setText(null);
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        jTextField2.setText(null);
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jPasswordField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MouseClicked
        jPasswordField2.setText(null);
    }//GEN-LAST:event_jPasswordField2MouseClicked

    private void jPasswordField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField3MouseClicked
        jPasswordField3.setText(null);
    }//GEN-LAST:event_jPasswordField3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String newuser = jTextField2.getText();
        try {
            //String query = "SELECT uname FROM login where uname='" + newuser + "'";
            String query = "SELECT uname FROM login where uname=?";
            ResultSet rs = new DatabaseFunctions(jTextField3.getText()).getResultSet(query, newuser);

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "User " + newuser + "already exists.Choose a new one!", "Failure", JOptionPane.ERROR_MESSAGE);
                jTextField2.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Username Available", "Success", 1);
                if (jPasswordField2.getText().equals(jPasswordField3.getText())) {
                    System.out.println("pwd equals");
                    String newpwd = jPasswordField2.getText();
                    String s_sql = "INSERT INTO login(uname,password) values('" + newuser + "','" + newpwd + "')";
                    new DatabaseFunctions(jTextField3.getText()).updateQuery(s_sql);
                } else {
                    System.out.println("pwd unequal");
                    JOptionPane.showMessageDialog(null, "Password Mismatch", "Failure", 1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error2");
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                WelcomeScreen ws = new WelcomeScreen();
                ws.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
