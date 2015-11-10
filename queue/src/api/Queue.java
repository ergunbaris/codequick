package api;

public class Queue<E>{
    private static class QueueNode<E>{
        private final E item;
        private QueueNode<E> next;

        private QueueNode(E item){
            this.item = item;
        }
    }
    
    private QueueNode<E> tail,head;

    public void add(E item){
        // TODO test for input
        QueueNode<E> curNode = new QueueNode<E>(item);
        if(null == tail){
            tail = head = curNode;
        }else{
            tail.next = curNode;
            tail = tail.next;
        }
    }

    public E remove(){
        if(null == head){
            throw new QueueEmptyException();
        }
        QueueNode<E> hTemp = head;
        head = head.next;
        if(null == head){
            tail = head;
        }
        hTemp.next = null;
        return hTemp.item;        
    }

    public E peek(){
        if(null == head){
            throw new QueueEmptyException();
        }
        return head.item;
    }

    public boolean isEmpty(){
        return head == null;
    }
    
}
