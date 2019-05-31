# Lexical analyzer for C programming language

Lexical analyzer for C programming language, written in Java

The lexical analyzer will use an automatic determinist that will have a final state for each type of token;
- to identify the tokens, it will consume characters from one row to another source file and will make automatic transitions until it locks up (so it does not reach the final state for the first time);
- if it stuck in a non-final state, then an error occurred so lexical and analysis stops; 
- if stuck in a final state, the token is generated (the type is indicated by the final state, the value is consumed to reach the original state there), after automatically switching to the initial state (without consuming characters from the source) and resume scanning by using characters and making transitions;

## C Tokens
A token is the smallest element of a program that is meaningful to the compiler. Tokens can be classified as follows:

Keywords
Identifiers
Constants
Strings
Special Symbols
Operators

<b> Keyword </b>: Keywords are pre-defined or reserved words in a programming language. Each keyword is meant to perform a specific function in a program. Since keywords are referred names for a compiler, they can’t be used as variable names because by doing so, we are trying to assign a new meaning to the keyword which is not allowed. You cannot redefine keywords. However, you can specify text to be substituted for keywords before compilation by using C/C++ preprocessor directives.C language supports 32 keywords which are given below:

auto         double      int        struct
break        else        long       switch
case         enum        register   typedef
char         extern      return     union
const        float       short      unsigned
continue     for         signed     void
default      goto        sizeof     volatile
do           if          static     while
 
Identifiers: Identifiers are used as the general terminology for naming of variables, functions and arrays. These are user defined names consisting of arbitrarily long sequence of letters and digits with either a letter or the underscore(_) as a first character. Identifier names must differ in spelling and case from any keywords. You cannot use keywords as identifiers; they are reserved for special use. Once declared, you can use the identifier in later program statements to refer to the associated value. A special kind of identifier, called a statement label, can be used in goto statements.

There are certain rules that should be followed while naming c identifiers:
- They must begin with a letter or underscore(_).
- They must consist of only letters, digits, or underscore. No other special character is allowed.
- It should not be a keyword.
- It must not contain white space.
- It should be up to 31 characters long as only first 31 characters are significant.
Some examples of c identifiers:

NAME	     REMARK
_A9	       Valid
Temp.var	 Invalid as it contains special character other than the underscore
void	     Invalid as it is a keyword


<b> Constants:</b>  Constants are also like normal variables. But, only difference is, their values can not be modified by the program once they are defined. Constants refer to fixed values. They are also called as literals.
Constants may belong to any of the data type.Syntax:
const data_type variable_name; (or) const data_type *variable_name;
Types of Constants:

  Integer constants – Example: 0, 1, 1218, 12482
  Real or Floating point constants – Example: 0.0, 1203.03, 30486.184
  Octal & Hexadecimal constants – Example: octal: (013 )8 = (11)10, Hexadecimal: (013)16 = (19)10
  Character constants -Example: ‘a’, ‘A’, ‘z’
  String constants -Example: “GeeksforGeeks”

<b> Strings:</b>  Strings are nothing but an array of characters ended with a null character (‘\0’).This null character indicates the end of the string. Strings are always enclosed in double quotes. Whereas, a character is enclosed in single quotes in C and C++.Declarations for String:
char string[20] = {‘g’, ’e’, ‘e’, ‘k’, ‘s’, ‘f’, ‘o’, ‘r’, ‘g’, ’e’, ‘e’, ‘k’, ‘s’, ‘\0’};
char string[20] = “geeksforgeeks”;
char string [] = “geeksforgeeks”;
Difference between above declarations are:

1.when we declare char as “string[20]”, 20 bytes of memory space is allocated for holding the string value.
2.when we declare char as “string[]”, memory space will be allocated as per the requirement during execution of the program.

<b> Special Symbols:</b> The following special symbols are used in C having some special meaning and thus, cannot be used for some other purpose.[] () {}, ; * = #

  Brackets[]: Opening and closing brackets are used as array element reference. These indicate single and multidimensional subscripts.
  Parentheses(): These special symbols are used to indicate function calls and function parameters.
  Braces{}: These opening and ending curly braces marks the start and end of a block of code containing more than one executable statement.
  comma (, ): It is used to separate more than one statements like for separating parameters in function calls.
  semi colon : It is an operator that essentially invokes something called an initialization list.
  asterick (*): It is used to create pointer variable.
  assignment operator: It is used to assign values.
  pre processor(#): The preprocessor is a macro processor that is used automatically by the compiler to transform your program before actual compilation.

<b> Operators:</b> Operators are symbols that triggers an action when applied to C variables and other objects. The data items on which operators act upon are called operands.
Depending on the number of operands that an operator can act upon, operators can be classified as follows:
  Unary Operators: Those operators that require only single operand to act upon are known as unary operators.For Example increment and decrement operators
  Binary Operators: Those operators that require two operands to act upon are called binary operators. Binary operators are classified into :
  Arithmetic operators
  Relational Operators
  Logical Operators
  Assignment Operators
  Conditional Operators
  Bitwise Operators
