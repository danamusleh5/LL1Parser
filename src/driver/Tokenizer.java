package driver;
import java.util.*;

public class Tokenizer {
    /*
     * Fields
     */
    private StringBuilder input;
    private List<String> generatedTokens;
    private int currentIndex;
    private int currentLineNumber; 
    private boolean errorOccurred = false;

    /*
     * Reserved words, all of them in an ArrayList
     */
    public static final List<String> RESERVED_WORDS = Arrays.asList(
        "module", "begin", "end", "const", "var", "integer", "real", "char",
        "procedure", "mod", "div", "readint", "readreal", "readchar", "readln",
        "writeint", "writereal", "writechar", "writeln", "if", "then", "else",
        "while", "do", "loop", "until", "exit", "call"
    );

    /*
     * Punctuation: "=", ";", ":", "(", ")", ",", ".", ":="
     * Operators: "+", "-", "*", "/", "mod", "div"
     * Relational Operators: "=", "!=", "<", "<=", ">", ">="
     */
    public static final List<String> TERMINALS = Arrays.asList(
        ":=", "!=", "<=", ">=", "=", ";", ":", "(", ")", ",", ".", "+", "-", "*", "/", "<", ">"
    );

    /*
     * Constructor
     */
    public Tokenizer(String input) {
        this.input = new StringBuilder(input);
        this.generatedTokens = new ArrayList<>();
        this.currentIndex = 0;
        this.currentLineNumber = 1; // Initialize line number

        
    }

    /*
     * Method to generate tokens
     */
    public List<String> generateTokens() {
        try {
            while (currentIndex < input.length()) {
                char currentChar = input.charAt(currentIndex);
                if (currentChar == '\n') {
                    currentLineNumber++;
                }

                // Debug print
                debug("Current character: " + currentChar);

                // Skip whitespace
                if (Character.isWhitespace(currentChar)) {
                    currentIndex++;
                    continue;
                }

                // Skip comments
                if (currentChar == '/') {
                    if (currentIndex + 1 < input.length() && input.charAt(currentIndex + 1) == '/') {
                        skipLineComment();
                        continue;
                    } else if (currentIndex + 1 < input.length() && input.charAt(currentIndex + 1) == '*') {
                        skipBlockComment();
                        continue;
                    }
                }

                // Debug print
                debug("Current index: " + currentIndex);

                // Try to match reserved words and terminals
                String matchedToken = matchReservedWordOrTerminal();
                if (matchedToken != null) {
                    generatedTokens.add(matchedToken);
                    debug("Matched token: " + matchedToken);
                    continue;
                }

                // Debug print
                debug("Trying to match name...");

                // Match names and values
                if (Character.isLetter(currentChar) || currentChar == '_') {
                    String name = matchName();
                    generatedTokens.add(name);
                    debug("Matched name: " + name);
                } else if (Character.isDigit(currentChar)) {
                    String value = matchValue();
                    generatedTokens.add(value);
                    debug("Matched value: " + value);
                } else if (currentChar == '"') {
                    String stringLiteral = matchStringLiteral();
                    generatedTokens.add(stringLiteral);
                    debug("Matched string literal: " + stringLiteral);
                } else {
                    // Report unexpected character as an error
                    setIsErrorOccurred(true);
                    throw new RuntimeException("Unexpected character, Line: " + currentLineNumber + ": " + currentChar);
                }
            }
        } catch (RuntimeException e) {
            // If an error occurs during token generation, print the error message
            setIsErrorOccurred(true);
            System.err.println("Error generating tokens: " + e.getMessage());
        }

        return generatedTokens;
    }
    /*
     * Match reserved words or terminals
     */
    private String matchReservedWordOrTerminal() {
        for (String word : RESERVED_WORDS) {
            if (input.substring(currentIndex).startsWith(word) &&
                (currentIndex + word.length() == input.length() ||
                 !Character.isLetterOrDigit(input.charAt(currentIndex + word.length())) &&
                 input.charAt(currentIndex + word.length()) != '_')) {
                currentIndex += word.length();
                return word;
            }
        }
        for (String terminal : TERMINALS) {
            if (input.substring(currentIndex).startsWith(terminal)) {
                currentIndex += terminal.length();
                return terminal;
            }
        }
        return null;
    }
    /*
     * Match names (identifiers)
     */
    private String matchName() {
        int start = currentIndex;

        // Ensure the first character is a letter
        if (!Character.isLetter(input.charAt(currentIndex))) {
            setIsErrorOccurred(true);
            throw new RuntimeException("Invalid identifier: " + input.charAt(currentIndex) + " cannot start an identifier");
        }

        // Continue scanning the identifier for letters, digits, and underscores
        while (currentIndex < input.length() && (Character.isLetterOrDigit(input.charAt(currentIndex)) || input.charAt(currentIndex) == '_')) {
            currentIndex++;
        }

        // Extract the identifier
        return input.substring(start, currentIndex);
    }

    /*
     * Match values (integers and reals)
     */
    private String matchValue() {
        int start = currentIndex;
        boolean isReal = false;
        while (currentIndex < input.length() && (Character.isDigit(input.charAt(currentIndex)) || input.charAt(currentIndex) == '.')) {
            if (input.charAt(currentIndex) == '.') {
                if (isReal) {
                    setIsErrorOccurred(true);
                    throw new RuntimeException("Invalid number format, Line:" + currentLineNumber);
                }
                isReal = true;
            }
            currentIndex++;
        }
        return input.substring(start, currentIndex);
    }

    /*
     * Match string literals
     */
    private String matchStringLiteral() {
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        currentIndex++; // Skip opening double quote
        while (currentIndex < input.length() && input.charAt(currentIndex) != '"') {
            if (input.charAt(currentIndex) == '\\' && currentIndex + 1 < input.length()) {
                sb.append(input.charAt(currentIndex)); // Append the escape character
                currentIndex++;
            }
            sb.append(input.charAt(currentIndex));
            currentIndex++;
        }
        if (currentIndex == input.length()) {
            setIsErrorOccurred(true);
            throw new RuntimeException("Unterminated string literal");
        }
        sb.append('"');
        currentIndex++; // Skip closing double quote
        return sb.toString();
    }

    /*
     * Skip line comments (// comment)
     */
    private void skipLineComment() {
        currentIndex += 2; // Skip the "//"
        while (currentIndex < input.length() && input.charAt(currentIndex) != '\n') {
            currentIndex++;
        }
        if (currentIndex < input.length()) {
            currentIndex++; // Skip the newline character
        }
    }

    /*
     * Skip block comments (/* comment *)
     */
    private void skipBlockComment() {
        currentIndex += 2; // Skip the "/*"
        while (currentIndex + 1 < input.length() && !(input.charAt(currentIndex) == '*' && input.charAt(currentIndex + 1) == '/')) {
            currentIndex++;
        }
        if (currentIndex + 1 < input.length()) {
            currentIndex += 2; // Skip the "*/"
        } else {
            setIsErrorOccurred(true);
            throw new RuntimeException("Unterminated block comment");
        }
    }

    /*
     * Debug print method
     */
    private void debug(String message) {
        System.out.println(message);
    }

    /*
     * Get the generated tokens
     */
    public List<String> getTokens() {
        return generatedTokens;
    }

    /*
     * Check if an error occurred during token generation
     */
    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    /*
     * Set the error occurred status
     */
    public void setIsErrorOccurred(boolean errorOccurred) {
        this.errorOccurred = errorOccurred;
    }
}
