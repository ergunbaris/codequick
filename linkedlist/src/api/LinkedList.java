package api;
import java.util.Iterator;

public class LinkedList<E> implements Iterable<E>{
    Node<E> first = null;
    Node<E> last = null;

    public Node<E> getFirst(){
        return first;
    }
    public Node<E> getLast(){
        return last;
    }
    public void addFirst(E item){
        Node<E> firstNode = new Node<E>(item);
        if(null != first){
            first.setPrev(firstNode);
            firstNode.setNext(first);
        }else{ // means empty List
            last = firstNode;   
        }
        first = firstNode;
    }
    public void addLast(E item){
        Node<E> lastNode = new Node<E>(item);
        if(null != last){
            last.setNext(lastNode);
            lastNode.setPrev(last);
        }else{ // means empty List
            first = lastNode;   
        }
        last = lastNode;
    }

    public void add(E item, int index){
        int curIndex = -1;
        Node curNode = first;
        boolean added = false;
        do{
            curIndex++;
            if(index == curIndex){
                Node<E> newNode = new Node<E>(item);
                newNode.setNext(curNode);
                if(curNode != null){
                    newNode.setPrev(curNode.prev());
                    curNode.setPrev(newNode);
                }
                if(curNode.prev()!=null){
                    curNode.prev().setNext(newNode);
                }
                if(curIndex == 0){
                    first = newNode;
                }
                added = true;
            }
        }while((curNode = curNode.next())!=null);
        if(!added){
            throw new IndexOutOfBoundsException(String.format("index %d is out of bounds",index));
        }
    }

    public boolean remove(Object item){
        Node<E> curNode = first;
        while(curNode != null){
            if(item.equals(curNode.getItem())){
                return remove(curNode);
            }
            curNode = curNode.next();
        }
        return false;
    }

    private boolean remove(Node<E> curNode){
        if(null == curNode){
            return false;
        }
        if(curNode.prev() != null){
            curNode.prev().setNext(curNode.next());
        }
        if(curNode.next() != null){
            curNode.next().setPrev(curNode.prev());
        }
        return true;
    }

    public int size(){        
        Node<E> curNode = first;
        int curIndex = 0;
        while(curNode != null){
            curIndex++;
            curNode = curNode.next();
        }
        return curIndex;
    }

    public Iterator<E> iterator(){
        return new MyIterator<E>();
    }

    class MyIterator<E> implements Iterator<E>{
        Node<E> curNode = (Node)LinkedList.this.first;
        public boolean hasNext(){
            return curNode.next()!=null;
        }
        public E next(){
            curNode = curNode.next();
            return curNode.getItem();
        }

        public void remove(){
            LinkedList.this.remove(curNode);
        }
        
    }
}
