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
            R.drawable.letter_e, R.drawable.letter_f,
            R.drawable.letter_g, R.drawable.letter_h,
            R.drawable.letter_i, R.drawable.letter_j,
            R.drawable.letter_k, R.drawable.letter_l,
            R.drawable.letter_m, R.drawable.letter_n,
            R.drawable.letter_o, R.drawable.letter_p,
            R.drawable.letter_q, R.drawable.letter_r,
            R.drawable.letter_s, R.drawable.letter_t,
            R.drawable.letter_u, R.drawable.letter_v,
            R.drawable.letter_w, R.drawable.letter_x,
            R.drawable.letter_y, R.drawable.letter_z
    };
    public Integer[] mThumbIds2 = {
    		R.drawable.letter_a, R.drawable.letter_b,
            R.drawable.letter_c, R.drawable.letter_d,
            R.drawable.letter_e, R.drawable.letter_f,
            R.drawable.letter_g, R.drawable.letter_h,
            R.drawable.letter_i, R.drawable.letter_j,
            R.drawable.letter_k, R.drawable.letter_l,
            R.drawable.letter_m, R.drawable.letter_n,
            R.drawable.letter_o, R.drawable.letter_p,
            R.drawable.letter_q, R.drawable.letter_r,
            R.drawable.letter_s, R.drawable.letter_t,
            R.drawable.letter_u, R.drawable.letter_v,
            R.drawable.letter_w, R.drawable.letter_x,
            R.drawable.letter_y, R.drawable.letter_z
    };
}