package postfix.ast;

import postfix.ast.Expr.Binop;
import java.util.Stack;
import postfix.ast.Expr.Number;

public class PrintAst implements Expr.Visitor<String>{

	public String print(Expr expr) {
		return expr.accept(this);
	}

	@Override
	public String visitNumberExpr(Number expr) {
		return expr.value.toString();
	}

	@Override
	public String visitBinopExpr(Binop expr) {
		return parenthesizePreOrder(expr.operator.lexeme,
				expr.left, expr.right);
	}

	@Override
	public String visitIdExpr(Expr.Id expr) {
		return expr.value.toString();
	}

	public boolean isBalancedParantheses(String expr) {
		if (expr.isEmpty()) { 
			return true; 
		}

		Stack<Character> pilha = new Stack<Character>();
		for (int i = 0; i < expr.length(); i++) {
			char atual = expr.charAt(i);
			if (atual == '(') {
				pilha.push(atual);
			}
			if (atual == ')') {
				if (pilha.isEmpty()) { 
					return false; 
				}
				char last = pilha.peek();
				if (atual == ')' && last == '(') {
					pilha.pop();
				}
				else { 
					return false; 
				}
			}
		}

		return pilha.isEmpty()?true:false;
	}

	private String parenthesizePreOrder(String name, Expr... oprs) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("(").append(name);
		for (Expr expr : oprs) {
			buffer.append(" ");
			buffer.append(expr.accept(this));
		}
		buffer.append(")");

		return buffer.toString();
	}

}
