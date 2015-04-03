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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
/**
*
* @author nidhi
*/
public class Lexer extends JFrame {
    static Set setKeywords ;
	static Set var;
	static Set setOperators;
	static Set setComparators;
	static Set setLogical;
    static HashMap<String, Double> variable;
    
    static String lexicalInformation="";
    static JTextPane textPane;
    
    public static void lex()
    {
    	
    	 variable=new HashMap();
         var=new HashSet();
         try {
             setKeywords = new HashSet();
             setOperators = new HashSet();
             setComparators = new HashSet();
             setLogical = new HashSet();
             Scanner keyFile = new Scanner(new File("keywords.txt"));
             Scanner operatorFile = new Scanner(new File("arithmeticoperators.txt"));
             Scanner comparatorFile = new Scanner(new File("comparisonoperators.txt"));
             Scanner logicalFile = new Scanner(new File("logicaloperators.txt"));
             
             while(keyFile.hasNext())
             {
                 String word = keyFile.next();
                 setKeywords.add(word);
             }
             while(operatorFile.hasNext())
             {
                 String word = operatorFile.next();
                 setOperators.add(word);
             }
             while(comparatorFile.hasNext())
             {
                 String word = comparatorFile.next();
                 setComparators.add(word);
             }
             while(logicalFile.hasNext())
             {
                 String word = logicalFile.next();
                 setLogical.add(word);
             }
             Scanner file = new Scanner(new File("ruby.txt"));
             String t;
             int flag=0;
             while(file.hasNext())
             {
                 String token = file.next();
                 token = token.toLowerCase();
                 if(setKeywords.contains(token))
                     lexicalInformation+=token + " is a keyword \n\n";
                 else if(setOperators.contains(token))
                     lexicalInformation+=token + " is an arithmetic operator \n\n";
                 else if(token.equals("="))
                     lexicalInformation+=token + " is an assignment operator \n\n";
                 else if(setComparators.contains(token))
                     lexicalInformation+=token + " is a comparison operator \n\n";
                 else if(setLogical.contains(token))
                     lexicalInformation+=token + " is a logical operator \n\n";
                 else if(token.matches("[0-9*]"))
                     lexicalInformation+=token + " is a constant \n\n";
                 else if(token.matches("^[a-zA-Z_@$][a-zA-Z0-9_]*$"))
                 {   
                 	lexicalInformation+=token + " is a variable \n\n";
                 	var.add(token);
                 	variable.put(token, 0.0);
                 	//System.out.println(lexicalInformation);
                 }
                 
                 
                 else
                     lexicalInformation+=token + " \n\n";
             }
            // textPane.setText(lexicalInformation);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Lexer.class.getName()).log(Level.SEVERE, null, ex);
         }
    	
    	
    }
    
    
    
    public  Lexer()
    {   
        textPane = new JTextPane();
        setSize(1375,725);
        setTitle("RUBY INTERPRETER");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        create();
        lex();
        
    }
        private void create() {
        JPanel mainPanel = (JPanel) getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(textPane);
    }
}
