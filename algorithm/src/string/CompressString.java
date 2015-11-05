package string;
/* RETRORESPECTIVE:
*       Good:
        - started with the best algorithm and described another complex to implement algorithm which maybe in place
        - mentioned about StringBuilder amortised runtime on doubling array size.
*       Bad:
        - could mention why String not used instead O(kn^2)
        - code reuse
        Keep an eye on:
*       
*/
public class CompressString{
    public static void main(String...args){
        // TODO test args
        System.out.printf("input:%s compressed:%s%n",args[0],new CompressString().compress(args[0]));
        
    }
    public String compress(String input){
        // O(N) memory -> O(1) difficult? need to set the last bits of char array to \0 
        // what affect does ths have? and if we exceeed size than first we need to
        // calculate the expected size and do the replacement on same array
        // O(N) runtime
        if(input == null || input.length()<3){
            return input;
        }
        StringBuilder build = new StringBuilder("");
        Character prev = input.charAt(0);
        int sameCharCount = 1;
        for(int i=1;i<input.length();i++){
            Character cur = input.charAt(i);
            if(prev == cur){
                sameCharCount++;
            }else{                
                build.append(prev);
                build.append(sameCharCount);
                sameCharCount = 1;
                if(build.length() >= input.length()){
                    return input;
                }
            }
            if(i == input.length()-1){
                build.append(cur);
                build.append(sameCharCount);
                if(build.length() >= input.length()){
                    return input;
                }
            }
            prev = cur;
        }
        return build.toString();
    }
}
