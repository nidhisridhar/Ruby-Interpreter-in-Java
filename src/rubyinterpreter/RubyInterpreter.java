

package rubyinterpreter;

/**
 *
 * @author nidhi
 */
public class RubyInterpreter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int choice = 2;
        
        switch(choice)
            {    
                case 1 : RubyInput r = new RubyInput();
                         break;
                    
                case 2: Lexer l = new Lexer();
                        break;
            }
    }
}
