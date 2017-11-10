/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikandrosmavroudakis
 */
public class main {
    
    public main() {
        
        ArrayList<Token> list = new ArrayList();

Token t1 = new T_LeftCurlyBracket();
list.add(t1);
Token t2 = new T_Integer(1);
list.add(t2);
Token t3 = new T_Semicolon();
list.add(t3);
Token t4 = new T_Integer(2);
list.add(t4);
Token t5 = new T_Semicolon();
list.add(t5);
Token t6 = new T_LeftCurlyBracket();
list.add(t6);
Token t7 = new T_Integer(3);
list.add(t7);
Token t8 = new T_Semicolon();
list.add(t8);
Token t9 = new T_Integer(4);
list.add(t9);
Token t10 = new T_Semicolon();
list.add(t10);
Token t11 = new T_Integer(5);
list.add(t11);
Token t12 = new T_Semicolon();
list.add(t12);
Token t13 = new T_RightCurlyBracket();
list.add(t13);
Token t14 = new T_Semicolon();
list.add(t14);
Token t15 = new T_Integer(6);
list.add(t15);
Token t16 = new T_Semicolon();
list.add(t16);
Token t17 = new T_LeftCurlyBracket();
list.add(t17);
Token t18 = new T_Integer(7);
list.add(t18);
Token t19 = new T_Semicolon();
list.add(t19);
Token t20 = new T_LeftCurlyBracket();
list.add(t20);
Token t21 = new T_Integer(8);
list.add(t21);
Token t22 = new T_RightCurlyBracket();
list.add(t22);
Token t23 = new T_RightCurlyBracket();
list.add(t23);
Token t24 = new T_RightCurlyBracket();
list.add(t24);
        
        Parser p = Task3.create();
        
        try {
            Block b = p.parse(list);
            //System.out.println(b.exps);
            output(b.exps, 0);
            
        } catch (SyntaxException ex) {
            System.err.println(ex.msg);
        } catch (Task3Exception ex) {
            System.err.println(ex.msg);
        }
    }
    
    public void output(List<Exp> exp, int k) {
        //System.out.println(((BlockExp) exp.get(0)).b.exps);
        for(int i = 0; i < exp.size(); i++) {
            if(exp.get(i) instanceof BlockExp) {
                for(int j = 0; j < k; j++)
                    System.out.print("\t");
                System.out.println(exp.get(i));
                k++;
                output(((BlockExp) exp.get(i)).b.exps, k);
            } else {
                for(int j = 0; j < k; j++)
                    System.out.print("\t");
                System.out.println(exp.get(i) + " ");
            }
        }
    }
    
    public static void main(String[] args) {
        
        new main();
    }
}
