package addnodes;
import api.Node;

public class AddNodes{
    public static void main (String ... args){
        Node <Integer> node1 = new Node<Integer>(9);
        Node <Integer> nodeCur1 = node1;
        for(int i =8;i>5;i--){
            nodeCur1.setNext( new Node<Integer>(i));
            nodeCur1 = nodeCur1.next();
        }
        new AddNodes().printNode(node1);
        Node <Integer> node2 = new Node<Integer>(3);
        Node <Integer> nodeCur2 = node2;
        for(int i =4;i<8;i++){
            nodeCur2.setNext( new Node<Integer>(i));
            nodeCur2 = nodeCur2.next();
        }
        new AddNodes().printNode(node2);

        Node<Integer> result = new AddNodes().addNodes(node1,node2);
        new AddNodes().printNode(result);
        
        Node<Integer> result2 = new AddNodes().addNodes2(node1,node2);
        new AddNodes().printNode(result2);
        
    }
    
    public Node<Integer> addNodes(Node<Integer> node1, Node<Integer> node2){
        if(null == node1 || null == node2){
            return null;
        }
        int size1 = size(node1);
        int size2 = size(node2);
        
        if(size1 > size2){
            node2 = padZeros(node2,size1-size2);
        }else if (size2 > size1){
            node1 = padZeros(node1,size2-size1);
        }
        Node<Integer> result = null;
        Node<Integer> firstResult=null;
        int carry = 0;
        Node<Integer> curNode1 = node1;
        Node<Integer> curNode2 = node2;
        while(curNode1 != null){
            int sumDigit = curNode1.getItem() + curNode2.getItem() + carry;
            carry = sumDigit / 10;
            int valToSet = sumDigit%10;
            if(null == result){
                result = new Node<Integer>(valToSet);
                firstResult = result;
            }else{
                result.setNext(new Node<Integer>(valToSet));
                result = result.next();
            }
            curNode1 = curNode1.next();            
            curNode2 = curNode2.next();
        }
        if(carry == 1){
            result.setNext(new Node<Integer>(1));
        }
        return firstResult;
                
    }

    public Node<Integer> addNodes2(Node<Integer> node1, Node<Integer> node2){
        if(null == node1 || null == node2){
            return null;
        }
        int number1 = getNodeIntValue(node1);
        int number2 = getNodeIntValue(node2);
        int sum = number1 + number2;
        System.out.println(sum);
        Node<Integer> firstResult = null;
        Node<Integer> result = null;
        int digitVal =0;
        int exp = 1;
        while((digitVal=(int)(sum%Math.pow(10.0,exp)))!=sum){
            if(result == null){
                result = new Node<Integer>(digitVal);
                firstResult = result;
            }else{
                result.setNext( new Node<Integer>((int)(digitVal/Math.pow(10.0,exp-1))));
                result = result.next();
            }
            exp++;
        }
        result.setNext( new Node<Integer>((int)(digitVal/Math.pow(10.0,exp-1))));
        return firstResult;
        
    }
    
    private int getNodeIntValue(Node<Integer> node){
        int number=0;
        Node<Integer> curNode = node;
        int exp = 0;
        while(curNode != null){
            number += (int)(curNode.getItem()*Math.pow(10.0,exp));            
            exp++;
            curNode = curNode.next();
        }
        return number;
    }

    public int size(Node<Integer> node){
        if(null == node){
            return 0;
        }
        Node<Integer> current = node;
        int size = 1;
        while(current.next()!=null){
            size++;
            current = current.next();
        }
        return size;
    }

    public Node<Integer> padZeros(Node<Integer> node, int zerosTodPad){
        if(null == node){
            return null;
        }
        Node<Integer> current = node;
        while(current.next() != null){
            current = current.next();
        }
        for (int i=0;i<zerosTodPad;i++){
            current.setNext(new Node<Integer>(0));
            current = current.next();
        }
        return node;
    }

    public void printNode(Node<Integer> node){
        if(node == null){
            System.out.printf("Empty Node%n");
            return;
        }
        Node<Integer> curNode = node;
        while(curNode != null){
            System.out.printf("%d->",curNode.getItem());
            curNode = curNode.next();
        }
        System.out.println();
    }
}
