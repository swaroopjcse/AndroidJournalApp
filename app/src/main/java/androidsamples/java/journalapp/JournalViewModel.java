package androidsamples.java.journalapp;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class JournalViewModel extends AndroidViewModel {
    private final JournalRepository mRepository;
    private final LiveData<List<JournalEntry>> mAllEntries;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        mRepository = new JournalRepository(application);
        mAllEntries = mRepository.getAllEntries();
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return mAllEntries;
    }

    public void insert(JournalEntry entry) {
        mRepository.insert(entry);
    }
}
