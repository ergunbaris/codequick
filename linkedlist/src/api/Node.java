package api;
import java.util.*;

public class Node<T>{    
    final T item;
    Node prev;
    Node next;
    public Node(T t){
        this.item = t;
    }
    public void setNext(Node node){
        this.next = node;
    }
    public void setPrev(Node node){
        this.prev = node;
    }
    public Node next(){
        return next;
    }
    public Node prev(){
        return prev;
    }
    public T getItem(){
        return item;
    }

    public static void main(String...args){
        Node<Integer> first = new Node<>(1);
        first.setPrev(null);
        Node<Integer> second = new Node<>(2);
        first.setNext(second);
        second.setPrev(first);
        Node<Integer> third = new Node<>(3);
        third.setPrev(second);
        second.setNext(third);
        Node<Integer> last = new Node<>(4);
        last.setPrev(third);
        third.setNext(last);
        last.setNext(null);

        Node n = first;
        do{
            System.out.println(n.getItem());
        }        
        while((n=n.next()) != null);

        n = last;
        do{
            System.out.println(n.getItem());
        }        
        while((n=n.prev()) != null);
        
    }
}

