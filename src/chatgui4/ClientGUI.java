package chatgui4;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClientGUI extends javax.swing.JFrame implements Runnable, ActionListener,
        ListSelectionListener {//, ChangeListener

    private static Socket clientSocket = null;
    private static PrintStream os = null;
    private static BufferedReader is = null;
    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    public static String uname;
    private Vector listData;
    DefaultListModel dfm;
    String selected;
    

    public ClientGUI() {
        initComponents();
        jList2.addListSelectionListener(this);
//        jTabbedPane1.addChangeListener(this);
        getContentPane().setBackground(new Color(0xFF0096));
        jPanel2.setBackground(Color.white);
        jButton1.setBackground(Color.orange);
        jButton3.setBackground(Color.orange);
        jButton4.setBackground(Color.orange);
        jButton2.setBackground(Color.orange);
        jbdc.setBackground(Color.orange);
        jPanel2.setBackground(Color.getHSBColor(60, 40  , 100));
        jList2.setBackground(Color.getHSBColor(60, 40  , 100));
        jTextField1.setBackground(Color.getHSBColor(60, 40  , 100));


    }

    public void valueChanged(ListSelectionEvent evt) {
        boolean found = false;
        int j;
        try {
             selected = jList2.getSelectedValue().toString();

            System.out.println("value selected vayo hola");
            if (!selected.equals(uname)) {
                for (j = 0; j < jTabbedPane1.getTabCount(); j++) {
                    tabName[j] = jTabbedPane1.getTitleAt(j);
                    //System.out.println(tabName[j]);

                    if (tabName[j].equals(selected)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    jTabbedPane1.setSelectedIndex(j);
                    jTextField1.requestFocus();
                } else {
                    for (int i = 0; i < 10; i++) {
                        try {
                            if (panels[i] == null) {
                                panels[i] = new JPanel();
                                textArea[i] = new JTextArea(10, 35);
                                textArea[i].setEditable(false);
                                panels[i].add(textArea[i]);
                                jTabbedPane1.add(panels[i]);
                                jTabbedPane1.setTitleAt(i, selected);
                                jTabbedPane1.setSelectedIndex(i);
                                jTextField1.requestFocus();
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                }
            }//if close
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void goOffline() {
        //just updates database, not JList
        try {
            Class.forName("com.mysql.jdbc.Driver");//localhost
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
            Statement st = (Statement) con.createStatement();
            String s_sql = "UPDATE login SET online=0 WHERE uname='" + uname + "'";
            System.out.println(st.executeUpdate(s_sql));
            con.close();
            System.out.println("i m offline");
        } catch (Exception e) {
            System.out.println("error areeeeeee");
            System.out.println(e.getMessage());
        }
    }

    private void goOnline() {
        //just updates database not the JList
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
            Statement st = (Statement) con.createStatement();
            String s_sql = "UPDATE login SET online=1 WHERE uname='" + uname + "'";
            System.out.println(st.executeUpdate(s_sql));
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateOnlineList() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
            Statement st = (Statement) con.createStatement();
            String query = "SELECT uname FROM login where online=1 AND uname <> '" + uname + "'";
            ResultSet rs = st.executeQuery(query);
            System.out.println(rs.toString());

            dfm = new DefaultListModel();
            while (rs.next()) {
                dfm.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jList2.setModel(dfm);
    }

    private void disconnectedList() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
            Statement st = (Statement) con.createStatement();
            String query = "SELECT uname FROM login where online=1 AND uname <> '" + uname + "'";
            ResultSet rs = null;

            dfm = new DefaultListModel();
            while (rs.next()) {
                dfm.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jList2.setModel(dfm);
    }

    private void getnsend() {
        boolean found = false;
        String typeMsg = jTextField1.getText();
        try {
            String selected = jList2.getSelectedValue().toString();
            String Quit = "quit";

            if (Quit.equalsIgnoreCase(typeMsg.trim())) {
                os.println(uname + "@! server @!" + typeMsg + "@!quit");
            } else {
                os.println(uname + "@!" + selected + "@!" + typeMsg + "@!msg");
                int j;
                for (j = 0; j < jTabbedPane1.getTabCount(); j++) {
                    tabName[j] = jTabbedPane1.getTitleAt(j);
                    System.out.println(tabName[j]);
                    if (tabName[j].equals(selected)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    textArea[j].append(selected + ": " + typeMsg + "\n");
                    jTabbedPane1.setSelectedIndex(j);
                } else {
                    for (int i = 0; i < 10; i++) {
                        try {
                            if (panels[i] == null) {
                                panels[i] = new JPanel();
                                textArea[i] = new JTextArea(10, 35);
                                //textArea[i].setName("suleman");
                                panels[i].add(textArea[i]);
                                jTabbedPane1.add(panels[i]);
                                jTabbedPane1.setTitleAt(i, selected);
                                jTabbedPane1.setSelectedIndex(i);
                                textArea[i].append(uname + "- " + selected + ": " + typeMsg + "\n");
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                }
            }
            jTextField1.setText("");
        }//close try
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


    }//close getnsend

    /**
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jbdc = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Online Users");

        jButton1.setText("Available");
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jbdc.setText("Disconnect");
        jbdc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbdcActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton3.setText("Send");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList2);

        jButton4.setText("Chat Log");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Buzz");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(156, 156, 156))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Connect");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbdc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(0, 188, 188))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jbdc)
                    .addComponent(jButton4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-607)/2, (screenSize.height-431)/2, 607, 431);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int portNumber = 2222;
        String host = "localhost";
        
        try {
            clientSocket = new Socket(host, portNumber);
            System.out.println(clientSocket);
            System.out.println("connected");
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os.println(uname + " @! server @! " + uname + " @! iamonline");//@!@!
            this.setTitle("Talk to me: " + uname);
            goOnline();
            updateOnlineList();
            jButton1.setEnabled(false);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Is the server started!!!");
//            System.err.println("Couldn't get I/O for the connection to the host " + host);
        }
        if (clientSocket != null && os != null && is != null) {
            try {
                new Thread(this).start();
            } catch (Exception e) {
                System.err.println("IOException:  " + e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        getnsend();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        getnsend();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        goOffline();
        try {
            os.println(uname + " @! server @! " + uname + " @! quit");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_formWindowClosing

    private void jbdcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbdcActionPerformed
        goOffline();                
        try {
            jButton1.setEnabled(true);
            os.println(uname + " @! server @! " + uname + " @! quit");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
            Statement st = (Statement) con.createStatement();
            String query = "SELECT uname FROM login where online=2";
            ResultSet rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e+ "\nFirst be Available to Disconnect!");
            System.out.println(e);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jbdcActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        int portNumber = 2222;
        String host = "localhost";
        try {
            clientSocket = new Socket(host, portNumber);
            System.out.println("connected");
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //"from @! to @! message@! type of msg";
            os.println(uname + " @! server @! " + uname + " @! iamonline");//@!@!
            this.setTitle("Talk to me: " + uname);
            goOnline();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host " + host);
        }


        if (clientSocket != null && os != null && is != null) {
            try {
                new Thread(this).start();
            } catch (Exception e) {
                System.err.println("IOException:  " + e);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String[] name = {uname};
        ChatLog.main(name);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        System.out.println("lau main() vitra po chirecha ta.achamma hai:0)");

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
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ClientGUI().setVisible(true);
                System.out.println("lau yo ni printed lol");

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbdc;
    // End of variables declaration//GEN-END:variables
    String responseLine;
    JTextArea[] textArea = new JTextArea[10];
    JPanel[] panels = new JPanel[10];
    String[] tabName = new String[10];

    @Override
    public void run() {
        try {
            while ((responseLine = is.readLine().trim()) != null) {
                boolean found = false;
                String[] decodedResponseLine = responseLine.split("@!");
                if (decodedResponseLine[0].trim().equals("server")) {
                    if (decodedResponseLine[2].equalsIgnoreCase("populateonline")) {
                        System.out.println("populate request received");
                        updateOnlineList();
                    }
                    if (decodedResponseLine[2].equalsIgnoreCase("depopulateonline")) {
                        System.out.println("depopulate request received");
                        updateOnlineList();
                    }
                    if (decodedResponseLine[3].equals("closeyourself")) {
                        closed = true;
                    }
                } else if (decodedResponseLine[1].equalsIgnoreCase(uname)) {

                    int j;
                    for (j = 0; j < jTabbedPane1.getTabCount(); j++) {
                        tabName[j] = jTabbedPane1.getTitleAt(j);
                        if (tabName[j].equals(decodedResponseLine[0])) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        textArea[j].append(decodedResponseLine[0] + ": " + decodedResponseLine[2] + "\n");
                        jTabbedPane1.setSelectedIndex(j);
                    } else {
                        for (int i = 0; i < 10; i++) {
                            try {
                                if (panels[i] == null) {
                                    panels[i] = new JPanel();
                                    textArea[i] = new JTextArea(10, 35);
                                    panels[i].add(textArea[i]);
                                    jTabbedPane1.add(panels[i]);
                                    jTabbedPane1.setTitleAt(i, decodedResponseLine[0]);
                                    jTabbedPane1.setSelectedIndex(i);
                                    textArea[i].append(decodedResponseLine[0] + ": " + decodedResponseLine[2] + "\n");
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                        }
                    }
                }
                if (responseLine.indexOf("*** Bye") != -1) {
                    break;
                }
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
    
//       selected=jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
