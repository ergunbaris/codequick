package api;
import java.util.NoSuchElementException;

/*
    RETROSPECTIVE:
    - As shown below extending class can define a more restricted Generic type
    this is logical because StackMin can be casted to Stack but vice versa not valid
    so when casting from StackMin to Stack E which is Comparable can also be an Object

    - For better memory model we used another stack to keep track of mins.
    - should always use compareTo for comparing generics.
    
*/

public class StackMin<E extends Comparable> extends Stack<E>{
    Stack<E> minStack = new Stack<>();
    public StackMin(){}
    public void push(E item){
        if(minStack.isEmpty()){
            minStack.push(item);
        }else{
            if(item.compareTo(minStack.peek()) <= 0){
                minStack.push(item);
            }
        }
        super.push(item);
    }
    public E pop(){
        E e = super.pop();
        if(e.equals(minStack.peek())){
            minStack.pop();
        }
        return e;
    }

    public E peek(){
        return super.peek();
    }

    public boolean isEmpty(){
        return super.isEmpty();
    }
    
    public E min(){
        return minStack.peek();
    }
}
