package arsu.rufood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseHall extends Activity {
	
	private OnItemClickListener Hall_Selection = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			Intent nextScreen = new Intent();
			nextScreen.setClassName("arsu.rufood", "arsu.rufood.ListFood");
			switch (position) {
			case 0:
				nextScreen.putExtra("arsu.rufood.DiningHall", "Brower Commons");
				break;
			case 1:
				nextScreen.putExtra("arsu.rufood.DiningHall", "Busch Dining Hall");
				break;
			case 2:
				nextScreen.putExtra("arsu.rufood.DiningHall", "Tillett Dining Hall");
				break;
			case 3:
				nextScreen.putExtra("arsu.rufood.DiningHall", "Neilson Dining Hall");
				break;
			}

			startActivity(nextScreen);
		}
	};
	
	private void assignListeners() {
		ListView list = (ListView)findViewById(R.id.hallsList);
		list.setOnItemClickListener(Hall_Selection);

	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosehall);
        
        setTitle("Choose Dining Hall:");
        
        assignListeners();

    }
}
