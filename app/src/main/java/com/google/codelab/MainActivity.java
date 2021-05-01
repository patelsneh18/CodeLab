package com.google.codelab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Shows toast saying Hello Toast!
     * @param view root of ui
     */
    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Increases the count shown in text fied by 1
     * @param view root of ui
     */
    public void countUp(View view) {
        ++mCount;
        TextView mShowCount = (TextView) findViewById(R.id.show_count);
        if (mShowCount != null)
            mShowCount.setText("" + mCount);
    }
}