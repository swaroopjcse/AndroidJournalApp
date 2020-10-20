package androidsamples.java.journalapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ADD_ENTRY_REQUEST_CODE = 2;

    private JournalViewModel mJournalViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        JournalEntryListAdapter adapter = new JournalEntryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mJournalViewModel = new ViewModelProvider(this).get(JournalViewModel.class);
        mJournalViewModel.getAllEntries().observe(this, (List<JournalEntry> entries) -> adapter.setEntries(entries));
    }

    public void launchAddEntryActivity(View view) {
        Intent intent = new Intent(this, AddEntryActivity.class);
        Log.d(TAG, "Launching Add Entry");
        startActivityForResult(intent, ADD_ENTRY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra(AddEntryActivity.KEY_TITLE);
                Log.d(TAG, "Title: " + title);
                int duration = data.getIntExtra(AddEntryActivity.KEY_DURATION, 0);
                Log.d(TAG, "Duration: " + duration);
                mJournalViewModel.insert(new JournalEntry(title, duration));
            }
        }
    }
}