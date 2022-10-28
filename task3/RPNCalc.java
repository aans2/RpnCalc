package teste.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Alex Alves
 *	le um arquivo com a expressao em RPN e devolve a expressao avalliada
 */
public class RPNCalc {

	 public static void main(String[] args) {

	        try {
	            ArrayList<Token> tokens = busca("C:\\Users\\Pichau\\eclipse-workspace\\Calc\\src\\teste\\task3\\Calc1.stk");
	            RPN calc = new RPN();
	            int res;

	            while (!tokens.isEmpty()) {
	                Token token = tokens.remove(0);
	                System.out.println(token);

	                if (token.type == TokenType.NUM) {
	                    calc.salvaOp(token);
	                } else if (token.type != TokenType.NUM) {
	                    calc.RPNCalc(token);
	                }
	            }

	            res = calc.getres();
	            System.out.println("\nSaida: " + res + "\n");
	        } catch (FileNotFoundException e) {
	            System.out.println("Arquivo não encontrado");
	        } catch (RuntimeException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 /**
	  * 
	  * @param filename
	  * @return
	  * @throws FileNotFoundException
	  */
	 private static ArrayList<Token> busca(String filename) throws FileNotFoundException {
	        File file = new File(filename);

	        try (Scanner scan = new Scanner(file)) {
				ArrayList<Token> tokens = new ArrayList<>();

				while (scan.hasNextLine()) {
				    String line = scan.nextLine().trim();

				    Token token;

				    if(Regex.isNum(line)) {
				        token = new Token(TokenType.NUM, line);
				    } else if (Regex.isOP(line)) {
				        token = new Token(Regex.getOPTokenType(line), line);
				    } else {
				        throw new RuntimeException("Unexpected character: "+ line);
				    }

				    tokens.add(token);
				}

				scan.close();

				return tokens;
			}
	    }
}
