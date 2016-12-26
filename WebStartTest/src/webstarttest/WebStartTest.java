/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webstarttest;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Habiba Saim
 */
public class WebStartTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       EventQueue.invokeLater(new Runnable(){
      public void run()
      {
          JFrame frame = new JFrame("My first application Java Web Start");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(400,300);
          frame.getContentPane().setBackground(Color.orange);
          frame.setVisible(true);
      }
      });  
    }
    
}
