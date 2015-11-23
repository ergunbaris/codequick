package ops;

public class GetBit{

    public static void main (String ... args){
        int num = Integer.parseInt(args[0],2);
        byte bit = Byte.parseByte(args[1]);
        System.out.printf("num=%d, bit=%d is set=%b%n", num, bit , new GetBit().getBit(num,bit));
    }

    public boolean getBit(int number, byte bit){
        return (number & (1 << bit)) != 0;
    }
}

