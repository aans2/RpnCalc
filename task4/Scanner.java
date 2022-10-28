package teste.task4;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Scanner {

    private final String source;
    private final ArrayList<Token> tokens = new ArrayList<>();

    public Scanner(String source) {
        this.source = source;
    }

    public ArrayList<Token> scan() {
        return this.scan(this.source);
    }

    public ArrayList<Token> scan(String program) {
        StringTokenizer tokenizer = new StringTokenizer(program, Token.TOKENIZER_DELIMITER);
        while (tokenizer.hasMoreElements()) {
            String tokenStr = tokenizer.nextToken();
            this.tokens.add(this.getToken(tokenStr));
        }
        this.tokens.add(new Token(TokenType.EOF, "")); // EOF

        return this.tokens;
    }

    private Token getToken(String token) {
        Token output = null;
        if (Regex.isNum(token)) {
            output = new Token(TokenType.NUM, token);
        } else if (Regex.isID(token)) {
            output = new Token(TokenType.ID, token);
        } else if(Regex.isOP(token)) {
            output = new Token(Regex.getOPTokenType(token), token);
        } else {
            throw new RegexError("Unexpected character: "+token);
        }

        return output;
    }
}