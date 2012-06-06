/*
This is the main class of the program. It makes use of the Part Class to instantiate the snake parts.
It contains logic for handling the key events that occur each time.
*/

import java.awt.*;
import java.awt.event.*;

public class Snake extends Frame implements KeyListener
{
	//declaring the parts of the snake
	int max=100;
	int part_count=4;
	Part snake_part[]=new Part[max];
	int score=0;
	Part food;
	boolean game_end;
	//variables used after the game end condition becomes true
	int cnt=0;
	boolean flash=true;
	//declaring the co-ordinates for the snake
	int x,y;
	
	Snake()
	{
		super("Snake Game");
		game_end=false;
		x=150;y=150;		
		//initializing the snake parts
		snake_part[0]=new Part(x,y,1,'h');
		snake_part[1]=new Part(x+10,y,1,'b');
		snake_part[2]=new Part(x+20,y,1,'b');
		snake_part[3]=new Part(x+30,y,1,'t');
		food=new Part(250,250,0,'f');
		//setting the frame properties
		setVisible(true);
		setSize(500,500);		
		//adding key listener to the frame
		addKeyListener(this);
	}
	
	public void keyTyped ( KeyEvent e )
	{}
    
	public void keyPressed ( KeyEvent e )
	{
		if(e.getKeyCode()==37)
		{
			//Left arrow
			if(snake_part[0].d!=1 && snake_part[0].d!=3)
			{
				//adding an event to the event queue of each part
				for(int i=0;i<part_count;i++)
					snake_part[i].eq.addEvent(snake_part[0].x,snake_part[0].y,1);
			}
		}
		else if(e.getKeyCode()==39)
		{
			//Right arrow
			if(snake_part[0].d!=3 && snake_part[0].d!=1)
			{
				//adding an event to the event queue of each part
				for(int i=0;i<part_count;i++)
					snake_part[i].eq.addEvent(snake_part[0].x,snake_part[0].y,3);
			}			
		}
		else if(e.getKeyCode()==40)
		{
			//Down arrow
			if(snake_part[0].d!=2 && snake_part[0].d!=4)
			{
				//adding an event to the event queue of each part
				for(int i=0;i<part_count;i++)
					snake_part[i].eq.addEvent(snake_part[0].x,snake_part[0].y,2);			
			}
		}
		else if(e.getKeyCode()==38)
		{
			//Up arrow
			if(snake_part[0].d!=4 && snake_part[0].d!=2)
			{
				//adding an event to the event queue of each part
				for(int i=0;i<part_count;i++)
					snake_part[i].eq.addEvent(snake_part[0].x,snake_part[0].y,4);				
			}
		}
	} 
    
	public void keyReleased ( KeyEvent e )
	{}
	
	public void paint(Graphics g)
	{
	try
	{
		if(!game_end)
		{
			g.drawRect(100,100,310,310);
			//food
			drawFood(g);
			drawSnake(g);
			g.drawString("Score : "+score,100,450);
		}
		else
		{
			if(cnt==4)
			{
				g.drawString("Game Over.",100,100);
				g.drawString("Your Score : "+score,100,130);
			}
			else
			{
				g.drawRect(100,100,310,310);
				g.drawString("Score : "+score,100,450);
				if(flash)
				{
					drawSnake(g);
					flash=false;
					cnt++;
				}
				else
					flash=true;
				Thread.sleep(1000);
				repaint();
			}
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	}
	
	/*
	this function is used the draw the food partical that the snake has to gather,
	and if that food is reached a new food is generated randomly
	*/
	public void drawFood(Graphics g)
	{
		if(snake_part[0].isReached(food))
		{
			int x=(int)(Math.random()*300)+100;
			int y=(int)(Math.random()*300)+100;
			x=x-(x%10);
			y=y-(y%10);
			//the x and y generated should not be a part of the snake
			food=new Part(x,y,0,'f');
			//increaing the size of the snake			
			//snake_part[part_count-1]=new Part(snake_part[part_count-1].x,snake_part[part_count-1].y,snake_part[part_count-1].d,'b');
			//snake_part[part_count-1].qu=
			snake_part[part_count-1].changeType('b');
			if(snake_part[part_count-1].d==1)
				snake_part[part_count]=new Part(snake_part[part_count-1].x+10,snake_part[part_count-1].y,snake_part[part_count-1].d,'t');
			else if(snake_part[part_count-1].d==2)
				snake_part[part_count]=new Part(snake_part[part_count-1].x,snake_part[part_count-1].y-10,snake_part[part_count-1].d,'t');
			else if(snake_part[part_count-1].d==3)
				snake_part[part_count]=new Part(snake_part[part_count-1].x-10,snake_part[part_count-1].y,snake_part[part_count-1].d,'t');
			else if(snake_part[part_count-1].d==4)
				snake_part[part_count]=new Part(snake_part[part_count-1].x,snake_part[part_count-1].y+10,snake_part[part_count-1].d,'t');
			snake_part[part_count].eq=new EventQ(snake_part[part_count-1].eq);
			part_count++;
			score++;
		}
		food.drawPart(g);
	}
	
	//this function draws a snake (x,y) co-ordinates correspond to mouth of the snake
	public void drawSnake(Graphics g)
	{		
		for(int i=0;i<part_count;i++)
		{
			//checking for collision condition for the head part
			if(i==0 && !game_end)
			{
			for(int j=1;j<part_count;j++)
				if(snake_part[i].isReached(snake_part[j]))
				{
					game_end=true;
					repaint();
					break;
				}
			}
			snake_part[i].drawPart(g);
			if(!game_end)
			{
				snake_part[i].changeDirection();
				snake_part[i].getNextStep();
			}
		}
	}
	
	//this function calls the paint method repeatedly after a time delay of 1 sec
	public void mainMethod()
	{
		try
		{
		for(int i=1;i>0;i++)
		{
			if(!game_end)
			{
				repaint();
				Thread.sleep(300);				
			}
			else
				break;
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	//this is the main method which creates an object for the snake clase and calls the mainMethod()
	public static void main(String args[])
	{
		Snake obj=new Snake();
		obj.mainMethod();
	}
}