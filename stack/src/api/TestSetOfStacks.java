package api;

public class TestSetOfStacks{
    public static void main(String...args){
        SetOfStacks<Integer> testStack = new SetOfStacks<Integer>(10);
        for(int i=0;i<100;i++){
            testStack.push(i);
        }
        System.out.printf("peek() == %d%n",testStack.peek());
        if(!testStack.isEmpty()){
            while(!testStack.isEmpty()){
                System.out.printf("pop() = %d%n",testStack.pop());
            }
        }

    }
}
