package androidsamples.java.journalapp;

import android.util.Log;

import java.util.UUID;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class EntryDetailsViewModel extends ViewModel {
    private static final String TAG = "EntryDetailsViewModel";
    private final JournalRepository mRepository;

    private final MutableLiveData<UUID> entryIdLiveData = new MutableLiveData<>();

    public EntryDetailsViewModel() {
        mRepository = JournalRepository.getInstance();
    }

    LiveData<JournalEntry> getEntryLiveData() {
        Log.d(TAG, "getEntryLiveData called");
        return Transformations.switchMap(entryIdLiveData, mRepository::getEntry);
    }

    void loadEntry(UUID entryId) {
        Log.d(TAG, "loading entry: " + entryId);
        entryIdLiveData.setValue(entryId);
    }
}
