package api;

public class TestStackMin{
    public static void main(String...args){
        StackMin<Integer> testStack = new StackMin<Integer>();
        for(int i=0;i<10;i++){
            testStack.push(i);
        }
        System.out.printf("peek() == %d%n",testStack.peek());
        System.out.printf("min() == %d%n",testStack.min());
        if(!testStack.isEmpty()){
            while(!testStack.isEmpty()){
                System.out.printf("pop() = %d%n",testStack.pop());
                System.out.printf("min() = %d%n",testStack.min());
            }
        }

    }
}
