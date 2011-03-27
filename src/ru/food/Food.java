package ru.food;

public class Food 
{
	public String location;
	public String name;
	public String date;
	public String meal;
	public String genre;
	public Food(String Location, String Name, String Date, String Meal, String Genre)
	{
		location = Location;
		name = Name;
		date = Date;
		meal = Meal;
		genre = Genre;
	}
	public String toString()
	{
		return "Location: " + location + ", Name: " 
		+ name + ", Date: " + date + ", Meal: " + meal +
		", Genre: " + genre;
	}
}
