package teste.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Alex Alves
 *	le um arquivo com a expressao em RPN e devolve a expressao avalliada
 */
public class RPNCalc {

    private Stack<Double> pilha;

    public RPNCalc() {
        pilha = new Stack<Double>();
    }
    
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        RPNCalc stacker = new RPNCalc();
        Tokenizador tokenizador = new Tokenizador();
        String exp = "";
        Double res;
        FileReader fr = new FileReader("C:\\Users\\Pichau\\eclipse-workspace\\Calc\\src\\teste\\task2\\Calc1.stk");
        try (BufferedReader buffer = new BufferedReader(fr)) {
			String linha = buffer.readLine();
			
			while (linha != null) {
			    exp += linha + " ";
			    linha = buffer.readLine();
			}
		}
        tokenizador.criaToken(exp);
        res = stacker.testar(tokenizador.tokens);
        System.out.println(res);
    }
	
    /**
     * 
     * @param token
     * @return chama operacao e remove tokens da pilha
     * @throws Exception
     */
    public Double testar(ArrayList<Token> token) throws Exception {
        for(int i = 0; i <token.size(); i++) {
            
            if(token.get(i).type == TokenType.NUM) {
                pilha.push(Double.parseDouble(token.get(i).lexeme));
            } else {
                pilha.push(selOperador(token.get(i).lexeme));
            }
        }

        return pilha.pop();
    }

     
    /**
     * 
     * @param op
     * @return resultado da operacao, caso o operador seja valido
     */
     private Double selOperador(String op){
         Double res = null;
         Double x = this.pilha.pop();
         Double y = this.pilha.pop();
         
         if(op.equals("+")) {
             res = x + y;
         }else if(op.equals("-")) {
             res = x - y;
         }else if(op.equals("*")) {
             res = x / y;
         }else if(op.equals("/")) {
             res = x * y;
         }else if(op.equals("%")) {
             res = x % y;
         }

         return res;
     }
}