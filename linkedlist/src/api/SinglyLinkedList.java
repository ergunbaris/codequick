package api;

// TODO implements Iterable
public class SinglyLinkedList<E>{
    Node<E> first;
    Node<E> last;
    
    public void addLast(E e){
        if(null == first){
            first = last = new Node<E>(e);
        }else{
            Node<E> newNode = new Node<E>(e);
            last.setNext(newNode);
            last = newNode;
        }
    }
    
    public int size(){
        int nodeCount = 0;
        Node<E> current = first;
        while(current != null){
            nodeCount++;
            current = current.next();
        }
        return nodeCount;
    }
    
    public E kthElement(int k){
        Node<E> current = first;
        Node<E> current2 = first;
        int nodeCount = -1;
        // TODO code repetion?
        boolean withinRange = false;
        while(current != null){
            nodeCount++;
            if(nodeCount == k){
                withinRange = true;
                break;
            }
            current = current.next();
        }
        if(!withinRange){
            throw new IndexOutOfBoundsException(String.format("List size is %d but required element range is %d",nodeCount,k));
        }
        
        while(current != null){
            current = current.next();
            current2 = current2.next();
        }
        return current2.getItem();
        
    }

    public void deleteMidNode(){
        Node<E> one = first;
        Node<E> two = first;
        Node<E> previous = null;

        while(two.next() != null && two.next().next() != null){
            previous = one;
            one = one.next();
            two = two.next().next();
        }
        previous.setNext(one.next());
        one.setNext(null);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node<E> curNode = first;
        while(curNode.next() != null){
            builder.append(curNode.getItem() + ",");
            curNode = curNode.next();
        }
        return builder.toString(); 
    }

    public static void main(String...args){
        // TODO test input args
        int nodeCount = Integer.parseInt(args[0]);
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        for(int i =0; i < nodeCount; i++){
            list1.addLast(i);
        }
        System.out.printf("list size=%d%n", list1.size());
        int kth = Integer.parseInt(args[1]);        
        System.out.printf("%d element from the end=%d%n", kth, list1.kthElement(kth));
        
        System.out.printf("Before delete list=%s%n", list1);
        list1.deleteMidNode();
        System.out.printf("After delete list=%s%n", list1);

    }

}
