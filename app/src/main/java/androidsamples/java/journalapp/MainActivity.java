package androidsamples.java.journalapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        JournalEntryListAdapter adapter = new JournalEntryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void launchAddEntryActivity(View view) {
        Intent intent = new Intent(this, AddEntry.class);
        Log.d(TAG, "Launching Add Entry");
        startActivityForResult(intent, ADD_ENTRY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Log.d(TAG, "Title: " + data.getStringExtra(AddEntry.KEY_TITLE));
                Log.d(TAG, "Duration: " + data.getIntExtra(AddEntry.KEY_DURATION, 0));
            }
        }
    }
}