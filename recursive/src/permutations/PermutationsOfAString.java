package permutations;

import java.util.*;

public class PermutationsOfAString{
    public static void main(String...args){
        // TODO check args
        Set<String> results = (new PermutationsOfAString()).createPermutations(args[0]);
        for (String str:results){
            System.out.printf("%s,",str);
        }
        System.out.println();
    }
    
    public Set<String> createPermutations(String str){
        Set<String> perms = new HashSet<>();
        createPermutations(perms,str, str.substring(0,1),0);
        return perms;
        
    }

    private void createPermutations(Set<String> permSet, String str, String curStr, int index){
        if(index == str.length()){
            permSet.add(curStr);
        }else{
            for(int i=0;i <= curStr.length();i++){
                StringBuilder strBuild = new StringBuilder(curStr);
                strBuild.insert(i,str.charAt(index));
                createPermutations(permSet, str, strBuild.toString(), index++);
            }
        }
    }
}
