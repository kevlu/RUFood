package ru.food;

public class Favorite 
{
	public Favorite(String Name, int Rating)
	{
		name = Name;
		rating = Rating;
	}
	public String toString()
	{
		return name;
	}
	public static Favorite parseString(String s)
	{
		String[] ss = s.split("$");
		if(ss.length==2)
		{
			return new Favorite(ss[0], Integer.parseInt(ss[1]));
		}
		else
		{
			return new Favorite(ss[0],0);
		}
	}
	public String name;
	public int rating;
}
