package fibonacci;
import java.util.*;

/* Retrospective
    Good: correct algorithm
    Bad: OPerator precedence ignored == is considered prior to & operator.
*/

public class FibEven{
    public static void main(String...args){
        System.out.printf("sum=%d%n",new FibEven().sumFibEven());

    }

    int sumFibEven(){
        int sum = 0;
        int mask = 0x01;
        for(int number:fibonacciNumbers()){
            if((number & mask) ==0){
                sum +=number;
            }
        }
        return sum;
    }

    private List<Integer> fibonacciNumbers(){
        List<Integer> fib = new LinkedList<>();
        fib.add(1);
        fib.add(2);
        addNextFib(fib);
        return fib;
    }

    private void  addNextFib(List<Integer> fibList){
        int size = fibList.size();
        int cur = fibList.get(size-1) + fibList.get(size-2);
        if(cur > 4_000_000){
            return;
        }
        fibList.add(cur);
        addNextFib(fibList);
    }

    
}
