package teste.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Alex Alves
 *	Le um arquivo com a expressao em RPN e avalia
 */
public class RPNCalc {
    private Stack<Integer> pilha;

    public RPNCalc() {
    	pilha = new Stack<Integer>();
    }
    
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        String exp = "";
        int res;
        RPNCalc aval = new RPNCalc();
        FileReader fr = new FileReader("C:\\Users\\Pichau\\eclipse-workspace\\Calc\\src\\teste\\task1\\Calc1.stk");
        
        try (BufferedReader lerArq = new BufferedReader(fr)) {
			String linha = lerArq.readLine();
			while (linha != null) {
			    exp = exp + linha + " ";
			    linha = lerArq.readLine(); //
			  }
		}
        
        res = aval.testar(exp);
        System.out.println(res);
   }
    
    /**
     * 
     * @param expr
     * @return resultado da operacao
     */
	public int testar(String expr){
        int x, y, res = 0;
        String token;
        
        try (Scanner scaner = new Scanner(expr)) {
			while (scaner.hasNext())        
			{
			    token = scaner.next();          

			    if (validaOperador(token))
			    {
			        y = (pilha.pop()).intValue();
			        x = (pilha.pop()).intValue();
			        res = selOperador(token.charAt(0), x, y);     //
			        pilha.push(res);
			    }
			    else
			    	pilha.push(Integer.parseInt(token));       
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return res;
    }

	/**
	 * 
	 * @param token
	 * @return true caso seja um operador valido
	 */
     private boolean validaOperador(String token){
            return ( token.equals("+") || token.equals("-") ||
                     token.equals("*") || token.equals("/") || token.equals("%") );
        }
    
     /**
      * 
      * @param op
      * @param x
      * @param y
      * @return resultado da expressao caso operador seja valido
      */
     private int selOperador(char op, int x, int y){
            int res = 0;
            
            if(op == '+') {
                res = x + y;
            }else if(op == '-') {
                res = x - y;
            }else if(op == '/') {
                res = x / y;
            }else if(op == '*') {
                res = x * y;
            }else if(op == '%') {
                res = x % y;
            }

            return res;
        }
}