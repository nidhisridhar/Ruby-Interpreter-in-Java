/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubyinterpreter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Poorva
 */
class RubyInput extends JFrame {
    JTextField rubyText;
    JButton next; 
    
    RubyInput()
    {
        rubyText = new JTextField();
        next = new JButton("Next");
        setSize(1375,725);
        setTitle("RUBY INTERPRETER");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        create1();
        
        
    }
    
    private void create1()
    {
        JPanel mainPanel1 = (JPanel) getContentPane();
        mainPanel1.setLayout(new BorderLayout());
        mainPanel1.add(rubyText);
        mainPanel1.add(next);
    }
}
