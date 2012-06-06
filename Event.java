/*
This class encapsulates all the infromation about the event, 
the information include the x,y co-ordinates where the event has occured and the direction in which the snake part has to move.
*/

public class Event
{
	public int occured_at_x,occured_at_y,dir;
	Event(int x,int y, int d)
	{
		occured_at_x=x;
		occured_at_y=y;
		dir=d;
	}
}