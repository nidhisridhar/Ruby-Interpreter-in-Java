 package rubyinterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;



public class parser {
	
    void parsear()
    {
        int ifflag=0;
        Lexer.lex();
        System.out.println(Lexer.variable);
        StringTokenizer t;
        try 
        {
            Scanner file = new Scanner(new File("ruby.txt"));
            String s;
            int flag=0,fl=0;
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
                if(s.matches("^if\\( +.* +\\)"))
                {
                    System.out.println("If statement");
                    ifflag=1;
                    StringTokenizer g=new StringTokenizer(s);
                    String h;
                    g.nextToken();
                    h=g.nextToken();
                    System.out.println(h);
                    String p="",q="";
               	    g.nextToken();
                    while(g.hasMoreTokens())
                    {
               		 p=g.nextToken();
               		 if(p.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
               		 {
                            int x=Lexer.variable.get(p).intValue();
                            q= q + x + " "; 
                            System.out.println("Yes1");
               		 }
                         else if(p.matches("[/+-/*///^]"))
               		 {
                             q= q + p + " ";
                            System.out.println("Yes2");
               		 }
               		 else if (p.matches("[0-9*]"))
               		 {
                            q= q + p + " ";
                            System.out.println("Yes3");
               		 }
               	 }
               	 
               	 System.out.println("The string is :- " + q);
               	 EvaluateString ep= new EvaluateString();
               	 System.out.println("Th solution is:- " + ep.evaluate(q));
                 if (Lexer.variable.get(h).intValue()==ep.evaluate(q))
                 {
                    System.out.println("If statement is satisfied!");
                    s=file.nextLine();
                    endcheck = 0;
                    while(s!="end")
                    {
                        System.out.println(s);
                        if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[0-9]{1,13}(\\.[0-9]*)?$"))
                        {   
                            System.out.println("YES ASSIGNMENT");
                            t=new StringTokenizer(s);
                            String v=null,d=null;
                            while(t.hasMoreTokens())
                            {
                                String st=t.nextToken();
                                if(st.matches("[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                {            
                                    v=st; 
                                    System.out.println(v);
                                }
                                else if(st.matches("[0-9]{1,13}(\\.[0-9]*)?"))
                                {    
                                    d=st;
                                    System.out.println(d);
                                }
                            }
                            Lexer.variable.put(v,Double.parseDouble(d));
                        }
         
                        else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[a-zA-Z_@$]([a-zA-Z0-9_])*( +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*)*$"))//+[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))
                         {
                            flag=1;
                            System.out.println(Lexer.variable);
                            System.out.println("\n Arhithmetic expression");
                            t=new StringTokenizer(s);
                            System.out.println("\n Arhithmetic expression");
                            t=new StringTokenizer(s);
                            String st;
                            st = t.nextToken();
                            while(t.hasMoreTokens())
                            {  
                                st=t.nextToken();
                                
                                if(st.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                {
                                    if(Lexer.variable.containsKey(st)==false|| Lexer.variable.get(st)==0.0)
                                    {
                                        System.out.println("\n Undeclared variable " + st + " present. \n");
                                        break;
                                    }
                               }
                         }

                         System.out.println("fl is "+ fl);
                         if(fl==0)
                            {
                                System.out.println("fl is 0!!");
                                String b="",c="",a="";
                                StringTokenizer tt= new StringTokenizer(s);
                                a= tt.nextToken();
                                tt.nextToken();
                                while(tt.hasMoreTokens())
                                {
                                        b=tt.nextToken();

                                        if(b.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                        {
                                               int x=Lexer.variable.get(b).intValue();
                                               c= c + x + " "; 
                                               System.out.println("Yes1");
                                        }

                                        else if(b.matches("[/+-/*///^]"))
                                        {
                                                c= c + b+ " ";
                                                System.out.println("Yes2");
                                        }

                                        else if (b.matches("[0-9*]"))
                                        {
                                                c= c + b + " ";
                                                System.out.println("Yes3");
                                        }
                             }

                                System.out.println("The string is : " + c);
                                EvaluateString ev= new EvaluateString();
                                System.out.println("Th solution is: " + ev.evaluate(c));
                                Lexer.variable.put(a,(double) ev.evaluate(c) ); 
                                System.out.println(Lexer.variable);
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
           if(endcheck==0) System.out.println("ERROR if statement  doesnt have an end!");



                         }

                        }

                        if(s.matches("^else$"))
                        {
                                if(ifflag==0) System.out.println("Dangling else.");
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
                                {            v=st; System.out.println(v);
                                                        }
                                else if(st.matches("[0-9]{1,13}(\\.[0-9]*)?"))
                                {    d=st; System.out.println(d);
                                                        }


                            }

                            Lexer.variable.put(v,Double.parseDouble(d));
                        }
                
                else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[a-zA-Z_@$]([a-zA-Z0-9_])*( +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*)*$"))//+[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))
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
                            fl=1;
                            break;
                          }
                        }
                    }
                    
                    System.out.println("fl is "+ fl);
                    if(fl==0)
                    	{
                    	 System.out.println("fl is 0!!");
                    	 String b="",c="",a="";
                    	 StringTokenizer tt= new StringTokenizer(s);
                    	 a= tt.nextToken();
                    	 tt.nextToken();
                    	 while(tt.hasMoreTokens())
                    	 {
                    		 b=tt.nextToken();
                    		 
                    		 if(b.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                    		 {
                    			int x=Lexer.variable.get(b).intValue();
                    			c= c + x + " "; 
                    			System.out.println("Yes1");
                    		 }
                    		 
                    		 else if(b.matches("[/+-/*///^]"))
                    		 {
                    			 c= c + b+ " ";
                    			 System.out.println("Yes2");
                    		 }
                    		 
                    		 else if (b.matches("[0-9*]"))
                    		 {
                    			 c= c + b + " ";
                    			 System.out.println("Yes3");
                    		 }
                    	 }
                    	 
                    	 System.out.println("The string is :- " + c);
                    	 EvaluateString ev= new EvaluateString();
                    	System.out.println("Th solution is:- " + ev.evaluate(c));
                    	Lexer.variable.put(a,(double) ev.evaluate(c) ); 
                    	System.out.println(Lexer.variable);
                    	}
        
                }
            
                else if(s.matches("^def +[a-zA-Z_@$]([a-zA-Z0-9_])*(\\( ([a-zA-Z_@$]([a-zA-Z0-9_])* , )*[a-zA-Z_@$]([a-zA-Z0-9_])* \\))?$"))
                {
                    System.out.println("\n Function definition");
                    s=file.nextLine();
                    endcheck = 0;
                    while(s!="end")
                    {
                        System.out.println(s);
                        if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[0-9]{1,13}(\\.[0-9]*)?$"))
                        {   
                            System.out.println("YES ASSIGNMENT");
                            t=new StringTokenizer(s);
                            String v=null,d=null;
                            while(t.hasMoreTokens())
                            {
                                String st=t.nextToken();
                                if(st.matches("[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                { 
                                    v=st;
                                    System.out.println(v);
                                }
                                else if(st.matches("[0-9]{1,13}(\\.[0-9]*)?"))
                                {    
                                    d=st;
                                    System.out.println(d); 
                                }
                             
                            }
                            
                            Lexer.variable.put(v,Double.parseDouble(d));
                        }
                        
                        else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[a-zA-Z_@$]([a-zA-Z0-9_])*( +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*)*$"))//+[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))
                        {
                            flag=1;
                            System.out.println(Lexer.variable);
                            System.out.println("\n Arithmetic expression");
                            t=new StringTokenizer(s);
                            while(t.hasMoreTokens())
                            { 
                                String st=t.nextToken();
                                if(st.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                {
                                 if(Lexer.variable.containsKey(st)==false|| Lexer.variable.get(st)==0.0)
                                 {
                                      System.out.println("\n Undeclared variable " + st + " present. \n");
                                      break;
                                      
                                 }
                              }
                            }
                            
                            System.out.println("fl is "+ fl);
                            if(fl==0)
                            	{
                                        System.out.println("fl is 0!!");
                                        String b="",c="",a="";
                                        StringTokenizer tt= new StringTokenizer(s);
                                        a= tt.nextToken();
                                        tt.nextToken();
                                        while(tt.hasMoreTokens())
                                        {
                                                b=tt.nextToken();

                                                if(b.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                                {
                                                       int x=Lexer.variable.get(b).intValue();
                                                       c= c + x + " "; 
                                                       System.out.println("Yes1");
                                                }

                                                else if(b.matches("[/+-/*///^]"))
                                                {
                                                        c= c + b+ " ";
                                                        System.out.println("Yes2");
                                                }

                                                else if (b.matches("[0-9*]"))
                                                {
                                                        c= c + b + " ";
                                                        System.out.println("Yes3");
                                                }
                                        }

                                        System.out.println("The string is :- " + c);
                                        EvaluateString ev= new EvaluateString();
                                       System.out.println("Th solution is:- " + ev.evaluate(c));
                                       Lexer.variable.put(a,(double) ev.evaluate(c) ); 
                                       //System.out.println(Lexer.variable);
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
                        
            //System.out.println(flag);
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