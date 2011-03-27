package arsu.rufood;

import android.os.Bundle;
import android.widget.ListView;

public class TabDinner extends FoodTabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    info   = getIntent().getExtras();
	    choice = info.getString("arsu.rufood.DiningHall");
	    time   = "Dinner";
	    list = new ListView(this);

	    populateList(choice, time);
	    setContentView(list);
	}

}