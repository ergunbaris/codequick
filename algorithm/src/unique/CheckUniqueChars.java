package unique;

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
        int [] charOccurenceArray = new int[65536];
        for(int asciiVal=0;asciiVal<65536;asciiVal++){
            // O(1) runtime and memory
            charOccurenceArray[asciiVal] = 0;
        }
        for(int charIndex=0;charIndex<input.length();charIndex++){
            // O(N) runtime
            char curChar = input.charAt(charIndex);
            charOccurenceArray[curChar]++;
            if(charOccurenceArray[curChar] > 1){
                return false;
            }
        }
        return true;
        
    }
}
