package api;
import java.util.NoSuchElementException;

public class Stack<E>{
    private E item;
    private Stack<E> next;
    private Stack<E> top;
 
    public Stack(){}
    private Stack(E item){
        this.item = item;
    }
    public void push(E item){
        if(null == item){
            throw new IllegalArgumentException("pushing null element in stack not allowed!");
        }
        Stack<E> curNode = new Stack<E>(item);
        if(null == top){
            top = curNode;
        }else{
            curNode.next = top;
            top = curNode;
        }
    }
    public E pop(){
        if(null == top){
            throw new NoSuchElementException();
        }
        Stack<E> temp = top;
        top = top.next;
        temp.next = null;
        return temp.item;
    }

    public E peek(){
        if(null == top){
            throw new NoSuchElementException();
        }
        return top.item;
    }

    public boolean isEmpty(){
        return top == null;
    }
}
