package api;

public class StackWithSize<E> extends Stack<E>{
    private int curSize;
    public StackWithSize(){
    }
    public void push(E item){
        super.push(item);
        curSize++;
    }
    public E pop(){
        E e = super.pop();
        curSize--;
        return e;
    }

    public E peek(){
        return super.peek();
    }

    public boolean isEmpty(){
        return super.isEmpty();
    }
    
    public int size(){
        return curSize;
    }
}
