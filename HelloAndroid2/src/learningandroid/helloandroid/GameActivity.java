package learningandroid.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends Activity {
	public static char currLvl = 'A';
	// public Integer[] gameMaps = { R.drawable.map_all, R.drawable.map_apple };
	// private Integer[] gameMaps = { R.drawable.overworld_title,
	// R.drawable.overworld_1 };
	private Integer[] gameMaps = new Integer[] { R.drawable.p_01,
			R.drawable.p_02, R.drawable.p_03, R.drawable.p_04, R.drawable.p_05,
			R.drawable.p_06, R.drawable.p_07, R.drawable.p_08, R.drawable.p_09,
			R.drawable.p_10, R.drawable.p_11, R.drawable.p_12, R.drawable.p_13,
			R.drawable.p_14, R.drawable.p_15, R.drawable.p_16, R.drawable.p_17,
			R.drawable.p_18, R.drawable.p_19, R.drawable.p_20, R.drawable.p_21,
			R.drawable.p_22, R.drawable.p_23, R.drawable.p_24, R.drawable.p_25,
			R.drawable.p_26, R.drawable.p_27 }; 
			/*
			 * These are not the overworld maps themselves, but the path maps that
			 * reflect the progress of the player. They are overlaid on top of the overworld map.
			 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		

		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/KGPrimaryPenmanship.ttf");

		int gameMapId = getIntent().getIntExtra("id", 0);
		if (gameMapId < 0 || gameMapId >= gameMaps.length)
			gameMapId = 0;
		final int topassId = gameMapId;
		Log.e("Map", gameMapId + "");
		
//		ImageView imgView = (ImageView) findViewById(R.id.imageView1);
		final ImageView imgView = (ImageView) findViewById(R.id.pathImage);
		//we now just overlay a path on top of the background image.
		//we can update the path whenever they guess the current letter correctly.
		imgView.setImageResource(gameMaps[gameMapId]);
		
		
		final TextView desc = (TextView) findViewById(R.id.lvl_description);
		desc.setTypeface(typeFace); //to help if parents want to play with kids.
		
		final Button b = (Button) findViewById(R.id.button1);
		b.setTypeface(typeFace);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						com.canvas.PlayScreen.class);
				Bundle bundle = getIntent().getExtras();
				
				if(bundle != null)
					i.putExtras(bundle); //this is how we make sure the
											//PlayScreen updates properly
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
