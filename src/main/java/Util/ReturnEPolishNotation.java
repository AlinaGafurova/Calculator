package Util;

import static Util.Arithmetic.stp;
import java.util.regex.*;

public class ReturnEPolishNotation {
    private ReturnEPolishNotation(){}
    /*Operators.*/
    private static String pattern = "[0-9()]([\\+\\-\\*\\/\\^][0-9()]+)*";
    private static boolean isOperator(char c){
        switch (c){
            case '+': return true;
            case '-': return true;
            case '*': return true;
            case '/': return true;
            case '(': return true;
            case ')': return true;
            case '^': return true;
            default: return false;
        }
    }
    /*Priority of operator.*/
    private static int getPriority(char c){
        switch (c){
            case '^': return 3;
            case '*': case '/': return 2;
            case '+':case '-': return 1;
            case '(':case ')': return 0;
            default: return -1;
        }
    }
    /*For computing expression in result method.
    * Return int Digit if is it true, else return -1.*/
    private static int isDigit(char i){
        switch (i){
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            default: return -1;
        }
    }
    /*Compute on stack.*/
    private static double function(double b,double a,char c){
        switch (c){
            case '+': return a+b;
            case '-': return a-b;
            case '*': return a*b;
            case '/': return a/b;
            case '^': return stp(a,b);
            default: return 0;
        }
    }
    public static StringBuilder getInput(String s){
        if(s==null || s.length() == 0){
            System.out.println("Error! Empty String. Expected: Expression.");
            return null;
        }
        if(!Pattern.matches(pattern,s)){
            System.out.println("Error! The String: \" "+s+" \" is not Arithmetic Expression!");
            return null;
        }
        StringBuilder ss = new StringBuilder();//Output.
        Stack<Character> st2 = new Stack<Character>();//Stack.
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);//symbol
            if(isOperator(c)){
                if(c=='('){st2.push(c);}//Если скобка, то не добавляем пробел, кладём её в стек.
                else if(c==')'){
                    //st2.pop();//Выкидываем закрывающую скобку, и пока не окажется открывающая, кидать элементы на выход.
                    while(true){
                        c = st2.pop();
                        if(c=='('){ break;}
                        ss.append(' '); ss.append(c);//При добавлении новых операторов в выходную строку, делаем пробелы. Скобки НЕ добавляются!
                    }
                }
                else if (st2.getTos()>=0){
                    char n = st2.pop();
                    st2.setTosForward();
                    if(getPriority(c)<=getPriority(n)){
                        ss.append(' '); ss.append(n);st2.setTosBack(); st2.push(c);}
                    else{ss.append(' '); st2.push(c);}
                }
                else{ss.append(' ');st2.push(c);}
            }
            else ss.append(c);//add to output char.
        }
        for(int i=st2.getTos();i>=0;i--){
            ss.append(' '); ss.append(st2.pop());
        }
        return ss;
    }
    /*Return result of the converted expression into RPN*/
    public static double result(StringBuilder ss){
        StringBuilder an = new StringBuilder();
        Stack<Double> dt = new Stack<Double>();
        for(int i=0;i<ss.length();i++){
            char c = ss.charAt(i);
            if(isDigit(c)>=0){
                an.append(c);}
            else if(c=='.'||c==','){
                an.append('.');
            }
            else if(c==' '&&an.length()>0){
                dt.push(Double.parseDouble(an.toString()));
                an.setLength(0);
            }
            if(isOperator(c)&&dt.getTos()>0){
                dt.push(function(dt.pop(),dt.pop(),c));
            }
        }
        return dt.pop();
    }
}
