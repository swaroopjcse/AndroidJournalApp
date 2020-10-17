package androidsamples.java.journalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ADD_ENTRY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchAddEntryActivity(View view) {
        Intent intent = new Intent(this, AddEntry.class);
        Log.d(TAG, "Launching Add Entry");
        startActivityForResult(intent, ADD_ENTRY_REQUEST_CODE);
    }
}