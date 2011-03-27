package arsu.rufood;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ru.food.*;

public class Favorites extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		populate();
	}
	void populate()
	{
		favs = new ru.food.Favorites("favorites", this);
		lunchLady = new LunchLady(this);
		
		ListView favorites = new ListView(this);
		ArrayList<String> s = new ArrayList<String>();
		for(Favorite f : favs.favorites)
		{
			s.add(f.name);
		}
		ArrayList<Food> foods = lunchLady.fLock().searchNames(favs.favorites);
		for(Food f : foods)
		{
			s.add(f.name + " is being served at " + f.location + " for " + f.meal);
		}
		
		Collections.sort(s);
		
		ArrayAdapter<String> a = new ArrayAdapter<String>(this,R.layout.list_item,s);
		favorites.setAdapter(a);
		favorites.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String s = ((TextView)view).getText().toString();
				if(!s.contains("is being served at"))
				{
					confirmDelete(s);
				}
			}
		});
		setContentView(favorites);
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
				populate();
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
	LunchLady lunchLady;
	ru.food.Favorites favs;
}
