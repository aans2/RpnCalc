package postfix.interpreter;

import postfix.ast.Expr;

public class SemanticError extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SemanticError(Expr.Id expr) {
        super(expr.value + " cannot be resolved");
    }
}
