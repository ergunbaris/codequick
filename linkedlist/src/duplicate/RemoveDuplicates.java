package duplicate;
import api.Node;
import java.util.*;

public class RemoveDuplicates<T>{
    private final Node<T> first;

    public static void main (String ... args){
        if(args.length != 1){
            throw new IllegalArgumentException();
        }
        int numberOfNodes = Integer.parseInt(args[0]);
        Node<Integer> first = generateLinkedList(numberOfNodes);
        RemoveDuplicates<Integer> dups1 = new RemoveDuplicates<>(first);
        long fastStartTime = System.nanoTime();
        dups1.removeDupsFast();
        long fastEndTime = System.nanoTime();
        elapsedTime("fast", fastEndTime, fastStartTime);
        dups1.traverse();
        Node<Integer> first2 = generateLinkedList(numberOfNodes);
        RemoveDuplicates<Integer> dups2 = new RemoveDuplicates<>(first2);
        long inPlaceStartTime = System.nanoTime();
        dups2.removeDupsInPlace();
        long inPlaceEndTime = System.nanoTime();
        elapsedTime("inPlace", inPlaceEndTime, inPlaceStartTime);
        dups2.traverse();
        
    }
    private static void elapsedTime(String test, long end, long start){
        double elapsed = (double)(end - start) /1_000_000_000.0;
        System.err.printf("%s elapsedtimeinsec=%10.2f%n",test, elapsed);
    }

    public RemoveDuplicates(Node<T> first){
        this.first = first;
    }
    public void removeDupsFast(){
        Set<T> uniqueSet = new HashSet<T>();
        Node cur = this.first;
        while(cur!=null){
            if(!uniqueSet.contains(cur.getItem())){
                uniqueSet.add((T)cur.getItem());
            }else{
                remove(cur);
            }
            cur = cur.next();
        }
    }

    public void removeDupsInPlace(){
        Node cur = this.first;
        while(cur!=null){
            Node innerCur = cur;
            while(innerCur!=null){
               if(innerCur.getItem().equals(cur.getItem())){
                    remove(innerCur);
                }
                innerCur = innerCur.next();
            }
            cur = cur.next();
        }

    }

    private void remove(Node<T> cur){
        if(cur.prev() != null){
            cur.prev().setNext(cur.next());
        }
        if(cur.next() != null){
            cur.next().setPrev(cur.prev());
        }
    }

    private void traverse(){
        Node<T> cur = first;
        int count = 0;
        while(cur!=null){
            System.out.printf("%d%n",cur.getItem());
            cur = cur.next();
            count++;
        }
        System.err.printf("size:%d%n", count);
    }

    static Node<Integer> generateLinkedList(int nodeCount){
        Node<Integer> first = new Node<>(100);
        Node<Integer> cur = first;
        cur.setPrev(null);
        for (int index=0;index<nodeCount;index++){
            Node<Integer> newNode = null;
            if(index%97==0){
                newNode = new Node<>(97);
                
            }else{
                double randFact = Math.random();
                int randNumber = (int) (randFact * Integer.MAX_VALUE);
                newNode = new Node<>(randNumber);
            }
            cur.setNext(newNode);
            newNode.setPrev(cur);
            newNode.setNext(null);
            cur = newNode;                    
        }
        return first;
    }

    
}
