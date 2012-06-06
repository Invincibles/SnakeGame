/*
This class encapsulates all the information about the snake part.
*/

import java.awt.*;

public class Part
{
	//(x,y) gives the current co-ordinates of the snake part and d gives the direction of the motion of snake part
	int x,y,d;
	//type define the type of the part head(h) or body(b)
	char type;
	//eq is the event queue that helps the snake part in changing the direction if an event has occured at the current location
	EventQ eq;
	Part(int x, int y, int d,char tp)
	{
		this.x=x;
		this.y=y;
		this.d=d;
		this.type=tp;
		eq=new EventQ();
	}
	
	Part(Part p1)
	{
		this.x=p1.x;
		this.y=p1.y;
		this.d=p1.d;
		this.eq=new EventQ(p1.eq);
	}
	//this method is used the change the type of the Part
	public void changeType(char c)
	{
		this.type=c;
	}
	
	//this method changes the direction of the snake part if an event has occured at the current location
	public void changeDirection()
	{
		if(!eq.isEmpty())
		{
			if(eq.qu[0].occured_at_x==this.x && eq.qu[0].occured_at_y==this.y)
			{
				this.d=eq.qu[0].dir;
				eq.deleteEvent();
			}
		}
	}
	
	//this method is used to draw the part of the snake, it can be a head(h) or body(b)
	public void drawPart(Graphics g)
	{
		switch(type)
		{
			case 'h': g.drawOval(x,y,10,10); g.drawLine(x+3,y+3,x+3,y+3); g.drawLine(x+3,y+7,x+3,y+7); break;
			case 'b': g.drawRect(x,y,10,10); break;
			case 't': 
						switch(d) 
						{
							case 1: g.drawLine(x,y,x,y+10);
									g.drawLine(x,y,x+10,y+5);
									g.drawLine(x,y+10,x+10,y+5);
									break;
							case 2: g.drawLine(x,y+10,x+10,y+10);
									g.drawLine(x,y+10,x+5,y);
									g.drawLine(x+10,y+10,x+5,y);
									break;
							case 3: g.drawLine(x+10,y,x+10,y+10);
									g.drawLine(x+10,y,x,y+5);
									g.drawLine(x+10,y+10,x,y+5);
									break;
							case 4: g.drawLine(x,y,x+10,y);
									g.drawLine(x,y,x+5,y+10);
									g.drawLine(x+10,y,x+5,y+10);
									break;
						}
					  break;
			case 'f': g.drawOval(x,y,10,10); break;
		}
	}
	
	//this function is used to test is a part is coinciding with another part 'p'
	boolean isReached(Part p)
	{
		if(this.x==p.x && this.y==p.y)
			return true;
		else
			return false;
	}
	
	/*
	this method calculates the next step of the snake part basing on the direction the snake part is moving,
	1 -> left
	2 -> down
	3 -> right
	4 -> up
	*/
	public void getNextStep()
	{
		switch(d)
		{
			case 1: if(x<=100) x=400; else x-=10; break;
			case 2: if(y>=400) y=100; else y+=10; break;
			case 3: if(x>=400) x=100; else x+=10; break;
			case 4: if(y<=100) y=400; else y-=10; break;
		}
	}
/*	
	public static void main(String args[])
	{
		Part obj1=new Part(100,100,1,'h');
		obj1.eq.addEvent(200,200,1);
		obj1.eq.addEvent(250,250,1);
		Part obj2=new Part(obj1);
		obj1.eq.deleteEvent();
		obj2.eq.display();
	}
*/
}