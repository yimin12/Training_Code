package src.template.OOD.impl.calculator;

public class ExpressionTokenizer {

    public String expression;
    public int count;
    ExpressionToken token;

    public ExpressionTokenizer(String expression) {
        this.expression = expression;
        this.count = 0;
        this.token = null;
    }

    public ExpressionToken nextToken() throws Exception {
        if (expression == null || expression.isEmpty()) throw new IllegalArgumentException("invalid expression");
        if (count > expression.length() - 1) return null;
        while (count < expression.length()) {
            if (this.expression.charAt(count) == ' ') {
                this.count ++;
            } else {
                break;
            }
        }
        this.count ++;
        if (count - 1 > expression.length() - 1 || this.expression.charAt(count - 1) == ' ') {
            return null;
        }
        return new ExpressionToken(this.expression.charAt(count - 1));
    }
}
