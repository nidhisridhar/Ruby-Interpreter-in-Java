	package rubyinterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class parser {
	
	
	void parsear()
	{
		
	    Lexer.lex();
	    System.out.println(Lexer.variable);
            StringTokenizer t;
		try {
			Scanner file = new Scanner(new File("ruby.txt"));
			String s;
			
			int flag=0;
			while(file.hasNextLine())
			{   
				s=file.nextLine();
				System.out.println(s);
                                int endcheck;
                                
				if(s.equals("end"))
                                {
                                    System.out.println("ERROR:End Defined without a function definition");
                                    break;
                                }
				if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[0-9]{1,13}(\\.[0-9]*)?$"))
				{   System.out.println("YES ASSIGNMENT");
					t=new StringTokenizer(s);
					String v=null,d=null;
					while(t.hasMoreTokens())
					{
						String st=t.nextToken();
						if(st.matches("[a-zA-Z_@$]([a-zA-Z0-9_])*"))
						{			v=st; System.out.println(v);
                                                }
						else if(st.matches("[0-9]{1,13}(\\.[0-9]*)?"))
						{	d=st; System.out.println(d);
                                                }
						
					
					}
				    
				    Lexer.variable.put(v,Double.parseDouble(d));
				}
				
				else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))//+[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))
				{
					flag=1;
					
					System.out.println(Lexer.variable);
			
					System.out.println("\n Arithmetic expression");
					t=new StringTokenizer(s);
                                        String st;
					st= t.nextToken();
					while(t.hasMoreTokens())
					{  
                                            st=t.nextToken();
                                        
                                            if(st.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                            {
                                                if(Lexer.variable.containsKey(st)==false|| Lexer.variable.get(st)==0.0)
                                                {
				    		  System.out.println("\n Undeclared variable present : "+ st);
				    		  break;
				    		  
                                                }
                                            }
                                        }
		
				}
			
				else if(s.matches("^def +[a-zA-Z_@$]([a-zA-Z0-9_])*(\\( ([a-zA-Z_@$]([a-zA-Z0-9_])* , )*[a-zA-Z_@$]([a-zA-Z0-9_])* \\))?$"))
				{
					System.out.println("\n Function definition");
				s=file.nextLine();
                                    endcheck = 0;
                                    
				    while(s!="end")
				    {
				    	
				    	
				    	/*if(s=="end")
				    	{
				    		System.out.println("End Definition");
				    		break;
				    	}
				    	*/
				    	System.out.println(s);
				    	if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[0-9]{1,13}(\\.[0-9]*)?$"))
						{   System.out.println("YES ASSIGNMENT");
							t=new StringTokenizer(s);
							String v=null,d=null;
							while(t.hasMoreTokens())
							{
								String st=t.nextToken();
								if(st.matches("[a-zA-Z_@$]([a-zA-Z0-9_])*"))
								{			v=st; System.out.println(v);}
								else if(st.matches("[0-9]{1,13}(\\.[0-9]*)?"))
								{	d=st; System.out.println(d); }
								
							
							}
						    
						    Lexer.variable.put(v,Double.parseDouble(d));
						}
						
						else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))//+[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))
						{
							flag=1;
							
							System.out.println(Lexer.variable);
							
							System.out.println("\n Arhithmetic expression");
							t=new StringTokenizer(s);
							
							while(t.hasMoreTokens())
							{  String st=t.nextToken();
						      if(st.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
						      {
						    	 if(Lexer.variable.containsKey(st)==false|| Lexer.variable.get(st)==0.0)
						    	  {
						    		  System.out.println("\n Undeclared variable " + st + " present. \n");
						    		  break;
						    		  
						    	  }
						      }
							}
                                                        }
                                        
                                         
                                                        s=file.nextLine();
                                                        if("end".equals(s))
				    	{
				    		System.out.println("End Definition");
                                                endcheck=1;
				    		break;
				    	}
                                                        
                                                       
                                                        
                                                        
		               
					    
				
				    }
                                    if(endcheck==0) System.out.println("ERROR Function doesnt have an end!");
				    
				}
                        
			System.out.println(flag);
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

   public static void main(String args[])
   {
	   parser p=new parser();
	   p.parsear();
   }

}
