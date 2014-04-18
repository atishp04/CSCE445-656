package com.canvas;

import java.io.IOException;

import learningandroid.helloandroid.GameActivity;
import learningandroid.helloandroid.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;




public class PlayScreen extends Activity implements OnClickListener,OnLongClickListener{
	private TextToSpeech myTts;
	CanvasClass canClass;
	private LinearLayout main;
	private LinearLayout handWLayout;
	private ImageView next;
	private ImageView back;
	private ImageView center;
	private ImageView left;
	private ImageView right;
	
	private TextView[] TV=new TextView[1];
	private TextView[] TViews=new TextView[5];
	public final static int mButtonHeight = 220;
	public final static int mButtonWidth = 80;
	String inst[]=new String[5]; ;
	String InstTemp;
	int flag=0;
	int c0flag=0;
	int c1flag=0;
	int c2flag=0;
	int c3flag=0;
	int SpaceSelFlag = 1;
	String newStr="";
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); 
		width = display.getWidth(); 
		height = display.getHeight(); 

		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		AssetInstaller assetInstaller = new AssetInstaller(getApplicationContext(), "projects");
		try {
			assetInstaller.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conObj = this;
		canvasClass = new CanvasClass(this,conObj);
		setContentView(R.layout.play_screen);
		handWLayout = (LinearLayout) findViewById(R.id.boxToWrite);
		handWLayout.setBackgroundColor(Color.BLUE);
		handWLayout.addView(canvasClass);
		
		center = (ImageView) findViewById(R.id.center);
		left = (ImageView) findViewById(R.id.left);
		right = (ImageView) findViewById(R.id.right);
		
		next = (ImageView) findViewById(R.id.button_next);
		back = (ImageView) findViewById(R.id.button_back);

		center.setImageResource(R.drawable.thinking_b);
		left.setImageResource(R.drawable.dino_left);
		right.setImageResource(R.drawable.handwrite_b);
		
		final int id = getIntent().getExtras().getInt("id", 0);
		next.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent_play = new Intent(getApplicationContext(), GameActivity.class);
				intent_play.putExtra("id", id+1);
				startActivity(intent_play);
			}
		});
		back.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent_play = new Intent(getApplicationContext(), GameActivity.class);
				intent_play.putExtra("id", id);
				startActivity(intent_play);
			}
		});

		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

	}
	protected void onActivityResult(
			int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				mTts = new TextToSpeech(this, new OnInitListener() {
					public void onInit(int status) {
						// TODO Auto-generated method stub

						//mTts.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);

					}
				});

			} else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent.setAction(
						TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}

	public void onClick(View v) {
		
		if(v==back){
			// Go to the previous screen
			Log.v(PLAY_TAG, "Back button pressed");
		}
		if(v==next){
			// Go to the next screen
			Log.v(PLAY_TAG, "Next button pressed");
		}
	}


	/* This method will handle the swipe across the edge. Calls Freepad once the
	   touch area reaches the right end of screen */
	public void ClearCanvas(){
		if(canvasClass != null){
			handWLayout.removeView(canvasClass);
			canvasClass = new CanvasClass(this,conObj);
			handWLayout.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, 
					((height/2)+130)));
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
		protected void onPreExecute(){
			dialog = ProgressDialog.show(PlayScreen.this, "Processing","Please wait...", true);
			
		}
	}
	public void CallingMethod(){
		ProgressdialogClass ObjAsy=new ProgressdialogClass();
		ObjAsy.execute();
	}
	public void FreePadCall(){
		
		
		//detected text is set here
		System.out.println("recognized character"+CanvasClass.character[0]);
		Toast.makeText(getApplicationContext(), CanvasClass.character[0], Toast.LENGTH_LONG).show();
		/*TV[0].setText(canvasClass.character[0]);
		String str1 = TV[0].getText().toString();*/
		mTts.speak(CanvasClass.character[0], 0, null);
		//HorizontalSV.setVisibility(View.VISIBLE);
		if(canvasClass != null){
			handWLayout.removeView(canvasClass);
			canvasClass.destroyDrawingCache();
			canvasClass = new CanvasClass(this,conObj);
			/*handWLayout.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT, 
					((height/2)+130)));*/
			handWLayout.addView(canvasClass);
		}
	}

	public boolean onLongClick(View v) {
		System.out.println("-----long click------");		
		return true;
	}
	
	int curr_indx = 0;
	public void SpeakOutChoices(){
		if(canvasClass != null){
			handWLayout.removeView(canvasClass);
			canvasClass = new CanvasClass(this,conObj);
			handWLayout.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, //don't use deprecated stuff. JS
					((height/2)+130)));
			handWLayout.addView(canvasClass);
		}
		System.out.println("index"+curr_indx +"---"+ CanvasClass.StrokeResultCount);
		if(curr_indx < CanvasClass.StrokeResultCount){
			TV[0].setText(CanvasClass.character[curr_indx]);
			String Choice1=CanvasClass.character[curr_indx];
			mTts.speak(Choice1, 0, null);
			curr_indx++;
			if(curr_indx == CanvasClass.StrokeResultCount)
				curr_indx = 0;
			
		}
	}
}