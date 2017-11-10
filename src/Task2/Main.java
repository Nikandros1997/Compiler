/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools   Templates
 * and open the template in the editor.
 */
package Task2;

import java.util.List;

/**
 *
 * @author nikandrosmavroudakis
 */
public class Main {
    
    public Main() {
        
    }
    
    public static void main(String[] args) throws Task2Exception {
        Lexer l = new LexerImplementation();
        try { 
           List<Token> tokens = l.lex("{ 1; 2; { 3; 4; 5;}; 6; { 7; { 8 }}}");
           
           
           for(int i = 0; i < tokens.size(); i++) {
                String s = "";
                
                if(tokens.get(i).getClass().getSimpleName().equals("T_Integer")) {
                    s = String.valueOf(((T_Integer) tokens.get(i)).n);
                }
                
                System.out.println("Token t"+ (i + 1)+" = new "+tokens.get(i).getClass().getSimpleName()+"("+s+");");
                System.out.println("list.add(t"+ (i + 1)+");");

        
           }
           
           
        } catch(LexicalException ex) {
            System.out.println(ex.msg);
            
        }
        
        
    }
}
