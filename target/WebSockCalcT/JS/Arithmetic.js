
/*
* Enable on ECMAScript 6.
* For using on ECMAScript 5 or earlier, replace 'let' with 'var' and replace '**' operator with exp. function.
 */
var arithmeticExpression = /[0-9]+([*/+-^][0-9]+)*/i;
var ops = /[*/()^+-]/i;
var numeric = /\d+/i;
function getPriority(c){
    if(!c || typeof c !== 'symbol')
        throw new TypeError('Symbol expected.');
    switch (c){
        case '^': return 3;
        case '*': case '/': return 2;
        case '+':case '-': return 1;
        case '(':case ')': return 0;
        default: return -1;
    }
}

function calc(a,b,c){
    if(!a || !b || !c)
        throw new Error("Arguments lost.");
    if(typeof a !== 'number' || typeof b !== 'number' || typeof c !== 'symbol')
        throw new TypeError("Type Error! Must: number, number, character. (operator)");
    switch (c){
        case '+': return a+b;
        case '-': return a-b;
        case '*': return a*b;
        case '/': return a/b;
        case '^': return a ** b;
        default: return 0;
    }
}

function polish(s) {
    if(!arithmeticExpression.test(s))
        throw new SyntaxError("The message "+s+"is not ArithmeticExpression.");
    let a = "";
    let stack = [], j = -1;
    for(var i = 0; i< s.length;i++){
        c = s.charAt(i);
        if(ops.test(c)){//is operator.
            if(c == '('){
                stack[++j] = c;
            }
            else if(c == ')'){
                while(true){
                    c = stack[j--];
                    if(c=='('){ break;}
                    a.append(' '); a.append(c);
                }
            }
            else if (stack.length>=0){
                n = stack[j--];
                j++;
                if(getPriority(c)<=getPriority(n)){
                    a.append(' '); a.append(n);j--; stack[++j] = c;}
                else{a.append(' '); stack[++j] = c;}
            }
            else{a.append(' ');stack[++j] = c;}
        }
        else{
            a.append(c);
        }
    }
    for(i=j;i>=0;i--){
        a.append(' '); a.append(stack.pop());
    }
    return a;
}
function resultPolish(str){
    if(!str || typeof str !== 'string' || str.length === 0){
        throw new TypeError("String expected!");
    }
    let a = "", stack = [];
    for(let i = 0; i<str.length;i++){
        c = str.charAt(i);
        if(numeric.test(c)){
            a.append(c);
        }
        else if(c=='.'||c==','){
            a.append('.');
        }
        else if(c==' '&&a.length>0){
            stack.push(parseFloat(a));
            a = "";
        }
        if(ops.test(c) && stack.length>0){
            stack.push(calc(stack.pop(),stack.pop(),c));
        }
    }
    return stack.pop();
}