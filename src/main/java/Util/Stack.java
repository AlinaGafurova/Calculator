package Util;

import java.util.Arrays;


class Stack<T extends Comparable<T>> implements MinMax<T>{
    private Object[] stck;
    private int tos;
    private int size;
    private int count;
    public Stack(int size){
        stck = new Object[size];
        tos = -1;
        this.size = size;
    }
    public Stack(){
        stck = new Object[10];
        tos = -1;
    }
    public  Object[] toArray(){
        Object[] ob = new Object[tos+1];
        for(int i=0;i<ob.length;i++){
           ob[i]=stck[i];
        }
         return ob;
    }
    public int getCount(){
        return count;
    }
    /*The same method toArray from Collection Framework.*/
    public T[] toArray(T[] a){
        if (a.length < tos+1)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(stck,tos+1, a.getClass());
        //if a.length==tos+1, but may be...
        System.arraycopy(stck, 0, a, 0,tos+1);
        if (a.length > tos+1)
            a[tos+1] = null;
        return a;
    }
    public void push(T item){
        if(tos==stck.length-1) {
            Object[] temp = new Object[stck.length * 2];
            for(int i=0;i<stck.length;i++){
                temp[i]=stck[i];
            }
            stck=temp;
            stck[++tos]=item;
        }
        else stck[++tos]=item;
        count++;
    }
    public T pop(){
        if(tos<0){
            System.out.println("Stack is empty!");
            return null;
        }
        else{count--;
        T v = (T) stck[tos--];
//        Object[] nst = new Object[tos+1];
//        for(int i = 0; i<tos+1;i++){
//            nst[i] = stck[i];
//        }
//        stck = nst;
        return (v);}//Convert Object to T.
    }
    public T peek(){
        if(tos<0){
            System.out.println("Stack is empty!");
            return null;
        }
        else return (T) stck[tos];
    }
    public T elementAt(int pos){
        if(pos<0 || pos>=stck.length) return null;
        return (T) stck[pos];
    }
    public int getTos(){
        return tos;
    }
    public void setTosForward(){
        tos++;
    }
    public void setTosBack(){
        tos--;
    }
    public boolean sameTos(Stack<?> s2){//Compare with another Stack<T2> where T2 can be anything ref. type.
        if(this.getTos()==s2.getTos())
            return true;
        else return false;
    }

    /*Move values into the other Stack. Types must be equals.*/
    public void move(Stack<T> s2){
        int to = this.getTos();
        while(this.getTos()>=0){
            s2.push(this.pop());}
        tos = to;
    }
    /*Copy values from one Stack to another.*/
    public void copy(Stack<T> s2){
        int i = 0;
        while(stck[i]!=null){
            s2.push((T)stck[i]);
            i++;
        }
    }
    public Stack<T> move(){
        Stack<T> s2 = new Stack<T>();
        int to = this.getTos();
        while(this.getTos()>=0){
            s2.push(this.pop());}
        tos = to;
        return s2;
    }
    /*Method copy for references type Stack.*/
    public Stack<T> copy(){
        int i =0;
        Stack<T> s2 = new Stack<T>();
        while(stck[i]!=null){
            s2.push((T) stck[i]);
            i++;
        }
        return s2;
    }
    public  boolean isIn(T ob){
        for(int i=0;i<=this.tos;i++){
            if(ob.equals(this.stck[i])) return true;
        }
        return false;
    }

    public T min(){
        T v = (T) this.stck[0];
        for(int i=1;i<=this.tos;i++){
            if(v.compareTo((T)this.stck[i])>0)
                v = (T) this.stck[i];
        }
        return v;
    }

    public T max(){
        T v = (T) this.stck[0];
        for(int i=1;i<=this.tos;i++)
            if(v.compareTo((T)this.stck[i])<0)
                v = (T) this.stck[i];
        return v;
    }
}
