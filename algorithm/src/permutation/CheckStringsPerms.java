package permutation;
/*
* RETRORESPECTIVE:
*       Good:
        - Was able to discuss other algorithms and saw the possibility that there is a better algorithm
*       Bad:
*       - I forgot the utilization of charsets for BCR hence ı did know that approach
        - map value must be get processed and put back to its key value 
        - Should ask questions like shoudl whitespaces be considered loudly
        - map size is with size() not length() I knew but writing code needs good testing by eye!
        Keep an eye on:
*       
*/

import java.util.*;

public class CheckStringsPerms{
    public static void main(String...args){
        // TODO better stderr message
        if(args.length != 2){
            throw new IllegalArgumentException("check inputs!");
        }
        System.out.printf("Is %s permutation of %s answer=%b%n",args[0],args[1],
            new CheckStringsPerms().isPermutations(args[0],args[1]));
    }
    public boolean isPermutations(String str1, String str2){
        if(str1 == null || null == str2 || str1.length() != str2.length()){
            return false;
        }
        int [] str1CharOccurence = new int[65536];
        int [] str2CharOccurence = new int[65536];
        // O(1) or O(C) memory; 
        countChars(str1CharOccurence, str1); 
        countChars(str2CharOccurence, str2);
        // O(N) runtime

        return Arrays.equals(str1CharOccurence,str2CharOccurence);
    }
    
    private void countChars(int charsCount[], String str){
        for(int i=0;i<str.length();i++){
            charsCount[str.charAt(i)]++;
        }
    }

}
