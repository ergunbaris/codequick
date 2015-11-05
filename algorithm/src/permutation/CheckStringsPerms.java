package permutation;
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
        Map<Character, Integer> str1Map = new HashMap<>();
        Map<Character, Integer> str2Map = new HashMap<>(); 
        // O(N) memory coud it be O(1)?
        constructStrMap(str1Map, str1); 
        constructStrMap(str2Map, str2);
        if(str1Map.length() != str2Map.length()){
            return false;
        }
        // O(N) runtime

        return str1Map.equals(str2Map); // O(N)
    }
    
    private void constructStrMap(Map<Character,Integer> strMap, String str){
        for(char found:str){            
            if(strMap.contains(found)){
                strMap.get(found)++; //?
            }else{
                strMap.put(found,1);
            }
        }
    }

}