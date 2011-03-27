package ru.food;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

public class LunchLady 
{
	public LunchLady(Activity Parent)
	{
		fileName = "rawData";
		parent = Parent;
		loadRawData();
	}
	public LunchLady(ArrayList<Food> foodList)
	{
		foods = foodList;
		locked = true;
	}
	private void update()
	{
		Calendar cal = Calendar.getInstance();
		if(locked)
		{
			return;
		}
		if(!fileFound)
		{
			getRawData();
		}
		else if(cal.get(Calendar.DAY_OF_MONTH)!=lastUpdate.get(Calendar.DAY_OF_MONTH))
		{
			getRawData();
		}
	}

	private void getRawData()
	{
		String temp = SSLFuuu.getRawData();
		if(temp!=null)
		{
			rawData = temp;
			init();
		}
		else
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(parent);
			builder.setMessage("Wrecked");
			builder.show();
		}
	}
	private void loadRawData()
	{
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		try 
		{
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(parent.openFileInput(fileName)));
			Date temp = df.parse(reader.readLine());
			lastUpdate = Calendar.getInstance();
			lastUpdate.setTime(temp);
			rawData = reader.readLine();
			reader.close();
			fileFound = true;
			init();
		} 
		catch (FileNotFoundException e) 
		{
		} 
		catch (IOException e) 
		{
		} 
		catch (ParseException e) 
		{
		}
	}
	public void saveRawData()
	{
		FileOutputStream fos;
		try 
		{
			fos = parent.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(lastUpdate.getTime().toString().getBytes());
			fos.write('\n');
			fos.write(rawData.getBytes());
			fos.write('\n');
			fos.close();
		} 
		catch (FileNotFoundException e) 
		{
		} 
		catch (IOException e) 
		{
		}
	}
	private void init()
	{
		foods = new ArrayList<Food>();
		String cLocation = null;
		String cDate = null;
		String cMeal = null;
		String cGenre = null;

		String[] splitData = rawData.split("\"");

		for(int i = 0; i < splitData.length; i++)
		{
			if(splitData[i].contains("location_name"))
			{
				cLocation = splitData[i+2];
			}
			else if(splitData[i].contains("date"))
			{
				cDate = splitData[i+2];
			}
			else if(splitData[i].contains("meal_name"))
			{
				cMeal = splitData[i+2];
			}
			else if(splitData[i].contains("genre_name"))
			{
				cGenre = splitData[i+2];
			}
			else if(splitData[i].contains("items"))
			{
				i+=2;
				while(!splitData[i].contains("]"))
				{
					if(!splitData[i].contains(","))
					{
						String cName = splitData[i];
						Food f = new Food(cLocation, cName, cDate, cMeal, cGenre);
						foods.add(f);
					}
					i++;
				}
			}
		}
		DateFormat df = new SimpleDateFormat("EEE, MMM dd, yyyy");
		try 
		{
			Date temp = df.parse(cDate);
			lastUpdate = Calendar.getInstance();
			lastUpdate.setTime(temp);
		} 
		catch (ParseException e)
		{
		}
		saveRawData();
	}
	public ArrayList<Food> searchNames(ArrayList<Favorite> term)
	{
		update();
		ArrayList<Food> output = new ArrayList<Food>();
		if(foods==null)
		{
			//Error
			return output;
		}
		for(Food f : foods)
		{
			for(Favorite t : term)
			{
				if(f.name.equalsIgnoreCase(t.name))
				{
					output.add(f);
				}
			}
		}
		return output;
	}
	public ArrayList<Food> atLocation(ArrayList<String> term)
	{
		update();
		ArrayList<Food> output = new ArrayList<Food>();
		for(Food f : foods)
		{
			for(String t : term)
			{
				if(f.location.equalsIgnoreCase(t))
				{
					output.add(f);
				}
				break;
			}
		}
		return output;
	}
	public ArrayList<Food> filter(String loc, String meal)
	{
		update();
		ArrayList<Food> output = new ArrayList<Food>();
		for(Food f : foods)
		{
			if(f.meal.equals(meal)&&f.location.equals(loc))
			{
				output.add(f);
			}
		}
		return output;
	}
	public ArrayList<Food> filter(String loc, String meal, Favorites favs)
	{
		update();
		ArrayList<Food> output = new ArrayList<Food>();
		for(Food f : foods)
		{
			for(Favorite s : favs.favorites)
			{
				if(f.meal.equals(meal)&&f.location.equals(loc)&&f.name.equals(s.name))
				{
					output.add(f);
				}
			}
		}
		return output;
	}
	public Food served(String Name)
	{
		update();
		for(Food f : foods)
		{
			if(f.name.contains(Name))
			{
				return f;
			}
		}
		return null;
	}
	public ArrayList<Food> foodArray()
	{
		update();
		return foods;
	}
	public LunchLady fLock()
	{
		update();
		return new LunchLady(foods);
	}
	private ArrayList<Food> foods;
	public Calendar lastUpdate;
	public String rawData;
	private String fileName;
	public Activity parent;
	boolean fileFound = false;
	boolean locked = false;
}
