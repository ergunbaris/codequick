package api;
import java.util.*;

public class SetOfStacks<E> implements IStack<E>{
    private final List<StackWithSize<E>> stacks;
    private final int maxSize;
    
    public SetOfStacks(int maxStackSize){
        stacks = new ArrayList<>();
        stacks.add(new StackWithSize<E>());
        this.maxSize = maxStackSize;
    }
    public void push(E item){
        StackWithSize<E> lastStack = getLastStack();
        if(lastStack == null || lastStack.size() > maxSize){
            StackWithSize<E> newStack = new StackWithSize<E>();
            newStack.push(item);
            stacks.add(newStack);
        }else{
            lastStack.push(item);
        }
    }
    public E pop(){
        StackWithSize<E> lastStack = getLastStack();
        if(lastStack == null){
            throw new RuntimeException("StackEmptyException"); //TODO
        }
        E e = lastStack.pop();
        return e;
    }

    public E popAt(int stackIndex){
        // What will happen with non-full stacks
        // client code might be assuming all the stacks are full
        return null;
    }

    public E peek(){
        StackWithSize<E> lastStack = getLastStack();
        E e = lastStack.peek();
        return e;
    }

    public boolean isEmpty(){
        return stacks.size() == 0;
    }
    
    private StackWithSize<E> getLastStack(){
        if(stacks.size() == 0){
            return null;
        }
        StackWithSize<E> lastStack = stacks.get(stacks.size()-1);
        if(lastStack.size() == 0){
            stacks.remove(lastStack);
            return getLastStack();
        }
        return lastStack;
    }
}
