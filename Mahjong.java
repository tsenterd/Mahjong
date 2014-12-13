/*
David Tsenter
Mahong - Version 1 alpha

*/

import java.awt.*;
import java.io.*;
import hsa.*;

public class Mahjong
{

    Console c;
    static final int ROW = 6;
    static final int COL = 6;
    static final int TOTAL = 56;
    String choice = "";
    int score;
    int grid[] [] = new int [ROW] [COL];

    public void intro ()
    {
	SplashScreen s = new SplashScreen (c);
    }


    public void mainMenu ()
    {
	title ();
	c.println ("Please select one of the options...");
	c.println ();
	c.println ("1. Easy");
	c.println ("2. Medium");
	c.println ("3. Hard");
	c.println ("4. Instructions");
	c.println ("5. High Scores");
	c.println ("6. Exit");


	while (true)
	{
	    choice = c.readString ();
	    if (!choice.equals ("1") && !choice.equals ("2") && !choice.equals ("3") && !choice.equals ("4") && !choice.equals ("5") && !choice.equals ("6"))
	    {
		new Message ("Please select one of the options!");
	    }
	    else
	    {
		break;
	    }
	    c.setCursor (11, 1);
	    c.println ();
	    c.setCursor (11, 1);
	}
    }


    public void display (String choice)
    {
	title ();
	//Draws the board
	for (int x = 0 ; x <= 420 ; x += 70)
	{
	    c.drawLine (80 + x, 70, 80 + x, 430);
	}
	for (int x = 0 ; x <= 360 ; x += 60)
	{
	    c.drawLine (80, 70 + x, 500, 70 + x);
	}
	pauseProgram ();
    }


    public void instructions ()
    {
	title ();
	c.println ("scum");
    }


    public void displayHighScores ()
    {
	title ();
	c.println ("FILEIO");
	pauseProgram ();
    }


    public void goodBye ()
    {
	title ();
	c.println ("Thanks for using the Mahjong program!");
    }


    private void pauseProgram ()
    {
	c.setCursor (24, 1);
	c.println ("Press any key to continue...");
	c.getChar ();
    }


    private void title ()
    {
	c.clear ();
	c.print (' ', 36);
	c.println ("Mahjong");
	c.println ();
    }


    public Mahjong ()
    {
	c = new Console ();
    }


    public static void main (String[] args)
    {
	Mahjong t = new Mahjong ();
	t.intro ();
	do
	{
	    t.mainMenu ();
	    if (t.choice.equals ("1") || t.choice.equals ("2") || t.choice.equals ("3"))
		t.display (t.choice);
	    else if (t.choice.equals ("4"))
		t.instructions ();
	    else
		t.displayHighScores ();

	}
	while (!t.choice.equals ("6"));
	t.goodBye ();

    }
}

