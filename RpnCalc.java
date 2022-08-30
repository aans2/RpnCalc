package teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;


public class RpnCalc {
	
    private static Stack<Integer> buffer = new Stack<>();
    
    // Checa se é o numero do tipo inteiro
    public static boolean isInt(String input) {
        if (input == null) return false;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    
    // Checa se é uma operacao reconhecida
    public static boolean isOperation(String input) {
        if (input == null) return false;

        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }
    
    // Tratando entradas inválidas. Avisar que não é possivel dividir por 0
    // mantén o último número que estava na pilha.
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
    
    // Cada operação depende de 2 números. Se há apenas 1 item na pilha
    // ao se requisitar a operação, nada deve ocorrer e o número deve
    // retornar à pilha. Se não há nada na pilha, o número 0 é colocado.
    // O retorno deste método é adicionado na pilha.
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


