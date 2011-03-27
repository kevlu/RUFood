package ru.food;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

public class Favorites 
{
	public Favorites(String FileName, Activity Parent)
	{
		fileName = FileName;
		parent = Parent;
		loadFavorites();
	}
	private void loadFavorites()
	{
		favorites = new ArrayList<Favorite>();
		try 
		{
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(parent.openFileInput(fileName)));
			String line = null;
			while((line=reader.readLine())!=null)
			{
				favorites.add(Favorite.parseString(line));
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
		} 
		catch (IOException e) 
		{
		}
	}
	public void saveFavorites()
	{
		FileOutputStream fos;
		try 
		{
			fos = parent.openFileOutput(fileName, Context.MODE_PRIVATE);
			for(Favorite f : favorites)
			{
				fos.write(f.toString().getBytes());
				fos.write('\n');
			}
			fos.close();
		} 
		catch (FileNotFoundException e) 
		{
		} 
		catch (IOException e) 
		{
		}
	}
	public void addFavorite(Food f, int rating)
	{
		Favorite temp = new Favorite(f.name,rating);
		if(!containsName(f.name))
		{
			favorites.add(temp);
		}
	}
	public void addFavorite(String f, int rating)
	{
		Favorite temp = new Favorite(f,rating);
		if(!containsName(f))
		{
			favorites.add(temp);
		}
	}
	public boolean containsName(String name)
	{
		for(Favorite f : favorites)
		{
			if(f.name.equals(name))
			{
				return true;
			}
		}
		return false;
	}
	public int getRating(String name)
	{
		for(Favorite f : favorites)
		{
			if(f.name.equals(name))
			{
				return f.rating;
			}
		}
		return -1;
	}
	public void changeRating(Food f, int rating)
	{
		for(Favorite fav : favorites)
		{
			if(fav.name.equals(f.name))
			{
				fav.rating = rating;
			}
		}
	}
	public void deleteFav(Favorite Name)
	{
		favorites.remove(Name);
	}
	public void deleteFav(String Name)
	{
		for(int i = 0; i < favorites.size(); i++)
		{
			if(favorites.get(i).name.equals(Name))
			{
				favorites.remove(i);
				break;
			}
		}
	}
	public ArrayList<Favorite> favorites;
	private String fileName;
	Activity parent;
}
