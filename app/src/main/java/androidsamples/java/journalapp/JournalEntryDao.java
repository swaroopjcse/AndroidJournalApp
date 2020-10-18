package androidsamples.java.journalapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface JournalEntryDao {
    @Insert
    void insert(JournalEntry entry);

    @Query("DELETE from journal_table")
    void deleteAll();

    @Query("SELECT * from journal_table ORDER BY title ASC")
    LiveData<List<JournalEntry>> getAllEntries();
}
