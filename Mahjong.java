/*
David Tsenter
Mahong - Version 4 BETA
This program is a version of the classic Mahjong game. It consists of an introduction screen on which there is a splash screen. Following the splash screen, the user is lead to
a menu where he/she will need to decide which function of the program they would like to pursue. There are 3 difficulties in the game, and the difference between them is the amount of time
given to complete the puzzle. The high scores option lets the user save the score they earned in the hard difficulty in a list of scores.

*program flow

Name        Type        Purpose
c           Console     to create a new instance of Console in RAM
hScores     String[]    to store the high scores
ROW         final int   to keep track of the number of rows in the array
COL         final int   to keep track of the numbers of columns in the array
layerNum    int         determines the layer number of the card
time        int         stores the amount of time the user gets to complete the puzzle (varies depending on difficulty)
selected    String      stores the first selected card value
tempX       int         stores the x coordinate of the first selected card
tempY       int         stores the y coordinate of the first selected card
oneSelected boolean     keeps track of whether a card is already selected
choice      String      stores the menu choice of the user
xCoord      int         stores current x coordinate (used in display to control the movement of cursor)
yCoord      int         stores the current y coordinate
result      String      to determine when a game is either won or lost
score       int         to store the score of the user (updates dynamically)
name        String      to store the name of the user (for high score list)
grid        int [][]    to store all the card values
n           TimingGame  to control the timer and how much time is given to the user
saved       boolean     used to identify whether the score should be saved or not

GLOBALS IN RETURNS
^INCORPORATE FINAL VARS IN LOOPS LIKE THIS
 (call right after lost, or maybe combine in an outcome method)

*/
import java.awt.*;
import java.io.*;
import hsa.*;
import java.util.*;

public class Mahjong
{
    Console c;
    String[] hScores = new String [10];
    static final int ROW = 6;
    static final int COL = 6;
    int layerNum;
    int time;
    String selected;
    int tempX, tempY;
    boolean oneSelected = false;
    String choice = "";
    int xCoord = 0, yCoord = 0;
    String result = "";
    int score = 0;
    String name = "";
    String grid[] [] = new String [ROW] [COL];
    TimingGame n;
    boolean saved = false;

    public void intro ()
    {
	SplashScreen s = new SplashScreen (c);
	s.run ();
    }


    private void initialize ()
    {
	grid [0] [0] = "E00";
	grid [0] [1] = "D00";
	grid [0] [2] = "400";
	grid [0] [3] = "500";
	grid [0] [4] = "200";
	grid [0] [5] = "900";

	grid [1] [0] = "700";
	grid [1] [1] = "370";
	grid [1] [2] = "8B0";
	grid [1] [3] = "7C0";
	grid [1] [4] = "4E0";
	grid [1] [5] = "A00";

	grid [2] [0] = "B00";
	grid [2] [1] = "610";
	grid [2] [2] = "891";
	grid [2] [3] = "E4C";
	grid [2] [4] = "670";
	grid [2] [5] = "200";

	grid [3] [0] = "800";
	grid [3] [1] = "E50";
	grid [3] [2] = "5B3";
	grid [3] [3] = "3A5";
	grid [3] [4] = "260";
	grid [3] [5] = "900";

	grid [4] [0] = "D00";
	grid [4] [1] = "AC0";
	grid [4] [2] = "480";
	grid [4] [3] = "9D0";
	grid [4] [4] = "A20";
	grid [4] [5] = "D00";

	grid [5] [0] = "300";
	grid [5] [1] = "B00";
	grid [5] [2] = "600";
	grid [5] [3] = "C00";
	grid [5] [4] = "100";
	grid [5] [5] = "100";
    }


