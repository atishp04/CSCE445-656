package learningandroid.helloandroid;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MediaPlayer mP; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button button1 = (Button) findViewById(R.id.button1);
		final Button button2 = (Button) findViewById(R.id.button2);
		final Button mute = (Button) findViewById(R.id.mute);
		final Button credits = (Button) findViewById(R.id.button3);
		
		final TextView mainTitle = (TextView) findViewById(R.id.mainTitle);
		final TextView subTitle = (TextView) findViewById(R.id.subTitle1);
		
		mP= MediaPlayer.create(getApplicationContext(), R.raw.theme);
		try {
			mP.setVolume(0.33f, 0.33f);
			mP.setLooping(true);
			mP.start();

		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		}
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/KGPrimaryPenmanship.ttf");
		mute.setTypeface(typeFace);
		button1.setTypeface(typeFace);
		button2.setTypeface(typeFace);
		credits.setTypeface(typeFace);
		mainTitle.setTypeface(typeFace);
		subTitle.setTypeface(typeFace);
		
		mute.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!mP.isPlaying()){
					mP.start();
					mute.setText("| |");
				}else{
					mP.pause();
					mute.setText(">");
				}
			}
			
		});
		
		credits.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				try {
					Intent intent_learn = new Intent(MainActivity.this, CreditsActivity.class);
					startActivity(intent_learn);
					
				} catch (Exception e) {
					Log.e("Home Page", e.toString());
				}
			}
			
		});
		
		
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
	
	@Override
	protected void onStop(){
		if(mP.isPlaying()){
			mP.stop();
		}
		
		super.onStop();
		
	}
	
	@Override
	protected void onDestroy(){
		mP.stop();
		mP.release();
		super.onDestroy();
		
	}
	
	@Override
	protected void onResume(){
		if(!mP.isPlaying()){
			mP.reset();
			mP = MediaPlayer.create(getApplicationContext(), R.raw.theme);
			mP.setVolume(0.33f, 0.33f);
			mP.start();
		}
		super.onResume();
		
		
	}
	
	@Override
	public void onBackPressed(){
		mP.stop();
		super.onBackPressed();
		
	}
}
