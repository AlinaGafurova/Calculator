package Util;


class Arithmetic {

    static double stp(double a, double b){
        double n = a;
        if(b!=0){
            if(b%1 == 0){//if b is integer.
                if(b<0) {
                    for (double i = -1.0; i > b; i--) {
                        a*=n;
                    }
                    return 1.0/a;
                }
                else {
                    for (int i = 1; i < b; i++) {
                        a *= n;
                    }
                    return a;
                }
            }
            else {
                a = Math.pow(a,b);//Use Lib for double degree (1.5,2.25,....)
            }
        }
        return 1.0;
    }
}
