package learningandroid.helloandroid;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.Menu;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button button1 = (Button) findViewById(R.id.button1);
		final Button button2 = (Button) findViewById(R.id.button2);
		
		
		
		button1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent intent_learn = new Intent(MainActivity.this, LearnScreen.class);
					startActivity(intent_learn);
				} catch (Exception e) {
					Log.e("Home Page", e.toString());
				}
			}
		});
		
		button2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent intent_play = new Intent(MainActivity.this, GameActivity.class);
					startActivity(intent_play);
				} catch (Exception e) {
					Log.e("Home Page", e.toString());
				}
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
