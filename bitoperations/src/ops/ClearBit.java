package ops;

public class ClearBit{

    public static void main (String ... args){
        int num = Integer.parseInt(args[0],2);
        byte bit = Byte.parseByte(args[1]);
        int tempNum = num;
        num = new ClearBit().clearBit(num,bit);
        System.out.printf("num=%d, bit=%d is cleared %s%n", tempNum, bit , Integer.toBinaryString(num));
    }

    public int clearBit(int number, byte bit){
        return number & ~(1 << bit);
    }
}

