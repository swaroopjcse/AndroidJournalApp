package androidsamples.java.journalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddEntryActivity extends AppCompatActivity {
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
        String title = editTitle.getText().toString();
        String duration = editDuration.getText().toString();
        Log.d(TAG, "title: " + title + ", duration: " + duration);
        if (title.isEmpty() || duration.isEmpty()) {
            setResult(RESULT_CANCELED);
        } else {
            Intent resultData = new Intent();
            resultData.putExtra(KEY_TITLE, title);
            resultData.putExtra(KEY_DURATION, Integer.parseInt(duration));
            setResult(RESULT_OK, resultData);
        }
        finish();
    }

}