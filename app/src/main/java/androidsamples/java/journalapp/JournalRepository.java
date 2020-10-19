package androidsamples.java.journalapp;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class JournalRepository {
    private static final String DATABASE_NAME = "journal_table";
    private final JournalEntryDao mJournalEntryDao;

    private static JournalRepository sInstance;

    public JournalRepository(Context context) {
        JournalRoomDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                JournalRoomDatabase.class,
                DATABASE_NAME).build();
        mJournalEntryDao = db.journalEntryDao();
    }

    public static void init(Context context) {
        if (sInstance == null)
            sInstance = new JournalRepository(context);
    }

    public static JournalRepository getInstance() {
        if (sInstance == null)
            throw new IllegalStateException("JournalRepository must be initialized");
        return sInstance;
    }

    public void insert(JournalEntry entry) {
        new insertAsyncTask(mJournalEntryDao).execute(entry);
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return mJournalEntryDao.getAllEntries();
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
