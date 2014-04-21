package com.canvas;

import java.io.IOException;

import learningandroid.helloandroid.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LearnScreenAlphabet extends Activity implements OnClickListener {
	CanvasClass canClass;
	private ImageView next;
	// private ImageView back;
	private ImageView back;
	private ImageView center;
	private ImageView left;
	private GifMovieView right;

	private int[] traceArray = { R.drawable.trace_a, R.drawable.trace_b,
			R.drawable.trace_c, R.drawable.trace_d, R.drawable.trace_e };

	private int[] speechArray = { R.drawable.speech_a, R.drawable.speech_b,
			R.drawable.speech_c, R.drawable.speech_d, R.drawable.speech_e };
	
	private String[] alphaStringArray = { "A", "B" , "C", "D"
			, "E"};
	
	private String[] voiceStringArray = { "Ay for apple", "B for banana" , "C for coconut", "D for donut"
			, "E for eggplant"};

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
	LearnScreenAlphabet conObj = null;
	private final static String PLAY_TAG = "PLAYSCREEN";

	public final int MY_DATA_CHECK_CODE = 1;
	private TextToSpeech mTts = null;
	public ProgressDialog dialog;
	private Intent pass;
	private boolean won = false;

	private int level = 0;

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

		setContentView(R.layout.learn_screen_alphabet);
		
		mTts = new TextToSpeech(this, new OnInitListener() {
			public void onInit(int status) {
				// mTts.speak("Hello World", TextToSpeech.QUEUE_FLUSH,
				// null);
				mTts.setSpeechRate(0.75f);
				playSound();
			}
		});
		

		center = (ImageView) findViewById(R.id.learncenter2);
		left = (ImageView) findViewById(R.id.learnleft2);
		right = (GifMovieView) findViewById(R.id.learnright2);

		level = getIntent().getIntExtra("id", 0);
		// TEMP HACK : 
		if(level > 4){
			level = 0;
		}
		//int currTrace = getIntent().getIntExtra("trace", R.drawable.trace_a);
		//int currSpeech = getIntent().getIntExtra("speech", R.drawable.speech_a);

		updateImages();
		
		//center.setImageResource(currSpeech);
		left.setImageResource(R.drawable.dino_left);
		//right.setMovieResource(currTrace);
		// next.setOnClickListener(this);

		// this is how we track which speech and trace images we're on.
		// R keeps sequentially named resources with sequential integers.
		//center.setTag(currSpeech);
		//right.setTag(currTrace);

		next = (ImageView) findViewById(R.id.learn_button_next);
		back = (ImageView) findViewById(R.id.learn_button_back);

		next.setOnClickListener(this);
		back.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.learn_button_back:
			if (this.level > 0) {
				this.level--;
				updateImages();
				playSound();
			}
			break;
		case R.id.learn_button_next:
			if (this.level < 4) {
				this.level++;
				updateImages();
				playSound();
			}
			break;
		}
	}

	private void updateImages() {
		center.setImageResource(speechArray[level]);
		right.setMovieResource(traceArray[level]);
	}
	
	private void playSound() {
		mTts.speak(alphaStringArray[level], android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
		mTts.speak(voiceStringArray[level], android.speech.tts.TextToSpeech.QUEUE_ADD, null);
	}
	
	protected void onDestroy() {
		if (mTts != null) {
			mTts.shutdown();
		}
		super.onDestroy();
	}
}
