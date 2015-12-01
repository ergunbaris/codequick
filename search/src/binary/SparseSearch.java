package binary;

public class SparseSearch
{
  public static void main(String...args)
  {
    String [] arr = {"aba","","","","","","bobo","","","can","","dede","","","","","","poi","","","","zaza"};
    System.out.printf("key=%s is found on index=%d%n",args[0],new SparseSearch().find(arr,args[0]));
  }
  public int find(String [] arr, String key)
  {
  if(key.isEmpty())
    {
      return -1; // should we handle?
    }
    return find(arr, key.toLowerCase(), 0, arr.length-1);
  }
  
  private int find(String [] arr, String key, int start, int end)
  {
  int mid = (end+start)/2;
  if(start>mid)
    {
    return -1;
    }

  if(!arr[mid].isEmpty())
  {
  int comp = key.compareTo(arr[mid].toLowerCase());
  if(comp == 0)
    {
    return mid;
    }
  else if(comp>0)
    {
    return find(arr, key, mid+1,end);
    }
  else
    {
    return find(arr,key,start,mid-1);
    }
  }
  else
    {
    int index = find(arr, key, mid+1,end);
    if(index != -1)
      {
      return index;
      }
    return find(arr,key,start,mid-1);
    }
  }
}
