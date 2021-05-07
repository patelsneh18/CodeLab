package com.google.codelab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.codelab.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;
    SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    private int mCount = 0;
    private TextView mShowCount;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        mShowCount = b.showCount;
        mColor = ContextCompat.getColor(this,
                R.color.gray);
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        if (mPreferences!=null) {
            // Add stored colors and count in shared preferences
            mColor = mPreferences.getInt(Constants.COLOR_KEY, mColor);
            mCount = mPreferences.getInt(Constants.COUNT_KEY, 0);
            mShowCount.setText(String.valueOf(mCount));
            mShowCount.setBackgroundColor(mColor);

        }
        setContentView(b.getRoot());
    }



    /**
     * Increases the count shown in text fied by 1
     * @param view root of ui
     */
    public void countUp(View view) {
        ++mCount;
        TextView mShowCount = b.showCount ;
        if (mShowCount != null)
            mShowCount.setText("" + mCount);
    }

    /**
     * To reset the count value to 0 and display in the text view
     */
    public void reset(View view) {
        // To reset the count
        mCount = 0;
        b.showCount.setText(String.valueOf(mCount));

        // To reset the color
        mColor = getResources().getColor(R.color.gray);
        b.showCount.setBackgroundColor(mColor);

        // To clear the data stored in the shared preferences
        mPreferences.edit().clear().apply();
    }

    /**
     * To update the background color of the background of text view
     */
    public void updateBackgroundColor(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        b.showCount.setBackgroundColor(color);
        mColor = color;
    }

    /**
     * Overriding onPause to put count and color values in shared preferences
     */
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putInt(Constants.COUNT_KEY, mCount);
        preferenceEditor.putInt(Constants.COLOR_KEY, mColor);
        preferenceEditor.apply();
    }
}