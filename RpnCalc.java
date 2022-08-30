package teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;


public class RpnCalc {
	
    private static Stack<Integer> buffer = new Stack<>();
    
    // Checa se � o numero do tipo inteiro
    public static boolean isInt(String input) {
        if (input == null) return false;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    
    // Checa se � uma operacao reconhecida
    public static boolean isOperation(String input) {
        if (input == null) return false;

        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }
    
    // Tratando entradas inv�lidas. Avisar que n�o � possivel dividir por 0
    // mant�n o �ltimo n�mero que estava na pilha.
    public static int operate(String operation, int left, int right) {
        switch (operation) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": {
                if (right == 0) {
                    System.out.println("ERROR: Nao pode dividir por 0.");
                    return left;
                }
                return left / right;
            }
            default: return left;
        }
    }
    
    // Cada opera��o depende de 2 n�meros. Se h� apenas 1 item na pilha
    // ao se requisitar a opera��o, nada deve ocorrer e o n�mero deve
    // retornar � pilha. Se n�o h� nada na pilha, o n�mero 0 � colocado.
    // O retorno deste m�todo � adicionado na pilha.
    public static int parseOp(String operation, Stack<Integer> buffer) {
        int result = (buffer.empty()) ? 0 : buffer.pop();

        if (!buffer.empty()) {
            result = operate(operation, buffer.pop(), result);
        }

        return result;
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader buffRead = new BufferedReader(new FileReader("src/teste/Calc1.stk"));
		String linha = "";
		
		while (true) {
			
			linha = buffRead.readLine();
			if (linha != null) {
				 if (isInt(linha)) {
					 buffer.push(Integer.parseInt(linha));
				 } else if (isOperation(linha)) {
					 int current = parseOp(linha, buffer);
					 if (buffer.size() == 0) {
						 System.out.println("Result: " + current);
						 } else {
							 System.out.println("Result: " + current);
							 }
					 buffer.push(current);
					 }
			} else
				break;
		}
		buffRead.close();

	}

}


