package learningandroid.helloandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;



public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
    		R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d
    };
    public Integer[] mThumbIds2 = {
    		R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d
    };
}