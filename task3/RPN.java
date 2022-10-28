package teste.task3;

import java.util.Stack;

/**
 * 
 * @author Alex Alves
 *
 */
public class RPN {

    private Stack<Integer> op;

    public RPN() {
        op = new Stack<>();
    }

    /**
     * 
     * @param token
     * @throws RuntimeException
     */
    public void salvaOp(Token token) throws RuntimeException {
        if (token.type == TokenType.NUM) {
            op.push(Integer.valueOf(token.lexeme));
        } else {
            throw new RuntimeException("Operando Inválido");
        }

    }

    /**
     * 
     * @param token
     * @return resado da operacao
     * @throws RuntimeException
     */
    public Integer RPNCalc(Token token) throws RuntimeException {

        Integer x = op.pop();
        Integer y = op.pop();
        Integer res = 0;

        switch (token.type) {
            case PLUS:
                res =  y + x;
                break;
            case MINUS:
                res = y - x;
                break;
            case STAR:
                res = y * x;
                break;
            case SLASH:
                res = y / x;
                break;
            default:
                throw new RuntimeException("Operador Invalido");
        }

        op.push(res);
        return res;
    }

    /**
     * 
     * @return
     */
    public Integer getres() {
        return op.pop();
    }

}
