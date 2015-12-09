package route;
import java.util.*;

public class OneWayTrip
{

  private final Ticket [] tickets;
  private final Map<String,Ticket> tMap = new HashMap<>();
  private final Set<String> destSet = new HashSet<>();
  public static void main (String...args)
    {
    Ticket [] tickets = new Ticket[4];
    tickets[1] = new Ticket("Istanbul","Paris");
    tickets[0] = new Ticket("Paris", "Munich");
    tickets[3] = new Ticket("Munich", "Zurich");
    tickets[2] = new Ticket("Zurich", "London");
    display(tickets);
    new OneWayTrip(tickets).sort();
    display(tickets);
    }
  public static void display(Ticket[] tickets)
  {
  System.out.println("Tickets:");
  for(Ticket ticket:tickets)
    {
      System.out.printf("%s,%s%n",ticket.source, ticket.dest);
    }
  System.out.println();
  }
  public OneWayTrip(Ticket [] tickets)
    {
    this.tickets = tickets;
    initializeDataStructs();
    }
  public void sort()
    {       
    Ticket root = findRootTicket();
    sort(root);
    }
  private void initializeDataStructs()
    {
    for(Ticket t:tickets)
      {           
      tMap.put(t.source, t);
      destSet.add(t.dest);
      }    
    }
  private Ticket findRootTicket()
    {
    for(Ticket ticket:tickets)
      {
      if(!destSet.contains(ticket.source))
        {
        return ticket;
        }
      }
    throw new IllegalArgumentException("Not a one way trip");
    }
  private void sort(Ticket rootTicket)
    {
    int index = 0;
    Ticket curTicket = rootTicket;
    do
      {    
      tickets[index++] = curTicket;
      }
    while((curTicket = tMap.get(curTicket.dest)) != null);
    }
}

class Ticket
{
  String dest; // TODO get set accesors
  String source; // TODO get set accesors
  Ticket(String source, String dest)
    {
    this.dest = dest;
    this.source = source;
    }
}

