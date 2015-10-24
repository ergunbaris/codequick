package multiply;

public class MultiplyByMillion{
    public static void main (String ... args){
        int input = Integer.parseInt(args[0]);
        System.out.printf("result of multiplying %d by 1,000,000 is %d%n", input, new MultiplyByMillion().multiply(input));
    }
    
    public int multiply(int input){
        return addUp (input, 0);
    }

    private int addUp(final int input, int counter){        
        if(counter == input){
            return 0;
        }
        return 1_000_000 + addUp(input, ++counter);
    }
    
}
