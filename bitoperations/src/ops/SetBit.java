package ops;

public class SetBit{

    public static void main (String ... args){
        int num = Integer.parseInt(args[0],2);
        byte bit = Byte.parseByte(args[1]);
        int tempNum = num;
        num = new SetBit().setBit(num,bit);
        System.out.printf("num=%d, bit=%d is set to 1 %s%n", tempNum, bit , Integer.toBinaryString(num));
    }

    public int setBit(int number, byte bit){
        return number | (1 << bit);
    }
}

