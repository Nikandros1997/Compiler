/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task3;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;

/**
 *
 * @author nikandrosmavroudakis
 */
public class ParserImplementation implements Parser {

    List<Token> input;
    BlockExp previousBlock;
    
    public ParserImplementation() {
        input = new ArrayList();
    }

    @Override
    public Block parse(List<Token> input) throws SyntaxException {
        
        if(input.size() == 0) {
            throw new SyntaxException("No code provided");
        }
        
        this.input = input;
        
        input.add(0, new T_RightCurlyBracket());
        //input.add(0, new T_LeftCurlyBracket());
        /*
        for(int i = 0; i  < input.size(); i++)
            System.out.println(input.get(i));
        */
        Block b = new Block(discoverLevel(new ArrayList()));
        
        
        
        return b;
    }
    
    public List<Exp> discoverLevel(List<Exp> block) throws SyntaxException {
        Token token = null;
        
        if(input.size() != 0) {
            token = input.remove(0);
            
            if(token instanceof T_LeftCurlyBracket) {
                BlockExp b = new BlockExp(new Block(discoverLevel(new ArrayList())));
                block.add(b);
            } if(token instanceof T_Integer) {
                block.add(new IntLiteral(((T_Integer) token).n));
                 discoverLevel(block);
            } else if(token instanceof T_Skip) {
                block.add(new Skip());
                 discoverLevel(block);
            } else if(token instanceof T_RightCurlyBracket) {
                discoverLevel(block);
            } else if(token instanceof T_Semicolon) {
                 discoverLevel(block);
            }
        }
        return block;
    }
    
    public BlockExp createLevel(List<Exp> block) {
        return new BlockExp(new Block(block));
    }
    
    public void setPreviousBlock(BlockExp previousBlock) {
        this.previousBlock = previousBlock;
    }
    
    public BlockExp getPreviousBlock() {
        return previousBlock;
    }
}
