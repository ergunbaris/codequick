package api;

public class TestQueue{
    public static void main(String...args){
        Queue<Integer> testQ = new Queue<>();
        for(int i=0;i<10;i++){
            testQ.add(i);
        }
        System.out.printf("peek()=%d%n",testQ.peek());

        while(!testQ.isEmpty()){
            System.out.printf("pop()=%d%n",testQ.remove());
        }
    }
}
