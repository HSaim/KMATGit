/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingcounterws;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 

/**
 *
 * @author Habiba Saim
 */
/** A counter application in Swing */
@SuppressWarnings("serial")
public class SwingCounterWS extends JFrame {
   private JTextField tfCount; // for displaying the counter value
   private int count = 0;      // counter's value
 
   public SwingCounterWS() {
      Container cp = getContentPane();
      cp.setLayout(new FlowLayout());
 
      cp.add(new JLabel("Counter: "));
      tfCount = new JTextField("0", 10);
      tfCount.setEditable(false);
      cp.add(tfCount);
 
      JButton btnCount = new JButton("Count");
      cp.add(btnCount);
      btnCount.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            ++count;
            tfCount.setText(count + "");
         }
      });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Swing Counter");    // Set this JFrame's title
      setSize(300, 100);            // Set initial window size
      setVisible(true);             // Show it
   }
 
   public static void main(String[] args) {
      // Run the GUI codes in the Event-Dispatcher thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new SwingCounterWS();  // Let the constructor do the job
         }
      });
   }
}