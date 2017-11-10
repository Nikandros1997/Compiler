package Task2;

// Do not modify the code below except for replacing the "..."!  Don't
// add anything (including "public" declarations), don't remove
// anything (including "public" declarations). Don't wrap it in a
// package, don't make it an innner class of some other class.  If
// your IDE suggsts to change anything below, ignore your IDE. You are
// welcome to add new classes! Please put them into separate files.

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LexicalException extends Exception {
    public String msg;
    public LexicalException ( String _msg ) { msg = _msg; } } 

class Task2Exception extends Exception {
    public String msg;
    public Task2Exception ( String _msg ) { msg = _msg; } }

interface Lexer {
    public List<Token> lex ( String input ) throws LexicalException, Task2Exception; }

class Task2 {
    public static Lexer create () {
        return new Lexer() {
            @Override
            public List<Token> lex(String input) throws LexicalException, Task2Exception {

                if(input.isEmpty()) {
                    throw new LexicalException("Empty input...");
                }

                List<Token> tokenList = new ArrayList<>();

                Pattern p = Pattern.compile("def|if|then|else|skip|while|do|repeat"
                        + "|until|break|continue|;|\\(|\\)|==|=|<|>|<=|>=|,|\\{|\\}"
                        + "|:=|\\+|-|\\*|/|([a-z])([a-z]|[A-Z]|[0-9]|_)*|[0-9]+|\\w|.");
                Matcher token = p.matcher(input);

                while(token.find()) {
                    if (token.group().equals("def")) {
                        tokenList.add(new T_Def());
                    } else if (token.group().equals("if")) {
                        tokenList.add(new T_If());
                    } else if (token.group().equals("then")) {
                        tokenList.add(new T_Then());
                    } else if (token.group().equals("else")) {
                        tokenList.add(new T_Else());
                    } else if (token.group().equals("skip")) {
                        tokenList.add(new T_Skip());
                    } else if (token.group().equals("while")) {
                        tokenList.add(new T_While());
                    } else if (token.group().equals("do")) {
                        tokenList.add(new T_Do());
                    } else if (token.group().equals("repeat")) {
                        tokenList.add(new T_Repeat());
                    } else if (token.group().equals("until")) {
                        tokenList.add(new T_Until());
                    } else if (token.group().equals("break")) {
                        tokenList.add(new T_Break());
                    } else if (token.group().equals("continue")) {
                        tokenList.add(new T_Continue());
                    } else if (token.group().equals(";")) {
                        tokenList.add(new T_Semicolon());
                    } else if (token.group().equals("(")) {
                        tokenList.add(new T_LeftBracket());
                    } else if (token.group().equals(")")) {
                        tokenList.add(new T_RightBracket());
                    } else if (token.group().equals("==")) {
                        tokenList.add(new T_Equal());
                    } else if (token.group().equals("=")) {
                        tokenList.add(new T_EqualDefines());
                    } else if (token.group().equals("<")) {
                        tokenList.add(new T_LessThan());
                    } else if (token.group().equals(">")) {
                        tokenList.add(new T_GreaterThan());
                    } else if (token.group().equals("<=")) {
                        tokenList.add(new T_LessEq());
                    } else if (token.group().equals(">=")) {
                        tokenList.add(new T_GreaterEq());
                    } else if (token.group().equals(",")) {
                        tokenList.add(new T_Comma());
                    } else if (token.group().equals("{")) {
                        tokenList.add(new T_LeftCurlyBracket());
                    } else if (token.group().equals("}")) {
                        tokenList.add(new T_RightCurlyBracket());
                    } else if (token.group().equals(":=")) {
                        tokenList.add(new T_Assign());
                    } else if (token.group().equals("+")) {
                        tokenList.add(new T_Plus());
                    } else if (token.group().equals("-")) {
                        tokenList.add(new T_Minus());
                    } else if (token.group().equals("*")) {
                        tokenList.add(new T_Times());
                    } else if (token.group().equals("/")) {
                        tokenList.add(new T_Div());
                    } else if(Pattern.compile("[0-9]+").matcher(token.group()).matches()) {
                        tokenList.add(new T_Integer(Integer.parseInt(token.group())));
                    } else if(Pattern.compile("([a-z])([a-z]|[A-Z]|[0-9]|_)*").matcher(token.group()).matches()) {
                        tokenList.add(new T_Identifier(token.group()));
                    } else if(!Pattern.compile("\\s").matcher(token.group()).matches()) {
                        throw new LexicalException("There is a lexical error in " + token.group());
                    }
                }

                return tokenList;
            }
            
        };
    }
}
