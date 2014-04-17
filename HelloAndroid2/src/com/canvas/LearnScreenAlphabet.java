package com.canvas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.AttributedCharacterIterator.Attribute;

import learningandroid.helloandroid.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.inputmethodservice.InputMethodService;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;




public class LearnScreenAlphabet extends Activity {
	private ImageView next;
	private ImageView back;
	private ImageView center;
	private ImageView left;
	private ImageView right;
	private LinearLayout main;
	private LinearLayout handWLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_screen_alphabet);
		center = (ImageView) findViewById(R.id.learn_center);
		left = (ImageView) findViewById(R.id.learn_left);
		right = (ImageView) findViewById(R.id.learn_right);
		
		next = (ImageView) findViewById(R.id.learn_button_next);
		back = (ImageView) findViewById(R.id.learn_button_back);

		handWLayout = (LinearLayout) findViewById(R.id.learn_boxToWrite);
		//handWLayout.setBackgroundColor(Color.BLUE);
		//handWLayout.setBackgroundResource(R.drawable.dino_left);
		
		center.setImageResource(R.drawable.thinking_b);
		left.setImageResource(R.drawable.dino_left);
		right.setImageResource(R.drawable.handwrite_b);
		//next.setOnClickListener(this);
		//back.setOnClickListener(this);
		Intent i = getIntent();
		 
        // Selected image id
        int pos = i.getExtras().getInt("id");
        switch(pos){
        case 0 :
        	center.setImageResource(R.drawable.thinking_b);
    		left.setImageResource(R.drawable.dino_left);
    		right.setImageResource(R.drawable.handwrite_b);
    		break;
        case 1 :
        	center.setImageResource(R.drawable.thinking_b);
    		left.setImageResource(R.drawable.thinking_b);
    		right.setImageResource(R.drawable.thinking_b);
    		break;
        case 2 :
        	center.setImageResource(R.drawable.thinking_b);
    		left.setImageResource(R.drawable.dino_left);
    		right.setImageResource(R.drawable.handwrite_b);
    		break;
        case 3 :
        	center.setImageResource(R.drawable.thinking_b);
    		left.setImageResource(R.drawable.dino_left);
    		right.setImageResource(R.drawable.handwrite_b);
    		break;
        case 4 :
        	center.setImageResource(R.drawable.thinking_b);
    		left.setImageResource(R.drawable.dino_left);
    		right.setImageResource(R.drawable.handwrite_b);
    		break;
        	
        }
        
		
		
		
		

	}
	
	/*public void onClick(View v) {
		
		if(v==back){
			// Go to the previous screen
			Log.v("Back button pressed");
		}
		if(v==next){
			// Go to the next screen
			Log.v("Next button pressed");
		}
	}*/
}
	
	
	
	
	
	
	
