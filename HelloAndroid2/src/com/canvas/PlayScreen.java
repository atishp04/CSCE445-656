package com.canvas;

import java.io.IOException;

import learningandroid.helloandroid.GameActivity;
import learningandroid.helloandroid.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.PorterDuff;

public class PlayScreen extends Activity implements OnClickListener,
		OnLongClickListener {
	CanvasClass canClass;
	private LinearLayout main;
	private LinearLayout handWLayout;
	private ImageView next;
	// private ImageView back;
	private TextView back;
	private ImageView center;
	private ImageView left;
	private GifMovieView right;

	private TextView[] TV = new TextView[1];
	private TextView[] TViews = new TextView[5];
	public final static int mButtonHeight = 220;
	public final static int mButtonWidth = 80;
	String inst[] = new String[5];;
	String InstTemp;
	int flag = 0;
	int c0flag = 0;
	int c1flag = 0;
	int c2flag = 0;
	int c3flag = 0;
	int SpaceSelFlag = 1;
	String newStr = "";
	int height;
	public static int width;
	Vibrator v;
	/** Called when the activity is first created. */
	CanvasClass canvasClass = null;
	PlayScreen conObj = null;
	private final static String PLAY_TAG = "PLAYSCREEN";

	public final int MY_DATA_CHECK_CODE = 1;
	private TextToSpeech mTts = null;
	public ProgressDialog dialog;
	private Intent pass;
	private boolean won = false;
	private MediaPlayer match, mismatch;
	RatingBar winingStarBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();

		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		AssetInstaller assetInstaller = new AssetInstaller(
				getApplicationContext(), "projects");
		try {
			assetInstaller.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

		conObj = this;
		canvasClass = new CanvasClass(this, conObj);
		setContentView(R.layout.play_screen);

		handWLayout = (LinearLayout) findViewById(R.id.boxToWrite);
		handWLayout.setBackgroundColor(Color.WHITE);
		handWLayout.addView(canvasClass);

		center = (ImageView) findViewById(R.id.center);
		left = (ImageView) findViewById(R.id.left);
		right = (GifMovieView) findViewById(R.id.right);
		winingStarBar = (RatingBar) findViewById(R.id.ratingBar1);
		// next = (ImageView) findViewById(R.id.button_next);
		// back = (ImageView) findViewById(R.id.button_back);
		back = (TextView) findViewById(R.id.button_back);

		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/KGPrimaryPenmanship.ttf");
		back.setTypeface(typeFace);
		back.setAlpha(0.33f);

		TextView rules = (TextView) findViewById(R.id.rules);
		rules.setTypeface(typeFace);

		int currTrace = getIntent().getIntExtra("trace", R.drawable.trace_a);
		int currSpeech = getIntent().getIntExtra("speech", R.drawable.speech_a);

		center.setImageResource(currSpeech);
		left.setImageResource(R.drawable.dino_left);
		right.setMovieResource(currTrace);
		// next.setOnClickListener(this);

		// this is how we track which speech and trace images we're on.
		// R keeps sequentially named resources with sequential integers.
		center.setTag(currSpeech);
		right.setTag(currTrace);

		back.setOnClickListener(this);

		/*
		 * Audio objects.
		 */
		match = MediaPlayer.create(getApplicationContext(), R.raw.win);
		mismatch = MediaPlayer.create(getApplicationContext(), R.raw.lose);
		try {
			match.setVolume(0.33f, 0.33f);
			mismatch.setVolume(0.33f, 0.33f);

		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		}

		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				mTts = new TextToSpeech(this, new OnInitListener() {
					public void onInit(int status) {
						// mTts.speak("Hello World", TextToSpeech.QUEUE_FLUSH,
						// null);
						mTts.setSpeechRate(0.75f);
						mTts.setPitch(0.75f);
						mTts.speak("Draw the letter feed the dragon", 0, null);
						
					}
				});

			} else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				// startActivity(installIntent);
				startService(installIntent);
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onClick(View v) {
		//
		if (v == back) {
			/*
			 * This means we've clicked the button to see the map, not the
			 * hardware back button JS
			 */
			if (pass == null) {
				pass = new Intent(this, GameActivity.class);
				pass.putExtras(getIntent().getExtras());
			}
			finish();
			startActivity(pass);
		}
		// //This is not currently in use. JS
		// if (v == next) {
		// // Go to the next screen
		// Log.v(PLAY_TAG, "Next button pressed");
		// }
	}

	/*
	 * This method will handle the swipe across the edge. Calls Freepad once the
	 * touch area reaches the right end of screen
	 */
	public void ClearCanvas() {
		if (canvasClass != null) {
			handWLayout.removeView(canvasClass);
			canvasClass = new CanvasClass(this, conObj);
			handWLayout.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					((height / 2) + 130)));
			handWLayout.addView(canvasClass);
		}
	}

	class ProgressdialogClass extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... unsued) {
			canvasClass.addStroke(canvasClass._currentStroke);
			return null;
		}

		@Override
		protected void onPostExecute(String sResponse) {
			dialog.dismiss();
			FreePadCall();
		}

		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(PlayScreen.this, "Processing",
					"Please wait...", true);

		}
	}

	public void CallingMethod() {
		ProgressdialogClass ObjAsy = new ProgressdialogClass();
		ObjAsy.execute();
	}
	
	/*@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mTts.shutdown();
	}*/
	
	public void FreePadCall() {

		// detected text is set here
		System.out.println("recognized character: " + canvasClass.character[0]);
//		Toast.makeText(getApplicationContext(), canvasClass.character[0],
//				Toast.LENGTH_LONG).show();
		/*
		 * TV[0].setText(canvasClass.character[0]); String str1 =
		 * TV[0].getText().toString();
		 */

		// mTts.speak(canvasClass.character[0], 0, null);
		// HorizontalSV.setVisibility(View.VISIBLE);

		/*
		 * hold on to canvas character.
		 */
		if (winCheck(canvasClass.character[0])) {
			if (!won) {
				advanceLevel();
				won = true; // only advance the map if we haven't yet for this
							// letter JS
			}
			// First play the character sound
			mTts.speak(canvasClass.character[0], 0, null);
			
			mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
				
				@Override
				public void onStart(String utteranceId) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onError(String utteranceId) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onDone(String utteranceId) {
					// TODO Auto-generated method stub
					Log.i("Check", "tts sound play done");
				}
			});
			
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LayerDrawable stars = (LayerDrawable) winingStarBar.getProgressDrawable();
			stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
			winingStarBar.setVisibility(0);
			match.start(); //play the win sound. JS
			//TODO: This is where we would change the dragon image to happy. JS
			
			
		} else {
			LayerDrawable stars = (LayerDrawable) winingStarBar.getProgressDrawable();
			stars.getDrawable(2).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
			
			winingStarBar.setVisibility(0);
			back.setAlpha(0.33f);
			back.setText("See Map");
			won = false;
			mismatch.start(); //play the lose sound. JS
			//TODO: This is where we would change the dragon image to sad. JS
		} 

		

		if (canvasClass != null) {
			handWLayout.removeView(canvasClass);
			canvasClass.destroyDrawingCache();
			canvasClass = new CanvasClass(this, conObj);
			/*
			 * handWLayout.setLayoutParams(new LinearLayout.LayoutParams(
			 * LinearLayout.LayoutParams.FILL_PARENT, ((height/2)+130)));
			 */
			handWLayout.addView(canvasClass);
		}
	}

	public boolean onLongClick(View v) {
		System.out.println("-----long click------");
		return true;
	}

	int curr_indx = 0;

	public void SpeakOutChoices() {
		if (canvasClass != null) {
			handWLayout.removeView(canvasClass);
			canvasClass = new CanvasClass(this, conObj);
			handWLayout.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					((height / 2) + 130)));
			handWLayout.addView(canvasClass);
		}
		System.out.println("index" + curr_indx + "---"
				+ CanvasClass.StrokeResultCount);
		if (curr_indx < CanvasClass.StrokeResultCount) {
			TV[0].setText(CanvasClass.character[curr_indx]);
			String Choice1 = CanvasClass.character[curr_indx];
			mTts.speak(Choice1, 0, null);
			curr_indx++;
			if (curr_indx == CanvasClass.StrokeResultCount)
				curr_indx = 0;

		}
	}

	protected void onDestroy() {
		if (mTts != null) {
			mTts.shutdown();
		}
		match.release();
		mismatch.release();
		super.onDestroy();
	}


	private boolean winCheck(String received) {
		return received.charAt(0) == (char) getIntent().getIntExtra("id", 0) + 65;
	}

	/*
	 * Update the bundle with the new image and map ids.
	 */
	private void advanceLevel() {
		int c2 = getIntent().getIntExtra("id", 0);

		if (pass == null) {
			pass = new Intent(this, GameActivity.class);
		}

		pass.putExtra("id", ++c2);
		int s = (Integer) center.getTag();
		int t = (Integer) right.getTag();
		pass.putExtra("speech", ++s);
		pass.putExtra("trace", ++t);
		Log.e("speech", pass.getIntExtra("speech", -1) + "");
		Log.e("trace", pass.getIntExtra("trace", -1) + "");
		back.setAlpha(1);
		back.setText("Next Level!");
	}

	/*
	 * Handles pressing the back button. Makes super call.
	 * @see android.app.Activity#onBackPressed()
	 */
	public void onBackPressed() {
		Log.v(PLAY_TAG, "Back button pressed");
		if (pass == null)
			pass = new Intent(this, GameActivity.class);
		finish();
		startActivity(pass);
		super.onBackPressed();
	}

	protected void onResume() {

		/*
		 * If we want to let them use the hardware back button to navigate, this
		 * will allow them to replay levels.
		 */
		if (back != null && !won) {
			back.setAlpha(0.33f);
			
			back.setText("See Map");
			won = false;
		}
		
		super.onResume();
	}

}