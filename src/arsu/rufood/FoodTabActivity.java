package arsu.rufood;
import java.util.ArrayList;

import ru.food.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FoodTabActivity extends Activity {
	protected void populateList(String choice, String time) {
		lunchLady = (new LunchLady(this)).fLock();
		favs = new ru.food.Favorites("favorites", this);
		ArrayList<Food> foods = lunchLady.filter(choice, time);
		ArrayList<Food> favorites = lunchLady.filter(choice, time, favs);
		ArrayList<String> s = new ArrayList<String>();
		if(!favorites.isEmpty())
		{
			s.add("Favorites:");
			for(Food f : favorites)
			{
				if(!s.contains(f.name))
				{
					s.add(f.name);
				}
			}
		}
		s.add("Food:");
		for(Food f : foods)
		{
			if(!s.contains(f.name))
			{
				s.add(f.name);
			}
		}
		ArrayAdapter<String> a = new ArrayAdapter<String>(this,R.layout.list_item,s);
		list.setAdapter(a);
		list.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String s = ((TextView)view).getText().toString();
				if(s.contains("Favorites:"))
				{
					return;
				}
				if(s.contains("Food:"))
				{
					return;
				}
				if(favs.containsName(s))
				{
					confirmDelete(s);
				}
				else
				{
					confirmAdd(s);
				}
			}
		});
	}
	void confirmAdd(final String f)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to add?")
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int id) 
			{
				favs.addFavorite(f, 5);
				favs.saveFavorites();
				populateList(choice, time);
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int id) 
			{
				dialog.cancel();
			}
		});
		builder.show();
	}
	void confirmDelete(final String f)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete?")
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int id) 
			{
				favs.deleteFav(f);
				favs.saveFavorites();
				populateList(choice, time);
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int id) 
			{
				dialog.cancel();
			}
		});
		builder.show();
	}
	Bundle info;
	String choice;
	String time;
	ListView list;
	ru.food.Favorites favs;
	LunchLady lunchLady;
}
