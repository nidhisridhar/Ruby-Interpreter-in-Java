/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubyinterpreter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    public JTextPane rubyText;
    JButton next; 
    FileWriter fWriter = null;
    BufferedWriter writer = null;
    
    public RubyInput()
    {
        rubyText = new JTextPane();
        next = new JButton("Next");
        setSize(900,525);
        setTitle("RUBY INTERPRETER");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel mainPanel1 = new JPanel();
        mainPanel1.setLayout(new BorderLayout());
        mainPanel1.add(rubyText);
        add("Center",mainPanel1);
        
        mainPanel1 = new JPanel();
        mainPanel1.add(next);
        add("South",mainPanel1);
        
        next.addActionListener(new btn());
        
    }
    
     class btn implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                String text = rubyText.getText();
                fWriter = new FileWriter("ruby.txt");
                writer = new BufferedWriter(fWriter);
                writer.write(text);
                writer.newLine();
                writer.close();
                
            } catch (IOException ex) {
                Logger.getLogger(RubyInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            Lexer l = new Lexer();
            dispose();
        }
    }
}
