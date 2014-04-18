package com.canvas;

import learningandroid.helloandroid.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;




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
	
	
	
	
	
	
	
