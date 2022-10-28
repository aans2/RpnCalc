package teste.task2;

import java.util.ArrayList;

/**
 * 
 * @author Alex Alves
 *
 */
public class Tokenizador {
	public ArrayList<Token> tokens;
	
	public Tokenizador() {
		this.tokens = new ArrayList<Token>();
	}
	
	/**
	 * 
	 * @param expr
	 * @throws Exception
	 */
	public void criaToken(String expr) throws Exception{
		String aux = "";
		char val;
		for(int i = 0; i<expr.length(); i++) {
			val = expr.charAt(i);
			if(Character.isDigit(val)) {
				aux += val;
			}else if(val == ' ') {
				if(!aux.equals("")) {
					this.tokens.add(new Token(TokenType.NUM, aux));
					aux = "";
				}
			}else if(val == '+') {
				this.tokens.add(new Token(TokenType.PLUS, "+"));
			}else if(val == '-') {
				this.tokens.add(new Token(TokenType.MINUS, "-"));
			}else if(val == '*') {
				this.tokens.add(new Token(TokenType.STAR, "*"));
			}else if(val == '/'){
				this.tokens.add(new Token(TokenType.SLASH, "/"));
			}else {
				throw new Exception("Error: Unexpected character: " + val);
			}
		}
	}
}
