package priority;
import java.util.PriorityQueue;
import java.util.Collections;

public class TestPriority
{
public static void main(String...args)
  {
  PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(5);
  PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(5,Collections.reverseOrder());
  for(int i=5;i<100;i++)
    {
    maxHeap.add(i);
    if(maxHeap.size() > 5)
      {
      maxHeap.poll();
      }
    minHeap.add(i);
    if(minHeap.size() > 5)
      {
      minHeap.poll();
      }
    }
  printHeap(maxHeap);
  printHeap(minHeap);
  }
private static void printHeap(PriorityQueue pq)
  {
  while(!pq.isEmpty())
    {
    System.out.printf("%d,",pq.poll());
    }
  System.out.println();
  }
}
