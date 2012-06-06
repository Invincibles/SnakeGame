import java.awt.*;

public class Test extends Frame
{
	Part food;
	
	Test()
	{
		food=new Part(200,340,0,'f');
		setVisible(true);
		setSize(500,500);
	}
	
	public void paint(Graphics g)
	{
		food.drawPart(g);
	}
	
	public void function() throws Exception
	{
		for(int i=1;i>0;i++)
		{
			int k1=(int)(Math.random()*300)+100;
			int k2=(int)(Math.random()*300)+100;
			int x=k1-(k1%10);
			int y=k2-(k2%10);
			food=new Part(x,y,0,'f');
			Thread.sleep(1000);
			repaint();
			//System.out.println(i+" X = "+x+" Y = "+y);
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		Test obj=new Test();
		obj.function();
	}
}