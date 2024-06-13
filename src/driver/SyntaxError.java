package driver;
//// Synatx Errors 
public class SyntaxError extends Exception {
    private int lineNumber;
    private int tokenIndex;

    public SyntaxError(int lineNumber, int tokenIndex, String message) {
        super(message);
        this.lineNumber = lineNumber;
        this.tokenIndex = tokenIndex;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getTokenIndex() {
        return tokenIndex;
    }
}
