package learningandroid.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends Activity {

	public Integer[] gameMaps = { R.drawable.map_all, R.drawable.map_apple };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		int gameMapId = getIntent().getIntExtra("id", 0);
		if (gameMapId < 0 || gameMapId >= gameMaps.length) 
			gameMapId = 0;
		final int topassId = gameMapId;
		ImageView imgView = (ImageView) findViewById(R.id.imageView1);
		imgView.setImageResource(gameMaps[gameMapId]);
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						com.canvas.PlayScreen.class);
				// passing array index
				i.putExtra("id", topassId);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
