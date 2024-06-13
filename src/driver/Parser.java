package driver;

import java.util.List;
import java.util.Stack;


// Parser Class ((basic method : Parse())

public class Parser {
    private Production production = new Production();
    private int lineNumber = 1;
    private List<String> tokens;
    private int currentTokenIndex;
    private String currentToken;
    private Stack<String> stack = new Stack<>();
    private Tokenizer tokenizer;
    private boolean errorOccurred = false;



    public Parser(List<String> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
        this.currentToken = tokens.get(currentTokenIndex);
        production.initializeParsingTable();
        tokenizer = new Tokenizer(currentToken);
        StringBuilder input = new StringBuilder();
        for (String token : tokens) {
            input.append(token).append(" ");
        }
        tokenizer = new Tokenizer(input.toString().trim());
    }
   
   

	private void nextToken() {
        if (currentTokenIndex < tokens.size() - 1) {
            currentToken = tokens.get(++currentTokenIndex);
            // lineNumber ++  when read a newline 
            if (currentToken.equals("\n")) {
                lineNumber++;
            }
        } else {
            currentToken = null;
        }
    }

    public void error(String message) throws SyntaxError {
        throw new SyntaxError(lineNumber, currentTokenIndex, message);
    }


    private void parseModuleDecl() throws SyntaxError {
        stack.push("module-decl");

        while (!stack.isEmpty()) {
            String top = stack.pop();

            if (isNonTerminal(top)) {
                if (production.parsingTable.containsKey(top) && production.parsingTable.get(top).containsKey(currentToken)) {
                    List<String> production2 = production.parsingTable.get(top).get(currentToken);
                    if (!production2.get(0).equals("ε")) {
                        for (int i = production2.size() - 1; i >= 0; i--) {
                            stack.push(production2.get(i));
                        }
                    }
                } else {
                    // unexpected token
                    skipToNextModule();
                }
            } else if (isTerminal(top)) {
                if (top.equals(currentToken)) {
                    nextToken();
                } else {
                    // mismatch terminal
                    skipToNextModule();
                }
            } else if (top.equals("ε")) {
               //do nothing
            } else if (top.equals("name")) {
                // module name
                if (!currentToken.matches("[a-zA-Z][a-zA-Z0-9]*")) {
                    errorOccurred = true;
                    error("Unexpected character: " + currentToken);
                }
                nextToken();
                if (!stack.isEmpty() && stack.peek().equals(";")) {
                    // If ;  next, consume it
                    stack.pop();
                } else {
                    errorOccurred = true;
                    error("Expected semicolon after module name, but found: " + currentToken);
                }
            } else {
                errorOccurred = true;
                error("Invalid symbol on stack: " + top);
            }
        }

/////  Nake sure that is no any extra input 
        if (currentToken != null) {
            errorOccurred = true;
            error("Extra input after end of parsing");
        }
    }

    private void skipToNextModule() {
        // Skip tokens 
        while (currentToken != null && !currentToken.equals("module")) {
            nextToken();
        }
    }
    public void parse() {
    	if (tokenizer == null) {
            System.err.println("Tokenizer is not initialized.");
            return;
        }

        // Check if there were errors during tokenization
        if (tokenizer.isErrorOccurred()) {
             // Exit the parsing process
        	return;
        }

        try {
            parseModuleDecl();
            stack.push("module-decl");

            while (!stack.isEmpty()) {
                String top = stack.pop();

                if (currentToken == null) {
                    // eeeend
                    break;
                }

                if (isNonTerminal(top)) {
                    if (production.parsingTable.containsKey(top) && production.parsingTable.get(top).containsKey(currentToken)) {
                        List<String> production2 = production.parsingTable.get(top).get(currentToken);
                        if (!production2.get(0).equals("ε")) {
                            for (int i = production2.size() - 1; i >= 0; i--) {
                                stack.push(production2.get(i));
                            }
                        }
                    } else {
                        errorOccurred = true;
                        error("Unexpected token: " + currentToken);
                        break; 

                    }
                } else if (isTerminal(top)) {
                    if (top.equals(currentToken)) {
                        nextToken();
                    } else {
                        errorOccurred = true;
                        error("Expected " + top + ", but found " + currentToken);
                        break;
                    }
                } else if (top.equals("ε")) {
                    
                } else {
                    errorOccurred = true;
                    error("Invalid symbol on stack: " + top);
                    break; 
                }
            }

       
            if (!stack.isEmpty() || currentToken != null) {
                errorOccurred = true;
                error("Unexpected input after end of parsing");
            }
        } catch (Exception e) {
            errorOccurred = true;
        }
    }


   

 // check the terminal and nonterminal depends on the parsingTable
    private boolean isNonTerminal(String symbol) {
        return production.parsingTable.containsKey(symbol);
    }

    private boolean isTerminal(String symbol) {
        return !isNonTerminal(symbol) && !symbol.equals("ε");
    }

    
    
    public boolean isErrorOccurred() {
        return errorOccurred;
    }
    public void setIsErrorOccurred(boolean x) {
        this.errorOccurred = x;
    }



	public String returnResult() {

        if (isErrorOccurred()) {
	            return ("Parsing Failed!!!");
        } else {
	            return ("Parsing Successful!!!");

        }
     }



	public String getError() {
		return ("Error");

	}



}
