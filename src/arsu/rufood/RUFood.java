package arsu.rufood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RUFood extends Activity {
	
	private OnClickListener Main_ButtonMenus = new OnClickListener() {
		public void onClick(View v) {
			Intent nextScreen = new Intent();
			nextScreen.setClassName("arsu.rufood", "arsu.rufood.ChooseHall");
			startActivity(nextScreen);
		}
	};
	
	private OnClickListener Main_ButtonSettings = new OnClickListener() {
		public void onClick(View v) {
			Intent nextScreen = new Intent();
			nextScreen.setClassName("arsu.rufood", "arsu.rufood.Settings");
			startActivity(nextScreen);
		}
	};
	
	private OnClickListener Main_ButtonFavorites = new OnClickListener() {
		public void onClick(View v) {
			Intent nextScreen = new Intent();
			nextScreen.setClassName("arsu.rufood", "arsu.rufood.Favorites");
			startActivity(nextScreen);
		}
	};
	
	private void assignListeners() {
		Button btn = (Button)findViewById(R.id.buttonMenus);
		btn.setOnClickListener(Main_ButtonMenus);

		btn = (Button)findViewById(R.id.buttonSettings);
		btn.setOnClickListener(Main_ButtonSettings);
		btn = (Button)findViewById(R.id.buttonFavorites);
		btn.setOnClickListener(Main_ButtonFavorites);
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        assignListeners();
    }
}