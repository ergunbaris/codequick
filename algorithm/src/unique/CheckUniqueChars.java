package unique;

/*
* RETRORESPECTIVE:
*       Good
*       - Initial Algo is correct and precise
*       - Code compiled and algorithm was correct on first run
*       - It is not written down but I asked the questions like if it was ASCII chars or UTF-8
          chars and assumed that I was dealing with UTF-8
*       Bad:
*       - Could have used boolean array instead of integer array to preserve more space
*         because it wasnt necessary to count the occurences it was enough to fail on 
*         first reoccurence
*       - Didnt have to initialize all array members in local variable array. Initialzation
          would be necessarry on non array local variables only.
        Keep an eye on:
        - The runtimes were guessed correct it is allright to assume boolean array constant
          but the book says it can be argued to be o(c) where c is a variable number of charsets?
*       
*/

public class CheckUniqueChars{
    public static void main(String...args){
        // TODO test input args        
        System.out.printf("%s all characters are unique=%b%n", args[0], new CheckUniqueChars().areAllCharsUnique(args[0]));
        
    }
    
    // TODO Javadoc
    public boolean areAllCharsUnique(String input){
        if(null == input){
            throw new IllegalArgumentException("input cannot be null");
        }
        boolean [] charOccurenceArray = new boolean[65536];
        for(int charIndex=0;charIndex<input.length();charIndex++){
            // O(N) runtime
            char curChar = input.charAt(charIndex);
            if(charOccurenceArray[curChar]){
                return false;
            }
            charOccurenceArray[curChar] = true;
        }
        return true;
        
    }
}
