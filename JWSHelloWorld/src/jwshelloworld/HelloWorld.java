/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwshelloworld;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Saim
 */
public class HelloWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.getContentPane().setBackground(Color.red);
        frame.setVisible(true);
    }
    
}
