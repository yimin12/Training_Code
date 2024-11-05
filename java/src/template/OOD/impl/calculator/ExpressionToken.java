package src.template.OOD.impl.calculator;

/**
 *  Helper function for ExpressionTokenizer
 */
public class ExpressionToken {

    Type type;
    Character token;

    public ExpressionToken(Type type, Character token) {
        super();
    }

    public Type getType() {
        return type;
    }

    public int intValue() {
        return this.token - '0';
    }

    public Character getToken() {
        return token;
    }

    public ExpressionToken(Character token) {
        super();
        this.token = token;
        if (token - '0' <= 9 && token - '0' >= 0) {
            this.type = Type.NUMBER;
        } else if (token == '(' || token == ')') {
            this.type = Type.PARENTHESIS;
        } else {
            this.type = Type.OPERATOR;
        }
    }

    public enum Type {
        NUMBER, // e.g 23
        OPERATOR, // +， -， *， /
        PARENTHESIS, // ()
    }
}
