/*
This class is a simple queue program, a queue of events.
It instanciates the event objects and stores them in the queue.
*/

public class EventQ
{
	int max=100;
	int top=-1;
	public Event qu[]=new Event[max];
	
	EventQ()
	{}
	
	EventQ(EventQ a)
	{
		top=a.top;
		for(int i=0;i<=a.top;i++)
		{
			qu[i]=new Event(a.qu[i].occured_at_x,a.qu[i].occured_at_y,a.qu[i].dir);
		}
	}
	
	//this functions adds an event at the top of the queue
	public void addEvent(int x,int y,int d)
	{
		if(top<max)
			qu[++top]=new Event(x,y,d);
		else
			System.out.println("Event Queue is full.");
		//System.out.println("Event Added.");
	}	
	
	//this fucntion deletes an event from the bottom of the queue
	public void deleteEvent()
	{
		//display();
		if(top>0)
		{
			for(int i=0;i<top;i++)
			{
				qu[i]=qu[i+1];
			}
		}
		if(top>=-1) top--;
		//System.out.println("Deleted. ");
	}
	
	//this function is used to display the list events in the queue
	public void display()
	{
		if(!isEmpty())
		{
		System.out.println("List of Event : ");
		for(int i=0;i<=top;i++)
			System.out.println(i+" : X = "+qu[i].occured_at_x+" Y = "+qu[i].occured_at_y+" D = "+qu[i].dir);
		}
	}
	
	//this function will check if the queue is empty
	public boolean isEmpty()
	{
		if(top==-1)
			return true;
		else
			return false;
	}
}