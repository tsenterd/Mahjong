// The "Timer" class.
import java.awt.*;
import hsa.Console;

public class TimingGame extends Thread
{
    static Console c;           // The output console
    int time;
    int timeL;
    public void display (int timeLength)
    {
	c.setCursor (4, 65);
	c.print ("Time: ");
	for (int x = timeLength ; x >= 0 ; x--)
	{
	    c.print (x);
	    timeL = x;
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException e)
	    {
	    }
	    c.setCursor (4, 71);
	    c.println ();
	    c.setCursor (4, 71);
	}
    }


    public TimingGame (Console con, int timeLength)
    {
	c = con;
	time = timeLength;
    }


    public void run ()
    {
	display (time);
    }
} // Timer class
