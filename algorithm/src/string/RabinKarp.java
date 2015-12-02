package string;

public class RabinKarp
  {
  private static final int ALPHABET_SIZE = 128;
  public static void main(String...args)
    {
    boolean isSub = new RabinKarp().isSubstring(args[0], args[1]);
    System.out.printf("key=%s phrase=%s isSub=%b%n", args[1], args[0], isSub);
    }

  public boolean isSubstring(String phrase, String key)
  {
  int keyLen = key.length();
  if(phrase.length() < keyLen)
    {
    return false;
    }
  int [] arr = getRabinArray(phrase, keyLen);
  int keyHash = hashRabin(key);
  for(int i=0;i<arr.length;i++)
    {
    if(arr[i] == keyHash)
      {
      int count = 0;
      for(int j=0;j<keyLen;j++)
        {
        if(phrase.charAt(i+j) != key.charAt(j))
          {
          break;
          }
        else
          {
          count++;
          }
        }
      if(count==keyLen)
        {
        return true;
        }
      }
    }
  return false;
  }
  public int [] getRabinArray(String phrase, int keySize)
    {
    if(phrase.length() < keySize)
      {
      return null;
      }
    int [] rabinArr = new int[phrase.length()+1-keySize];
    int i = 0;
    int hash = hashRabin(phrase.substring(0,keySize));
    rabinArr[i] = hash;
    i++;
    while(i<phrase.length()+1-keySize)
      {
      hash -= (int)Math.pow(ALPHABET_SIZE,2)*phrase.charAt(i-1);
      hash *= ALPHABET_SIZE;
      hash += phrase.charAt(i+keySize-1);
      rabinArr[i] = hash;
      i++;
      }
    return rabinArr;
    }
  private int hashRabin(String sub)
    {
    int hash = 0;
    int pow = sub.length()-1;
    for(int i=0;i<sub.length();i++)
      {
        hash += (int)Math.pow(ALPHABET_SIZE,pow)*sub.charAt(i);
        pow--;
      }
    return hash;
    }
  }
