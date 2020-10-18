package androidsamples.java.journalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddEntry extends AppCompatActivity {
    private static final String TAG = "AddEntry";
    static final String KEY_TITLE = "KEY_TITLE";
    static final String KEY_DURATION = "KEY_DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
    }

    public void saveEntry(View view) {
        EditText editTitle = findViewById(R.id.edit_title);
        EditText editDuration = findViewById(R.id.edit_duration);
        Log.d(TAG, "title: " + editTitle.getText().toString() + ", duration: " + editDuration.getText().toString());
        Intent resultData = new Intent();
        resultData.putExtra(KEY_TITLE, editTitle.getText().toString());
        resultData.putExtra(KEY_DURATION, Integer.parseInt(editDuration.getText().toString()));
        setResult(RESULT_OK, resultData);
        finish();
    }

}