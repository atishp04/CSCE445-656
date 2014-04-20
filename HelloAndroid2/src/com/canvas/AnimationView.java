package com.canvas;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;








import learningandroid.helloandroid.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


public class AnimationView extends View {
    private Movie mMovie;
    private long mMovieStart;
    private static final boolean DECODE_STREAM = true;
    private static byte[] streamToBytes(InputStream is) {
      ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
      byte[] buffer = new byte[1024];
      int len;
      try {
        while ((len = is.read(buffer)) >= 0) {
          os.write(buffer, 0, len);
        }
      } catch (java.io.IOException e) {
      }
      return os.toByteArray();
    }
    
     public AnimationView(Context context,AttributeSet attrs) {
      this(context, attrs, R.drawable.trace_a);
    }
     
     public AnimationView(Context context, AttributeSet attrs, int iD){
    	 super(context,attrs);
    	 
         setFocusable(true);
         InputStream is;
         is = context.getResources().openRawResource(iD);
         if (DECODE_STREAM) {
           mMovie = Movie.decodeStream(is);
         } else {
           byte[] array = streamToBytes(is);
           mMovie = Movie.decodeByteArray(array, 0, array.length);
         }
     }
     
    @Override
    protected void onDraw(Canvas canvas) {	
     long now = android.os.SystemClock.uptimeMillis();
     
      if (mMovieStart == 0) { // first time
        mMovieStart = now;
      }
      if (mMovie != null) {
        int dur = mMovie.duration();
        if (dur == 0) {
          dur = 3000;
        }
        int relTime = (int) ((now - mMovieStart) % dur);
       Log.d("", "real time :: " +relTime);
        mMovie.setTime(relTime);
        mMovie.draw(canvas, getWidth() - 200, getHeight()-200);
        invalidate();
      }
    }
  }