    private void output ()
    {
	int row = 5, col = 18;
	int x, y;
	for (x = 0 ; x < 6 ; x++)
	{
	    for (y = 0 ; y < 6 ; y++)
	    {
		c.setCursor (row, col);
		c.print (actualValue (grid [x] [y]));
		row += 3;
		c.setCursor ((row - 4), (col - 2));
		if ((layerNum + 1) != 0)
		{
		    c.print ((layerNum + 1));
		}
		else
		{
		    c.print (" ");
		}
	    }
	    col += 8;
	    row = 5;
	}
	c.setCursor (3, 1);
	c.print ("Score: ");
	c.setCursor (3, 8);
	c.setCursor (3, 8);
	c.print (score);
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


    private void timingGame (int time)
    {
	n = new TimingGame (c, time);
	n.start ();

    }


    public void display (String choice)
    {
	char input;
	title ();
	result = "";
	initialize ();

	if (choice.equals ("1"))
	{
	    time = 500;

	}
	else if (choice.equals ("2"))
	{
	    time = 400;
	}
	else
	{
	    if (choice.equals ("3"))
	    {
		c.println ("Please enter your name: ");


		name = c.readString ();
		time = 300;
	    }
	}

	title ();
	timingGame (time);
	try
	{
	    Thread.sleep (100);
	}
	catch (InterruptedException e)
	{
	}
	c.setColor (Color.black);
	for (int x = 0 ; x <= 6 ; x++)
	{
	    c.drawRect (x * 60 + 120, 60, 0, 360);
	    c.drawRect (120, x * 60 + 60, 360, 0);
	}
	c.setColor (Color.red);
	c.drawRect (120 + xCoord * 60, 60 + yCoord * 60, 60, 60);


	while (result != "won" && result != "lost")
	{
	    output ();
	    if (n.timeL == 0)
	    {
		result = "lost";
		break;
	    }

	    input = c.getChar ();
	    c.setColor (Color.black);
	    c.drawRect (120 + xCoord * 60, 60 + yCoord * 60, 60, 60);
	    if (oneSelected)
	    {
		c.setColor (Color.green);
		c.drawRect (120 + tempX * 60, 60 + tempY * 60, 60, 60);
	    }
	    if (input == 97 && xCoord != 0)
		xCoord -= 1;
	    else if (input == 119 && yCoord != 0)
		yCoord -= 1;
	    else if (input == 100 && xCoord != 5)
		xCoord += 1;
	    else
	    {
		if (input == 115 && yCoord != 5)
		    yCoord += 1;
	    }

	    c.setColor (Color.red);
	    c.drawRect (120 + xCoord * 60, 60 + yCoord * 60, 60, 60);
	    if (input == '\n')
	    {
		control ();
	    }
	}
	try
	{
	    Thread.sleep (100);
	}
	catch (InterruptedException e)
	{
	}
	c.setCursor (23, 1);
	c.println ("You have " + result + " this game.");
	pauseProgram ();
    }


    private boolean gameWon ()
    {
	int s, z;
	for (s = 0 ; s < 6 ; s++)
	{
	    for (z = 0 ; z < 6 ; z++)
	    {
		if (!(grid [s] [z].equals ("000")))
		{
		    return false;
		}
	    }
	}
	return true;
    }


    private boolean gameLost ()
    {
	int t, d, g, h;

	for (t = 0 ; t < 6 ; t++)
	{
	    for (d = 0 ; d < 6 ; d++)
	    {
		if (available (t, d))
		{
		    for (g = 0 ; g < 6 ; g++)
		    {
			for (h = 0 ; h < 6 ; h++)
			{
			    if (available (g, h))
			    {
				if (actualValue (grid [g] [h]) == actualValue (grid [t] [d]) && (t != g && d != h))
				{
				    c.setCursor (10, 65);
				    c.print (actualValue (grid [g] [h]) + " " + actualValue (grid [t] [d]));
				    return false;
				}
			    }
			}
		    }
		}
	    }
	}

	return true;
    }


    private boolean sameCard ()
    {
	if (grid [tempX] [tempY].equals (grid [xCoord] [yCoord]))
	{
	    if (tempX == xCoord && tempY == yCoord)
	    {
		return true;
	    }
	}
	return false;
    }


    public void control ()
    {
	if (available (-1, -1))
	{
	    if (oneSelected)
	    {
		if (!sameCard ())
		{
		    if (matching (selected, grid [xCoord] [yCoord]))
		    {
			if (gameLost ())
			{
			    n.stop ();
			    result = "lost";
			}
			c.setCursor (23, 1);
			c.println ("MATCH!");
			try
			{
			    Thread.sleep (500);
			}
			catch (InterruptedException e)
			{
			}
			c.setCursor (23, 1);
			c.println ();
			oneSelected = false;
			grid [tempX] [tempY] = (newValue (selected));
			grid [xCoord] [yCoord] = (newValue (grid [xCoord] [yCoord]));
			score += 2;
			if (gameWon ())
			{
			    n.stop ();
			    result = "won";
			}

		    }
		    else
		    {
			oneSelected = false;
			error ("The cards don't match!");
			score--;
		    }
		    c.setColor (Color.black);
		    c.drawRect (120 + tempX * 60, 60 + tempY * 60, 60, 60);
		}
		else
		{

		    error ("You cannot select the same card!");
		}
	    }
	    else
	    {
		oneSelected = true;
		selected = grid [xCoord] [yCoord];
		tempX = xCoord;
		tempY = yCoord;
		//GREEN
		c.setColor (Color.green);
		c.drawRect (120 + tempX * 60, 60 + tempY * 60, 60, 60);
	    }

	}
	else
	{
	    if (grid [xCoord] [yCoord].equals ("000"))
	    {
		error ("This card has already been played");
	    }
	    else
	    {
		error ("You cannot play this card!");
	    }
	}
    }


    private boolean matching (String s1, String s2)
    {
	if (actualValue (s1) == actualValue (s2))
	{
	    return true;
	}
	return false;
    }


    private char actualValue (String s1)
    {
	for (layerNum = 2 ; layerNum >= 0 ; layerNum--)
	{
	    if (s1.charAt (layerNum) != '0')
	    {
		return (s1.charAt (layerNum));
	    }
	}
	return ' ';
    }


    private boolean available (int k, int b)
    {
	int temporaryX, temporaryY;
	if (k != -1 && b != -1)
	{
	    temporaryX = k;
	    temporaryY = b;
	}
	else
	{
	    temporaryX = xCoord;
	    temporaryY = yCoord;
	}
	for (int c = 2 ; c >= 0 ; c--)
	{
	    if (grid [temporaryX] [temporaryY].charAt (c) != '0')
	    {
		try
		{
		    if (grid [temporaryX + 1] [temporaryY].charAt (c) == '0' || grid [temporaryX - 1] [temporaryY].charAt (c) == '0')
		    {
			return true;
		    }
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
		    return true;
		}
	    }
	}
	return false;
    }


    private String newValue (String str)
    {
	String tempStr = (str.replace (actualValue (str), '0'));
	return tempStr;
    }


    private void error (String errorMsg)
    {
	c.setCursor (24, 48);
	c.println (errorMsg);
	try
	{
	    Thread.sleep (500);
	}


	catch (InterruptedException e)
	{
	}


	c.setCursor (24, 48);
	c.println ();
    }


    public void instructions ()
    {
	title ();
	c.println ("The rules of Mahjong are quite simple. The main goal is to continually match identical cards until no cards are left on the field.");
	c.println ("This becomes difficult however since a card can only be selected if there is an empty space to the left or to the right of it (this means that if the card is on the second layer, and there is no second layer cards to the left or right, it can be played).");
	c.println ("The layer number on which the card is on is indicated at the top left corner of the card, to facilitate the matching of cards.");
	c.println ("Once a succesful match has been made, both of the cards are removed from play, and the cards underneath them (if there are any) appear.");
	c.println ("You will need to hurry because there is a timer counting down, and when it reaches 0, you have lost the game.");
	c.println ("The easy and medium difficulties are really easy, so they are simply practice for the true test of skill; the hard difficulty. You will only be able to save high scores in the hard difficulty.");
	c.println ("Good luck!");
	pauseProgram ();
    }


    private void write ()
    {
	title ();
	PrintWriter out;
	try
	{
	    out = new PrintWriter (new FileWriter ("HighScores.txt"));
	    out.println ("Mahjong");
	    for (int x = 0 ; x < hScores.length ; x++)
	    {
		c.println (hScores [x]);
		out.println (hScores [x]);
	    }
	    out.close ();
	}
	catch (IOException e)
	{
	}
    }



    private void read ()
    {
	StringTokenizer st;
	String tempNum = "", tempName = "";
	PrintWriter out;
	try
	{
	    BufferedReader in = new BufferedReader (new FileReader ("HighScores.txt"));
	    if (in.readLine ().equals ("Mahjong"))
	    {
		for (int x = 0 ; x <= 9 ; x++)
		{
		    hScores [x] = in.readLine ();
		    if (saved)
		    {
			if (!(hScores [x].equals ("Anonymous 0")))
			{
			    st = new StringTokenizer (hScores [x]);
			    tempName = st.nextToken ();
			    tempNum = st.nextToken ();
			    if (score >= Integer.parseInt (tempNum))
			    {
				hScores [x + 1] = tempName + " " + tempNum;
				hScores [x] = name + " " + Integer.toString (score);
				x++;
				score = 0;
				c.println (hScores [x]);
			    }
			}
			else
			{
			    if (hScores [x].equals ("Anonymous 0") && x == 0 && score > 0)
			    {
				hScores [x] = name + " " + score;
			    }
			}
		    }
		}
	    }
	    else
	    {
		for (int s = 0 ; s < 10 ; s++)
		{
		    hScores [s] = "0";
		}
	    }
	}
	catch (IOException e)
	{
	    try
	    {
		out = new PrintWriter (new FileWriter ("HighScores.txt"));
		out.println ("Mahjong");

		for (int x = 0 ; x < 10 ; x++)
		{
		    if (saved && x == 0)
		    {
			hScores [x] = name + " " + score;
		    }
		    else
		    {
			hScores [x] = "Anonymous" + " " + "0";
			out.println ("Anonymous" + " " + "0");
		    }
		}
		read ();
	    }
	    catch (IOException ex)
	    {
		new Message ("The file could not be created");
	    }
	}
	catch (NumberFormatException e)
	{
	}
	catch (NullPointerException e)
	{
	}
    }


    public void displayHighScores ()
    {
	char answer;
	title ();
	if (!result.equals ("") && !name.equals ("")) //&&!result.equals("lost")
	{
	    while (true)
	    {
		c.println ("Would you like to save your score? (y/n)");
		answer = c.getChar ();
		if (answer == 'y')
		{
		    saved = true;
		    break;
		}
		else
		{
		    if (answer == 'n')
		    {
			saved = false;
			break;
		    }
		}
	    }

	}
	for (int x = 0 ; x <= 9 ; x++)
	{
	    hScores [x] = "0";
	}
	read ();
	write ();

	pauseProgram ();
    }


    public void goodBye ()
    {
	title ();
	c.println ("Thanks for using the Mahjong program!");
	pauseProgram ();
	c.close ();
    }


    private void pauseProgram ()
    {
	c.setCursor (24, 1);
	c.println ("Press any key to continue...");
	c.getChar ();
    }


    private void title ()
    {
	Font titleFont = new Font ("Calibri", 1, 20);
	c.clear ();
	c.println ();
	c.println ();
	c.setFont (titleFont);
	c.setColor (Color.black);
	c.drawString ("Mahjong", 280, 20);
    }


    public Mahjong ()
    {
	c = new Console ();
    }


    public static void main (String[] args)
    {
	Mahjong t = new Mahjong ();
	// t.intro ();
	do
	{
	    t.mainMenu ();
	    if (t.choice.equals ("1") || t.choice.equals ("2") || t.choice.equals ("3"))
		t.display (t.choice);
	    else if (t.choice.equals ("4"))
		t.instructions ();
	    else
	    {
		if (t.choice.equals ("5"))
		{
		    t.displayHighScores ();
		}
	    }
	}


	while (!t.choice.equals ("6"));
	t.goodBye ();

    }
}


