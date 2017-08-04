// Genevieve Leach
// CS 240, Section 1
// Programming Assignment 2
// 1 May 2016

 /**
 * COPYRIGHT: smanna@cpp.edu
 * CS 240 Spring 2016
 * Programming Assignment 2
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions.
 *
 *  Students need to implement a Calculator using the framework provided. Feel
 *  free to add your own fields and methods besides ones already provided. Please do not make
 *  any changes to the provided method signatures. If you do so, your code
 * cannot be run automatically, and you will not be graded.
 *
 *  ** YOUR RESULTS SHOULD BE ROUNDED TO THREE DECIMAL PLACES. IF YOU FAIL TO DO
 *  SO, YOU WILL BE PENALIZED BY 1 POINT.
 *  ** YOU SHOULD PROPOERLY COMMENT YOUR CODE, OTHERWISE YOU WILL BE PENALIZED
 *  BY 5 POINTS.
 *
 **/

import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Calculator {

  private DecimalFormat formatter = new DecimalFormat("######.###");
  private Stack<Double> operandStack = new Stack<>();
  private Stack<Character> operatorStack = new Stack<>();
  private boolean DEBUG;

  //Constructor
  public Calculator() {
    this.DEBUG = false;
  }

  //Constructor with manual debug setting
  public Calculator(boolean DEBUG) {
    this.DEBUG = DEBUG;
  }

  /** Returns true if the new character has precedence to be pushed on to the
  *   stack. Returns false if not.
  *   @param previous operator already on the stack
  *   @param newChar operator being added to the stack
  *   @return boolean of whether the operator has precedence
  */
  private boolean hasPrecedence(Character previous, Character newChar) {
    //if the previous has top precedence then the new doesn't
    if (previous == '*' || previous == '/')
      return false;
    //subtract previous first then new
    else if (previous == '-' && newChar == '-')
      return false;
    //subtract previous then add new
    else if (previous == '-' && newChar == '+')
      return false;
    else {
      return true;
    }
  }

  /** Pops two operands and one operator and performs the operation.
  *   @param operands stack of the operands
  *   @param operators stack of the operators
  *   @return answer to the operation
  */
  private double popStackAndSolve(Stack<Double> operands,Stack<Character>
                                  operators) {
    double operand1,operand2,answer;
    char operator;

    operand2 = operands.pop(); //pop the second first because stack ordering
    if(DEBUG) {
      System.out.println("Popped operand 2: " + operand2);
    }

    operand1 = operands.pop();
    if(DEBUG) {
      System.out.println("Popped operand 1: " + operand1);
    }

    operator = operators.pop();
    if(DEBUG) {
      System.out.println("Popped operator: " + operator);
    }

    answer = calc(operand1,operand2,operator);
    return answer;
  }
 
  /** @param operand1 the first operand in the equation
  *   @param operand2 the second operand in the equation
  *   @param operator the operator to perform on the two operands
  *   @return the answer
  */
  public double calc(double operand1, double operand2, char operator) {
     double result = 0.0;
     switch(operator) {
       case '+': {
         result = operand1 + operand2;
         break;
       }
       case '-': {
         result = operand1 - operand2;
         break;
       }
       case '/': {
         result = operand1 / operand2;
         break;
       }
       case '*': {
         result = operand1 * operand2;
         break;
       }
       default: {
         break;
       }
    }
    return Double.parseDouble(formatter.format(result));
  }

  /** Solves an infix equation and returns the answer as a Double. Limits to 3
  *   decimal places.
  *   @param equation infix String equation to be solved.
  *   @return double answer to equation
  */
  public Double solve(String equation) {
    equation = equation.replaceAll("\\s+",""); //replaces any spaces that may be in the equation

    StringTokenizer tokenizer = new StringTokenizer(equation, "()*/+-", true); //separates the equation into tokens of
                                                                               //numbers, parentheses, and operators
    String[] array = new String[tokenizer.countTokens()];

    for(int i = 0; tokenizer.hasMoreTokens(); i++) {
      array[i] = tokenizer.nextToken();
    }

    for(int i = 0; i < array.length; i++) {
      char token = array[i].charAt(0);
      if (DEBUG) {
        System.out.println("Checking token: " + array[i]);
      }
      switch(token) {
        case '(' : {
          operatorStack.push(token);
          if (DEBUG) {
            System.out.println("Added opening parenthesis. (");
          }
          break;
        }

        case ')' : {
          if(DEBUG) {
            System.out.println("Found closing parenthesis. )");
          }
          while(operatorStack.peek() != '(') {
            double result = popStackAndSolve(operandStack, operatorStack);
            operandStack.push(result);
            if(DEBUG) {
              System.out.println("Pushed result: " + result);
            }
          }
          operatorStack.pop(); //to get rid of opening parenthesis
          if(DEBUG) {
            System.out.println("Popped opening parenthesis.");
          }
          break;
        }

        case '*' : case '/' : case '+' : case '-' : {
          if (operatorStack.empty() || operatorStack.peek() == '(' || hasPrecedence(operatorStack.peek(), token)) {
            operatorStack.push(token);
            if(DEBUG) {
              System.out.println("Operator " + token + " has precedence.");
              System.out.println("Pushed operator: " + token);
            }
          } else {
            if(DEBUG) {
              System.out.println("Operator " + token + " does not have precedence over top of stack: " + operatorStack.peek());
            }
            double result = popStackAndSolve(operandStack, operatorStack);
            operandStack.push(result);
            if (DEBUG) {
              System.out.println("Pushed result: " + result);
            }
            operatorStack.push(token);
            if(DEBUG) {
              System.out.println("Pushed operator: " + token);
            }
          }
          break;
        }

        //If it's not any of the cases above, it's a number, so push that into the operand stack.
        default: {
          operandStack.push(Double.parseDouble(array[i]));
          if(DEBUG) {
            System.out.println("Pushed operand " + array[i]);
          }
          break;
        }
      }
    }
    while(!operatorStack.empty() && operandStack.size() > 1) {
      operandStack.push(popStackAndSolve(operandStack, operatorStack));
    }
    return operandStack.pop();
  }
}
