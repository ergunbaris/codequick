package shift;

public class BitShift{
    public static void main(String ... args){
        int sample = -878;
        System.out.printf("sample=%d %n", sample);
        for ( int i=0; i<40; i++){
            sample >>= 1; 
        }
        System.out.printf("sample (after 40 arithmetic shifts)=%d %n", sample);
        sample = 878;
        System.out.printf("sample=%d %n", sample);
        for ( int i=0; i<40; i++){
            sample >>= 1; 
        }
        System.out.printf("sample (after 40 arithmetic shifts)=%d %n", sample);
        sample = -878;
        System.out.printf("sample=%d %n", sample);
        for ( int i=0; i<40; i++){
            sample >>>= 1; 
        }
        System.out.printf("sample (after 40 logical shifts)=%d %n", sample);
        sample = 878;
        System.out.printf("sample=%d %n", sample);
        for ( int i=0; i<40; i++){
            sample >>>= 1; 
        }
        System.out.printf("sample (after 40 logical shifts)=%d %n", sample);
        
    }
}
