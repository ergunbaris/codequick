import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {  
    private final StringBuilder strBuffer = new StringBuilder();
    private final Deque<Operation> opsStack = new ArrayDeque<>();
  
    public static void main(String[] args) {
      Solution solution = new Solution();
      solution.processInput();        
    }
    
    // TODO handling constraints
    public Solution()
      {
        
      }
    
    public void processInput()
      {
      try (Scanner scanner = new Scanner(System.in))
        {
        int Q = scanner.nextInt();
        for (int i = 0; i < Q; i++)
          {          
          processInput(scanner);
          }
        }
      finally
        {
        strBuffer.delete(0, strBuffer.length());
        }      
      
      }
    
    private void processInput(Scanner scanner)
      {
       Type type = Type.getTypeFromByte(scanner.nextByte());
       switch(type)
          {
          case APPEND:
             String str = scanner.next();         
             strBuffer.append(str);
             Operation opErase = new Operation(Type.ERASE,
                                          Integer.toString(str.length()));
             opsStack.push(opErase);
          break;
          case ERASE:
              int k = scanner.nextInt();
              int kFromEnd = strBuffer.length() - k;
              Operation opAppend = new Operation(Type.APPEND,
                                                strBuffer.substring(kFromEnd)
                                                );
              opsStack.push(opAppend);
              strBuffer.delete(kFromEnd, strBuffer.length());
          break;
          case GET:
            int kGet = scanner.nextInt();
            System.out.println(strBuffer.charAt(kGet - 1));
          break;
          case UNDO:            
            Operation undo = opsStack.pop();
            processOperation(undo);
          break;          
          default:
          break;
          }
      }
  
    private void processOperation(Operation op)
      {
      switch(op.type)
        {
        case APPEND:
          strBuffer.append(op.value);
          break;
        case ERASE:
          int k = Integer.parseInt(op.value);
          int kFromEnd = strBuffer.length() - k;
          strBuffer.delete(kFromEnd, strBuffer.length());
          break;
        default:
          break;
        }
      }
    static private class Operation
        {
        private final Type type;
        private final String value;
        Operation(Type type,
                  String value)
          {
          this.type = type;
          this.value = value;
          }
        }
    enum Type
        {
        APPEND((byte)1),
        ERASE((byte)2),
        GET((byte)3),
        UNDO((byte)4);
      
        private byte type;
      
        Type(byte type)
          {
          this.type = type;
          }
        byte getType()
          {
          return this.type;
          }
        static Type getTypeFromByte(byte type)
          {
          switch(type)
            {
            case 1:
              return APPEND;
            case 2:
              return ERASE;
            case 3:
              return GET;
            case 4:
              return UNDO;
            default:
              throw new  UnsupportedOperationException(
                String.format("operation %d not supported",
                              type));
            }
          }
        
        }
}
