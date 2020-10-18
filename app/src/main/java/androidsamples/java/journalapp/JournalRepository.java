package androidsamples.java.journalapp;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class JournalRepository {
    private JournalEntryDao mJournalEntryDao;
    private LiveData<List<JournalEntry>> mAllEntries;

    public JournalRepository(Application application) {
        JournalRoomDatabase db = JournalRoomDatabase.getDatabase(application);
        mJournalEntryDao = db.journalEntryDao();
        mAllEntries = mJournalEntryDao.getAllEntries();
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return mAllEntries;
    }

    public void insert(JournalEntry entry) {
        new insertAsyncTask(mJournalEntryDao).execute(entry);
    }

    private static class insertAsyncTask extends AsyncTask<JournalEntry, Void, Void> {
        private final JournalEntryDao mAsyncTaskDao;

        public insertAsyncTask(JournalEntryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(JournalEntry... journalEntries) {
            mAsyncTaskDao.insert(journalEntries[0]);
            return null;
        }
    }
}
