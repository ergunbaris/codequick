package api;
import java.util.NoSuchElementException;

public class Stack<E> implements IStack<E>{
    private static class StackNode<E>{
        private final E item;
        private StackNode<E> next;
        private StackNode(E item){
            this.item = item;
        }
        
    }
    private StackNode<E> top;
 
    public Stack(){}
    public void push(E item){
        if(null == item){
            throw new IllegalArgumentException("pushing null element in stack not allowed!");
        }
        StackNode<E> curNode = new StackNode<E>(item);
        curNode.next = top;
        top = curNode;
    }
    public E pop(){
        if(null == top){
            throw new NoSuchElementException();
        }
        StackNode<E> temp = top;
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
