package api;

public class TestStack{
    public static void main(String...args){
        Stack<Integer> testStack = new Stack<Integer>();
        for(int i=0;i<10;i++){
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
