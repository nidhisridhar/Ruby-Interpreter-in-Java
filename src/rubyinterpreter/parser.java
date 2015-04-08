 package rubyinterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class parser {
    StringTokenizer t;
        Stack str=new Stack();
        int ifelseflag=0;
        int scopecount;
         int endcheck;
       Scanner file;
       
            String s;
            int flag=0,fl=0;
            void assignment()
            {
                 System.out.println(" ASSIGNMENT STATEMENT");
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
        void arithmetic()
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
                    
                 //   System.out.println("fl is "+ fl);
                    if(fl==0)
                    	{
                    	// System.out.println("fl is 0!!");
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
                    			//System.out.println("Yes1");
                    		 }
                    		 
                    		 else if(b.matches("[/+-/*///^]"))
                    		 {
                    			 c= c + b+ " ";
                    			 //System.out.println("Yes2");
                    		 }
                    		 
                    		 else if (b.matches("[0-9]*"))
                    		 {
                    			 c= c + b + " ";
                    			 //System.out.println("Yes3");
                    		 }
                    	 }
                    	 
                    	 System.out.println("The string to be evaluated is :- " + c);
                    	 EvaluateString ev= new EvaluateString();
                    	System.out.println("The solution is:- " + ev.evaluate(c));
                    	Lexer.variable.put(a,(double) ev.evaluate(c) ); 
                    	System.out.println(Lexer.variable);
                    	
                    	}
        }
    void deffunc()
    {
        System.out.println("\n Function definition");
                    scopecount=0;
                    s=file.nextLine();
                    endcheck = 0;
                    while(s!="end")
                    {
                        System.out.println(s);
                        if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[0-9]{1,13}(\\.[0-9]*)?$"))
                        {   
                             scopecount++;
                            System.out.println("ASSIGNMENT STATEMENT");
                           
                            t=new StringTokenizer(s);
                            String v=null,d=null;
                            while(t.hasMoreTokens())
                            {
                                String st=t.nextToken();
                                if(st.matches("[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                { 
                                    v=st;
                                    str.push(v);
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
                        
                        else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +(([a-zA-Z_@$]([a-zA-Z0-9_])*)|([0-9]*))( +[/+-/*///^] +(([a-zA-Z_@$]([a-zA-Z0-9_])*)|([0-9]*)))*$"))
                        {
                            flag=1;
                            System.out.println(Lexer.variable);
                            System.out.println("\n Arithmetic expression");
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
                                        fl = 1;
                                        break;
                                        
                                    }
                               }
                         }

                         System.out.println("fl is "+ fl );
                         if(fl == 1)break;
                         else if(fl==0)
                            {
                                System.out.println("fl is 0!!");
                                String b="",c="",a="";
                                StringTokenizer tt= new StringTokenizer(s);
                                a= tt.nextToken();
                                scopecount++;
                                str.push(a);
                                tt.nextToken();
                                while(tt.hasMoreTokens())
                                {
                                        b=tt.nextToken();

                                        if(b.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                        {
                                               int x=Lexer.variable.get(b).intValue();
                                               c= c + x + " "; 
                                               //System.out.println("Yes1");
                                               
                                        }

                                        else if(b.matches("[/+-/*///^]"))
                                        {
                                                c= c + b+ " ";
                                                //System.out.println("Yes2");
                                        }
                                         else if (b.matches("[0-9]*"))
                                        {
                                        //System.out.println(b);
                                        c= c + b + " ";
                                            //System.out.println("Yes3");
                                        }
                             }

                                System.out.println("The string is : " + c);
                                EvaluateString ev= new EvaluateString();
                                System.out.println("The solution is: " + ev.evaluate(c));
                                Lexer.variable.put(a,(double) ev.evaluate(c) ); 
                                System.out.println(Lexer.variable);
                            }

                      }
                         else
                            System.out.println("Cannot Interpret this Line");
                             s=file.nextLine();
                              if("end".equals(s))
                        {
                            System.out.println("End Definition");
                                                endcheck=1;
                            break;
                        }
                       
                    }
                  if(endcheck==0) System.out.println("ERROR Function doesnt have an end!");
                 int i;
                 for(i=0;i<scopecount;i++)
                 {
                	 String g=(String)str.pop();
                	 System.out.println(g);
                	 Lexer.variable.put(g, 0.0);
                 }
                  scopecount=0;
    }
    void checknotif()
    {
        System.out.println("If statement not satisfied.");
        ifelseflag=2;
        s=file.nextLine();
        while(!s.equals("end"))
        s=file.nextLine();
    }
    void checkif()
    {
            System.out.println("If statement is satisfied!");
            ifelseflag=1;
            s=file.nextLine();
            endcheck = 0;
            scopecount=0;

            while(!"end".equals(s))
            {
                System.out.println(s);
                if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[0-9]{1,13}(\\.[0-9]*)?$"))
                {   
                    scopecount++;        
                        System.out.println("ASSIGNMENT STATEMENT");
                    t=new StringTokenizer(s);
                    String v=null,d=null;
                    while(t.hasMoreTokens())
                    {
                        String st=t.nextToken();
                        if(st.matches("[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                        {        

                            v=st;
                            str.push(v);
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

                else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +(([a-zA-Z_@$]([a-zA-Z0-9_])*)|([0-9]*))( +[/+-/*///^] +(([a-zA-Z_@$]([a-zA-Z0-9_])*)|([0-9]*)))*$"))
                 {
                    flag=1;
                    System.out.println(Lexer.variable);
                    System.out.println("\n ARITHMETIC EXPRESSION");
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
                                System.out.println("\n Undeclared Variable  " + st + " present. OR Out of Scope Variable\n");
                                fl = 1;
                                break;

                            }
                       }
                 }

                 
                 if(fl == 1)break;
                 else if(fl==0)
                    {
                        
                        String b="",c="",a="";
                        StringTokenizer tt= new StringTokenizer(s);
                        a= tt.nextToken();
                        scopecount++;
                        str.push(a);
                        tt.nextToken();
                        while(tt.hasMoreTokens())
                        {
                                b=tt.nextToken();

                                if(b.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
                                {
                                       int x=Lexer.variable.get(b).intValue();
                                       c= c + x + " "; 
                                      // System.out.println("Yes1");
                                }

                                else if(b.matches("[/+-/*///^]"))
                                {
                                        c= c + b+ " ";
                                        //System.out.println("Yes2");
                                }
                                 else if (b.matches("[0-9]*"))
                                {
                                    //System.out.println(b);
                                    c= c + b + " ";
                                    //System.out.println("Yes3");
                                }
                     }

                        System.out.println("The string to be evaluated is : " + c);
                        EvaluateString ev= new EvaluateString();
                        System.out.println("The solution is: " + ev.evaluate(c));
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
           if(endcheck==0) System.out.println("ERROR if Statement Doesnt Have An End!");
            int i;
           for(i=0;i<scopecount;i++)
           {
          	 String f=(String)str.pop();
          	 System.out.println(f);
          	 Lexer.variable.put(f, 0.0);
           }
            scopecount=0;

    }
    void parsear()
    {
        int ifflag=0;
       // Lexer.lex();
        System.out.println(Lexer.variable);
        
        try 
        {
        	
           file = new Scanner(new File("ruby.txt"));
            while(file.hasNextLine())
            {   
                s=file.nextLine();
                System.out.println(s);
               
                if(s.equals("end"))
                {
                    System.out.println("ERROR:End Defined without a function definition");
                    break;
                }
                if(s.matches("^if\\( +.* +\\)"))
                {
                    System.out.println("If STATEMENT");
                    ifflag=1;
                    StringTokenizer g=new StringTokenizer(s);
                    String h;
                    String comparator;
                    g.nextToken();
                    h=g.nextToken();
                    System.out.println(h);
                    String p="",q="";
               	    comparator = g.nextToken();
                    System.out.println(comparator);
                    while(g.hasMoreTokens())
                    {
                        
               		 p=g.nextToken();
               		 if(p.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])*"))
               		 {
                            int x=Lexer.variable.get(p).intValue();
                            q= q + x + " "; 
                           // System.out.println("Yes1");
               		 }
                         else if(p.matches("[/+-/*///^]"))
               		 {
                             q= q + p + " ";
                            //System.out.println("Yes2");
               		 }
               		 else if (p.matches("[0-9]*"))
               		 {
                            q= q + p + " ";
                          //  System.out.println("Yes3");
               		 }
               	 }
               	 
               	 System.out.println("The string is :- " + q);
               	 EvaluateString ep= new EvaluateString();
               	 System.out.println("The solution is:- " + ep.evaluate(q));
                 if(comparator.equals("=="))
                 {
                 if (Lexer.variable.get(h).intValue()==ep.evaluate(q) && Lexer.variable.get(h)!=0)
                 {
                   checkif();
                 }
                 else 
                 {
                	System.out.println("If statement not satisfied.");
                	ifelseflag=2;
                        s=file.nextLine();
                        while(!s.equals("end"))
                        s=file.nextLine();
                        
                 }
                 
                 }
                 else if(comparator.equals(">="))
                 {
                 if (Lexer.variable.get(h).intValue()>=ep.evaluate(q) && Lexer.variable.get(h)!=0)
                 {
                   checkif();
                 }
                 else 
                 {
                	checknotif();
                 }
                 
                 }
                 else if(comparator.equals("<="))
                 {
                 if (Lexer.variable.get(h).intValue()<=ep.evaluate(q) && Lexer.variable.get(h)!=0)
                 {
                   checkif();
                 }
                 else 
                 {
                    checknotif();
                 }
                 
                 }
                 else if(comparator.equals("!="))
                 {
                 if (Lexer.variable.get(h).intValue()!=ep.evaluate(q) && Lexer.variable.get(h)!=0)
                 {
                   checkif();
                 }
                 else 
                 {
                	 checknotif();
                       
                 }
                 
                 }
                 else if(comparator.equals(">"))
                 {
                 if (Lexer.variable.get(h).intValue()>ep.evaluate(q) && Lexer.variable.get(h)!=0)
                 {
                   checkif();
                 }
                 else 
                 {
                	 checknotif();
                       
                 }
                 
                 }
                 else if(comparator.equals("<"))
                 {
                 if (Lexer.variable.get(h).intValue()<ep.evaluate(q) && Lexer.variable.get(h)!=0)
                 {
                   checkif();
                 }
                 else 
                 {
                	checknotif();
                       
                 }
                 
                 }     
                }

                        if(s.matches("^else$") && ifelseflag==2)
                        {
                                if(ifflag==0) System.out.println("Dangling else.");
                                break;
                        }
                         

                        if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +[0-9]{1,13}(\\.[0-9]*)?$"))
                        {  
                           assignment();
                        }
                
                else if(s.matches("^[a-zA-Z_@$]([a-zA-Z0-9_])* += +(([a-zA-Z_@$]([a-zA-Z0-9_])*)|([0-9]*))( +[/+-/*///^] +(([a-zA-Z_@$]([a-zA-Z0-9_])*)|([0-9]*)))*$"))//+[a-zA-Z_@$]([a-zA-Z0-9_])* +[/+-/*///^] +[a-zA-Z_@$]([a-zA-Z0-9_])*$"))
                {
                    arithmetic();
                
                }
               
            
                else if(s.matches("^def +[a-zA-Z_@$]([a-zA-Z0-9_])*(\\( ([a-zA-Z_@$]([a-zA-Z0-9_])* , )*[a-zA-Z_@$]([a-zA-Z0-9_])* \\))?$"))
                {
                   deffunc();
                }
                else if(s.equals("end")){}
                else
                System.out.println("Cannot Interpret this Line: "+ s);
                        
            //System.out.println(flag);
            }

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
    }

  /* public static void main(String args[])
   {
       parser p=new parser();
       p.parsear();
   }
*/
}