/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubyinterpreter;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nidhi
 */
public class Lexer {
    Set setKeywords, setOperators, setComparators, setLogical;
    
    Lexer()
    {
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
            
            while(file.hasNext())
            {
                String token = file.next();
                token = token.toLowerCase();
                if(setKeywords.contains(token))
                System.out.println(token + " is a keyword \n");
                else if(setOperators.contains(token))
                    System.out.println(token + " is an arithmetic operator \n");
                else if(token.equals("="))
                    System.out.println(token + " is an assignment operator \n");
                else if(setComparators.contains(token))
                    System.out.println(token + " is a comparison operator \n");
                else if(setLogical.contains(token))
                    System.out.println(token + " is a logical operator \n");
                else if(token.matches("[0-9*]"))
                    System.out.println(token + " is a constant \n");
                else if(token.matches("^[a-zA-Z_@$][a-zA-Z0-9_]*$"))
                    System.out.println(token + " is a variable \n");
                else                    
                    System.out.println(token + " \n");
 
             

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lexer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
