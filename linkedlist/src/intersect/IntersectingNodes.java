package intersect;
import api.Node;

public class IntersectingNodes<E>{
    public static void main(String...args){
        Node<Integer> node1 = new Node<Integer>(0);
        Node<Integer> nodeIntersect = null;
        Node<Integer> curNode = node1;
        for(int i=1;i<10;i++){
            curNode.setNext(new Node<Integer>(i));            
            curNode = curNode.next();
            if(i%5==0){
                nodeIntersect = curNode;
            }
        }
        Node<Integer> node2 = new Node<Integer>(10);
        node2.setNext(new Node<Integer>(11));
        Node<Integer> curNode2 = node2.next();
        curNode2.setNext(nodeIntersect);

        new IntersectingNodes<Integer>().print(node1);
        new IntersectingNodes<Integer>().print(node2);

        
        Node<Integer> intersectNode = (new IntersectingNodes<Integer>()).findInterSectingNode(node1,node2);
        System.out.printf("intersecting node=%s%n", intersectNode!=null?intersectNode.getItem():"none");
        
    }

    Node<E> findInterSectingNode(Node<E> node1, Node<E> node2){
        if(null == node1 || null == node2){
            return null;
        }
        int size1 = size(node1);
        int size2 = size(node2);
        
        int diff = size1-size2;
        if(diff > 0){
            while(diff!=0){
                node1 = node1.next();
                diff--;
            }
        }else if(diff < 0){
            while(diff!=0){
                node2 = node2.next();
                diff++;
            }
        }

        while(node1 != null){ 
            if(node1 == node2){
                return node1;
            }else{
                node1 = node1.next();
                node2 = node2.next();
            }
            
        }
        return null;        
    }

    public int size(Node<E> node){
        if(null == node){
            return 0;
        }
        int size = 0;
        while(node!=null){
            size++;
            node = node.next();
        }
        return size;
    }
    
    public void print(Node<E> node){
        if(null == node){
            System.out.printf("Empty list%n");
        }
        while(node != null){
            System.out.printf("->%s", node.getItem());
            node = node.next();
        }
        System.out.println();
    }
    
}
