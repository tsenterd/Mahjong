// The "SplashScreen" class.
import java.awt.*;
import hsa.Console;

public class SplashScreen implements Runnable
{
    Console c;           // The output console

    public void blink (Color col)
    {
	for (int x = 0 ; x < 110 ; x += 10)
	{
	    c.setColor (col);
	    c.fillOval (x * 5, 0, 50, 50);
	    c.fillOval (0, x * 5, 50, 50);
	    c.fillOval (x * 5, 450, 50, 50);
	    c.fillOval (550, x * 5, 50, 50);
	}
	try
	{
	    Thread.sleep (300);
	}
	catch (InterruptedException e)
	{
	}
    }


    public void display ()
    {

	int x;
	Font mahjongF = new Font ("Arial", 1, 60);
	Font nameF = new Font ("Calibri", 1, 20);
	c.setColor (Color.blue);
	c.fillRect (0, 0, 640, 500);

	for (x = 0 ; x < 110 ; x += 10)
	{
	    c.setColor (Color.black);
	    c.fillOval (x * 5, 0, 50, 50);
	    c.fillOval (0, x * 5, 50, 50);
	    c.fillOval (x * 5, 450, 50, 50);
	    c.fillOval (550, x * 5, 50, 50);
	}
	c.setFont (mahjongF);
	for (x = 0 ; x < 100 ; x++)
	{

	    c.setColor (Color.blue);
	    c.drawString ("MAHJONG", 240, 99 + x);
	    c.setColor (Color.red);
	    c.drawString ("MAHJONG", 240, 100 + x);
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
	for (x = 0 ; x < 200 ; x++)
	{
	    c.setColor (Color.red);
	    c.drawString ("MAHJONG", 240, 200);
	    c.setColor (Color.blue);
	    c.drawString ("SOLITAIRE", 100, 99 + x);
	    c.setColor (Color.green);
	    c.drawString ("SOLITAIRE", 100, 100 + x);
	    try
	    {
		Thread.sleep (5);
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
	c.setFont (nameF);
	for (x = 0 ; x < 300 ; x++)
	{

	    c.setColor (Color.blue);
	    c.drawString ("By: David Tsenter", 79 + x, 400);
	    c.setColor (Color.gray);
	    c.drawString ("By: David Tsenter", 80 + x, 400);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
	for (x = 0 ; x < 5 ; x++)
	{
	    blink (Color.yellow);
	    blink (Color.black);
	}

    }


    public SplashScreen (Console con)
    {
	c = con;
    }


    public void run ()
    {
	display ();
    }
} // SplashScreen class
