package api;
import java.util.NoSuchElementException;

public class StackMin<E extends Comparable>{
    private static class StackNode<E>{
        private final E item;
        private E previousMin;
        private StackNode<E> next;
        private StackNode(E item){
            this.item = item;
        }
        
    }
    private StackNode<E> top;
    private E curMin;
 
    public StackMin(){}
    public void push(E item){
        if(null == item){
            throw new IllegalArgumentException("pushing null element in stack not allowed!");
        }
        StackNode<E> curNode = new StackNode<E>(item);
        curNode.next = top;
        top = curNode;
        if(curMin == null || curMin.compareTo(item) > 0){
            curNode.previousMin = curMin;
            curMin = item;
        }
    }
    public E pop(){
        if(null == top){
            throw new NoSuchElementException();
        }
        StackNode<E> temp = top;
        if(temp.previousMin != null && temp.item == curMin){
            curMin = temp.previousMin;
        }
        top = top.next;
        temp.next = null;
        if(top == null){
            curMin = null;
        }
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
    
    public E min(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return curMin;
    }
}
