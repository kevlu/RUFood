package arsu.rufood;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TabHost;

public class ListFood extends TabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.listfood);	

	    Bundle info = getIntent().getExtras();
	    String choice = info.getString("arsu.rufood.DiningHall");
       // String choice = (String) savedInstanceState.get("arsu.rufood.DiningHall");

        setTitle(choice);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	    Time time = new Time();
	    
	//    savedInstanceState.get("arsu.rufood.DiningHall");


	    if (time.weekDay != 0 && time.weekDay != 6) {
		    intent = new Intent().setClass(this, TabBreakfast.class);
		    intent.putExtra("arsu.rufood.DiningHall", choice);
		    spec = tabHost.newTabSpec("breakfast").setIndicator("Breakfast",
		                      res.getDrawable(R.drawable.iconoriginal))
		                  .setContent(intent);
		    tabHost.addTab(spec);
	    }

	    
	    intent = new Intent().setClass(this, TabLunch.class);
	    intent.putExtra("arsu.rufood.DiningHall", choice);
	    if (time.weekDay != 0 && time.weekDay != 6) {
		    spec = tabHost.newTabSpec("lunch").setIndicator("Lunch",
		    		res.getDrawable(R.drawable.iconoriginal))
		    		.setContent(intent);
	    } else {
		    spec = tabHost.newTabSpec("lunch").setIndicator("Brunch",
                    res.getDrawable(R.drawable.iconoriginal))
                    .setContent(intent);
	    	
	    }
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, TabDinner.class);
	    intent.putExtra("arsu.rufood.DiningHall", choice);
	    spec = tabHost.newTabSpec("dinner").setIndicator("Dinner",
	    		res.getDrawable(R.drawable.iconoriginal))
	    		.setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, TabTakeout.class);
	    intent.putExtra("arsu.rufood.DiningHall", choice);
	    spec = tabHost.newTabSpec("takeout").setIndicator("Knight Room",
	    		res.getDrawable(R.drawable.iconoriginal))
	    		.setContent(intent);
	    tabHost.addTab(spec);

	    if (time.weekDay != 0 && time.weekDay != 6)
		    if (time.hour >= 7 && time.hour < 11) {
		    	tabHost.setCurrentTab(0);
		    } else if (time.hour >= 11 && time.hour < 16) {
		    	tabHost.setCurrentTab(1);
		    } else if (time.hour >= 16 && time.hour < 20) {
		    	tabHost.setCurrentTab(2);
		    } else {
		    	tabHost.setCurrentTab(3);
		    }
	    else
	    	if (time.hour >= 9 && time.hour < 4) {
	    		tabHost.setCurrentTab(0);
	    	} else if (time.hour >= 4 && time.hour < 8) {
	    		tabHost.setCurrentTab(1);
	    	} else {
	    		tabHost.setCurrentTab(2);
	    	}
	}

}
