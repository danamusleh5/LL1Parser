package driver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Based on the follow and first sets , this is the parsing table initialize for LL1 Parsing Table :
// At the end of this page , follow and first sets , and the parsing table 

public class Production {
    Map<String, Map<String, List<String>>> parsingTable = new HashMap<>();

	
	 void initializeParsingTable() {
		 
		 
	        parsingTable.put("module-decl", new HashMap<>());
	        parsingTable.get("module-decl").put("module", Arrays.asList("module-heading", "declarations", "block", "name", "."));

	        parsingTable.put("module-heading", new HashMap<>());
	        parsingTable.get("module-heading").put("module", Arrays.asList("module", "name", ";"));

	        parsingTable.put("block", new HashMap<>());
	        parsingTable.get("block").put("begin", Arrays.asList("begin", "stmt-list", "end"));

	        parsingTable.put("declarations", new HashMap<>());
	        parsingTable.get("declarations").put("const", Arrays.asList("const-decl", "var-decl", "procedure-decl"));
	        parsingTable.get("declarations").put("var", Arrays.asList("const-decl", "var-decl", "procedure-decl"));
	        parsingTable.get("declarations").put("procedure", Arrays.asList("const-decl", "var-decl", "procedure-decl"));
	        parsingTable.get("declarations").put("begin", Arrays.asList("ε"));

	        parsingTable.put("const-decl", new HashMap<>());
	        parsingTable.get("const-decl").put("const", Arrays.asList("const", "const-list"));
	        parsingTable.get("const-decl").put("var", Arrays.asList("ε"));
	        parsingTable.get("const-decl").put("procedure", Arrays.asList("ε"));
	        parsingTable.get("const-decl").put("begin", Arrays.asList("ε"));

	        parsingTable.put("const-list", new HashMap<>());
	        parsingTable.get("const-list").put("name", Arrays.asList("name", "=", "value", ";", "const-list"));
	        parsingTable.get("const-list").put("var", Arrays.asList("ε"));
	        parsingTable.get("const-list").put("procedure", Arrays.asList("ε"));
	        parsingTable.get("const-list").put("begin", Arrays.asList("ε"));

	        parsingTable.put("var-decl", new HashMap<>());
	        parsingTable.get("var-decl").put("var", Arrays.asList("var", "var-list"));
	        parsingTable.get("var-decl").put("procedure", Arrays.asList("ε"));
	        parsingTable.get("var-decl").put("begin", Arrays.asList("ε"));

	        parsingTable.put("var-list", new HashMap<>());
	        parsingTable.get("var-list").put("name", Arrays.asList("var-item", ";", "var-list"));
	        parsingTable.get("var-list").put("procedure", Arrays.asList("ε"));
	        parsingTable.get("var-list").put("begin", Arrays.asList("ε"));

	        parsingTable.put("var-item", new HashMap<>());
	        parsingTable.get("var-item").put("name", Arrays.asList("name-list", ":", "data-type"));

	        parsingTable.put("name-list", new HashMap<>());
	        parsingTable.get("name-list").put("name", Arrays.asList("name", "more-names"));

	        parsingTable.put("more-names", new HashMap<>());
	        parsingTable.get("more-names").put(",", Arrays.asList(",", "name-list"));
	        parsingTable.get("more-names").put(":", Arrays.asList("ε"));

	        parsingTable.put("data-type", new HashMap<>());
	        parsingTable.get("data-type").put("integer", Arrays.asList("integer"));
	        parsingTable.get("data-type").put("real", Arrays.asList("real"));
	        parsingTable.get("data-type").put("char", Arrays.asList("char"));

	        parsingTable.put("procedure-decl", new HashMap<>());
	        parsingTable.get("procedure-decl").put("procedure", Arrays.asList("procedure-heading", "declarations", "block", "name", ";", "procedure-decl"));
	        parsingTable.get("procedure-decl").put("begin", Arrays.asList("ε"));

	        parsingTable.put("procedure-heading", new HashMap<>());
	        parsingTable.get("procedure-heading").put("procedure", Arrays.asList("procedure", "name", ";"));

	        parsingTable.put("stmt-list", new HashMap<>());
	        parsingTable.get("stmt-list").put("name", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("readint", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("readreal", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("readchar", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("readln", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("writeint", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("writereal", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("writechar", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("writeln", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("if", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("while", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("loop", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("exit", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("call", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("begin", Arrays.asList("statement", ";", "stmt-list"));
	        parsingTable.get("stmt-list").put("end", Arrays.asList("ε"));

	        parsingTable.put("statement", new HashMap<>());
	        parsingTable.get("statement").put("name", Arrays.asList("ass-stmt"));
	        parsingTable.get("statement").put("readint", Arrays.asList("read-stmt"));
	        parsingTable.get("statement").put("readreal", Arrays.asList("read-stmt"));
	        parsingTable.get("statement").put("readchar", Arrays.asList("read-stmt"));
	        parsingTable.get("statement").put("readln", Arrays.asList("read-stmt"));
	        parsingTable.get("statement").put("writeint", Arrays.asList("write-stmt"));
	        parsingTable.get("statement").put("writereal", Arrays.asList("write-stmt"));
	        parsingTable.get("statement").put("writechar", Arrays.asList("write-stmt"));
	        parsingTable.get("statement").put("writeln", Arrays.asList("write-stmt"));
	        parsingTable.get("statement").put("if", Arrays.asList("if-stmt"));
	        parsingTable.get("statement").put("while", Arrays.asList("while-stmt"));
	        parsingTable.get("statement").put("loop", Arrays.asList("loop-stmt"));
	        parsingTable.get("statement").put("exit", Arrays.asList("exit-stmt"));
	        parsingTable.get("statement").put("call", Arrays.asList("call-stmt"));
	        parsingTable.get("statement").put("begin", Arrays.asList("block"));
	        parsingTable.get("statement").put("end", Arrays.asList("ε"));

	        parsingTable.put("ass-stmt", new HashMap<>());
	        parsingTable.get("ass-stmt").put("name", Arrays.asList("name", ":=", "exp"));

	        parsingTable.put("exp", new HashMap<>());
	        parsingTable.get("exp").put("name", Arrays.asList("term", "exp-prime"));
	        parsingTable.get("exp").put("value", Arrays.asList("term", "exp-prime"));
	        parsingTable.get("exp").put("(", Arrays.asList("term", "exp-prime"));

	        parsingTable.put("exp-prime", new HashMap<>());
	        parsingTable.get("exp-prime").put("+", Arrays.asList("+", "term", "exp-prime"));
	        parsingTable.get("exp-prime").put("-", Arrays.asList("-", "term", "exp-prime"));
	        parsingTable.get("exp-prime").put(";", Arrays.asList("ε"));
	        parsingTable.get("exp-prime").put("end", Arrays.asList("ε"));

	        parsingTable.put("term", new HashMap<>());
	        parsingTable.get("term").put("name", Arrays.asList("factor", "term-prime"));
	        parsingTable.get("term").put("value", Arrays.asList("factor", "term-prime"));
	        parsingTable.get("term").put("(", Arrays.asList("factor", "term-prime"));

	        parsingTable.put("term-prime", new HashMap<>());
	        parsingTable.get("term-prime").put("*", Arrays.asList("*", "factor", "term-prime"));
	        parsingTable.get("term-prime").put("/", Arrays.asList("/", "factor", "term-prime"));
	        parsingTable.get("term-prime").put(";", Arrays.asList("ε"));
	        parsingTable.get("term-prime").put("end", Arrays.asList("ε"));

	        parsingTable.put("factor", new HashMap<>());
	        parsingTable.get("factor").put("name", Arrays.asList("name"));
	        parsingTable.get("factor").put("value", Arrays.asList("value"));
	        parsingTable.get("factor").put("(", Arrays.asList("(", "exp", ")"));

	        parsingTable.put("read-stmt", new HashMap<>());
	        parsingTable.get("read-stmt").put("readint", Arrays.asList("readint", "(", "name-list", ")"));
	        parsingTable.get("read-stmt").put("readreal", Arrays.asList("readreal", "(", "name-list", ")"));
	        parsingTable.get("read-stmt").put("readchar", Arrays.asList("readchar", "(", "name-list", ")"));
	        parsingTable.get("read-stmt").put("readln", Arrays.asList("readln"));

	        parsingTable.put("write-stmt", new HashMap<>());
	        parsingTable.get("write-stmt").put("writeint", Arrays.asList("writeint", "(", "write-list", ")"));
	        parsingTable.get("write-stmt").put("writereal", Arrays.asList("writereal", "(", "write-list", ")"));
	        parsingTable.get("write-stmt").put("writechar", Arrays.asList("writechar", "(", "write-list", ")"));
	        parsingTable.get("write-stmt").put("writeln", Arrays.asList("writeln"));

	        parsingTable.put("if-stmt", new HashMap<>());
	        parsingTable.get("if-stmt").put("if", Arrays.asList("if", "condition", "then", "stmt-list", "else-part", "end"));

	        parsingTable.put("else-part", new HashMap<>());
	        parsingTable.get("else-part").put("else", Arrays.asList("else", "stmt-list"));
	        parsingTable.get("else-part").put("end", Arrays.asList("ε"));

	        parsingTable.put("while-stmt", new HashMap<>());
	        parsingTable.get("while-stmt").put("while", Arrays.asList("while", "condition", "do", "stmt-list", "end"));

	        parsingTable.put("loop-stmt", new HashMap<>());
	        parsingTable.get("loop-stmt").put("loop", Arrays.asList("loop", "stmt-list", "until", "condition"));

	        parsingTable.put("exit-stmt", new HashMap<>());
	        parsingTable.get("exit-stmt").put("exit", Arrays.asList("exit"));

	        parsingTable.put("call-stmt", new HashMap<>());
	        parsingTable.get("call-stmt").put("call", Arrays.asList("call", "name"));

	        parsingTable.put("condition", new HashMap<>());
	        parsingTable.get("condition").put("name", Arrays.asList("name-value", "relational-oper", "name-value"));
	        parsingTable.get("condition").put("value", Arrays.asList("name-value", "relational-oper", "name-value"));

	        parsingTable.put("name-value", new HashMap<>());
	        parsingTable.get("name-value").put("name", Arrays.asList("name"));
	        parsingTable.get("name-value").put("value", Arrays.asList("value"));

	        parsingTable.put("write-list", new HashMap<>());
	        parsingTable.get("write-list").put("name", Arrays.asList("name"));
	        parsingTable.get("write-list").put("value", Arrays.asList("value"));

	        parsingTable.put("relational-oper", new HashMap<>());
	        parsingTable.get("relational-oper").put("=", Arrays.asList("="));
	        parsingTable.get("relational-oper").put("|=", Arrays.asList("|="));
	        parsingTable.get("relational-oper").put("<", Arrays.asList("<"));
	        parsingTable.get("relational-oper").put("<=", Arrays.asList("<="));
	        parsingTable.get("relational-oper").put(">", Arrays.asList(">"));
	        parsingTable.get("relational-oper").put(">=", Arrays.asList(">="));
	    }

	 /*
First:-
	 
FIRST(module-decl) = { module } 

FIRST(module-heading) = { module } 

FIRST(block) = { begin } 

FIRST(declarations) = { const, var, procedure, ε } 

FIRST(const-decl) = { const, ε } 

FIRST(const-list) = { name, ε } 

FIRST(var-decl) = { var, ε } 

FIRST(var-list) = { name, ε } 

FIRST(var-item) = { name } 

FIRST(name-list) = { name } 

FIRST(more-names) = { ,, ε } 

FIRST(data-type) = { integer, real, char } 

FIRST(procedure-decl) = { procedure, ε } 

FIRST(procedure-heading) = { procedure } 

FIRST(stmt-list) = { ε, name, readint, readreal, readchar, readln, writeint, writereal, writechar, writeln, if, while, loop, exit, call, begin } 

FIRST(statement) = { ε, name, readint, readreal, readchar, readln, writeint, writereal, writechar, writeln, if, while, loop, exit, call, begin } 

FIRST(ass-stmt) = { name } 

FIRST(exp) = { (, name, integer-value, real-value } 

FIRST(exp-prime) = { +, -, ε } 

FIRST(term) = { (, name, integer-value, real-value } 

FIRST(term-prime) = { *, /, mod, div, ε } 

FIRST(factor) = { (, name, integer-value, real-value } 

FIRST(add-oper) = { +, - } 

FIRST(mul-oper) = { *, /, mod, div } 

FIRST(read-stmt) = { readint, readreal, readchar, readln } 

FIRST(write-stmt) = { writeint, writereal, writechar, writeln } 

FIRST(write-list) = { name, integer-value, real-value } 

FIRST(more-write-value) = { ,, ε } 

FIRST(write-item) = { name, integer-value, real-value } 

FIRST(if-stmt) = { if } 

FIRST(else-part) = { else, ε } 

FIRST(while-stmt) = { while } 

FIRST(loop-stmt) = { loop } 

FIRST(exit-stmt) = { exit } 

FIRST(call-stmt) = { call } 

FIRST(condition) = { name, integer-value, real-value } 

FIRST(relational-oper) = { =, !=, <, <=, >, >= } 

FIRST(name-value) = { name, integer-value, real-value } 

FIRST(value) = { integer-value, real-value } 

 Fllow :-
FOLLOW(module-decl) = { $ } 

FOLLOW(module-heading) = { $ } 

FOLLOW(block) = { $, ; } 

FOLLOW(declarations) = { $ } 

FOLLOW(const-decl) = { $, var, procedure, ε } 

FOLLOW(var-decl) = { $ } 

FOLLOW(procedure-decl) = { $ } 

FOLLOW(const-list) = { $ } 

FOLLOW(name) = { =, :, ;, ) } 

FOLLOW(value) = { ;, relational-oper, ) } 

FOLLOW(var-list) = { $ } 

FOLLOW(var-item) = { ; } 

FOLLOW(name-list) = { :, ) } 

FOLLOW(more-names) = { :, ) } 

FOLLOW(data-type) = { $ } 

FOLLOW(procedure-heading) = { $ } 

FOLLOW(stmt-list) = { end, else, until } 

FOLLOW(statement) = { ;, end, else, until } 

FOLLOW(ass-stmt) = { ; } 

FOLLOW(exp) = { ;, ) } 

FOLLOW(exp-prime) = { ;, ) } 

FOLLOW(term) = { ;, ) } 

FOLLOW(term-prime) = { ;, ) } 

FOLLOW(factor) = { ;, ) } 

FOLLOW(add-oper) = { (, name, integer-value, real-value } 

FOLLOW(mul-oper) = { (, name, integer-value, real-value } 

FOLLOW(read-stmt) = { ; } 

FOLLOW(write-stmt) = { ; } 

FOLLOW(write-list) = { ) } 

FOLLOW(write-item) = { ) } 

FOLLOW(more-write-value) = { ) } 

FOLLOW(if-stmt) = { ; } 

FOLLOW(else-part) = { end } 

FOLLOW(while-stmt) = { ; } 

FOLLOW(loop-stmt) = { ; } 

FOLLOW(exit-stmt) = { ; } 

FOLLOW(call-stmt) = { ; } 

FOLLOW(condition) = { then, do, ; } 

FOLLOW(relational-oper) = { name, integer-value, real-value } 

FOLLOW(name-value) = { relational-oper, ;, ) } 

FOLLOW(value) = { relational-oper, ;, ) } 

 */
	 
	 
	 
	 
	 
	 
	 /*
	  * 
	  * My LL1 Parsing Table : Printed from the code , for Testing ....
	  * 
	  *                     mod                 writechar           do                  integer             while               div                 else                loop                greater             if                  digit               period              $                   var                 module              *                   writeln             +                   integer-value       less                real                then                ,                   plus                exit                left                letter              =                   begin               readchar            minus               writeint            const               writereal           not                 and                 end                 readln              semicolon           readreal            procedure           right               add-oper            equal               comma               char                until               readint             
module-heading                                                                                                                                                                                                                                                                                              module name ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
var-item                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
more-names                                                                                                                                                                                                                                                                                                                                                                                                                                                                  , name-list | ε                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
const               mod                 writechar           do                  integer             while               div                 else                loop                greater than or equal toif                  digit               period                                  var                 module                                  writeln                                                     less than or equal toreal                then                                    plus                exit                left parenthesis    letter                                  begin               readchar            minus               writeint            const               writereal           not equal           and                 end                 readln              semicolon           readreal            procedure           right parenthesis                       equal               comma               char                until               readint             
relational-oper                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 = | != | < | <= | > | >=                                                                                                                                                                                                                                                                                                                                                                                                                
name-value                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
const-decl                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          const const-list | ε                                                                                                                                                                                                                                                                                                            
exit-stmt                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           exit                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
write-item                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
while-stmt                                                                                          while condition do stmt-list end                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
if-stmt                                                                                                                                                                                                 if condition then stmt-list else-part end                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
const-list                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
statement                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
block                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               begin stmt-list end                                                                                                                                                                                                                                                                                                                                                                                             
term                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
exp                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
factor                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
more-write-value                                                                                                                                                                                                                                                                                                                                                                                                                                                            , write-list | ε                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
write-list                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
value                                                                                                                                                                                                                                                                                                                                                                                       integer-value | real-value                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
real-value                                                                                                                                                                                                                  digit (digit)*.digit (digit)*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
term-prime                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
write-stmt                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      writeint ( write-list ) | writereal ( write-list ) | writechar ( write-list ) | writeln                                                                                                                                                                                                                                                                                                                                
procedure-decl                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
mul-oper                                                                                                                                                                                                                                                                                                                        * | / | mod | div                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
module                                                                                                                                                                                                                                                                                                      module name ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
name-list                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
var-decl                                                                                                                                                                                                                                                                                var var-list | ε                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
module-decl                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
integer-value                                                                                                                                                                                                               digit (digit)*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
data-type                                                                       integer | real | char                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
declarations                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
add-oper                                                                                                                                                                                                                                                                                                                                                                + | -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
stmt-list                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
procedure-heading                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   procedure name ;                                                                                                                                                
loop-stmt                                                                                                                                                       loop stmt-list until condition                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
call-stmt                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
condition                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
else-part                                                                                                                                   else stmt-list | ε                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
name                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        letter (letter | digit)*                                                                                                                                                                                                                                                                                                                                                                                                                                    
var-list                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
ass-stmt                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
read-stmt                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       readint ( name-list ) | readreal ( name-list ) | readchar ( name-list ) | readln
exp-prime                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   add-oper term exp-prime | ε                                                                                                    

	  * 
	  * 

*/
}
