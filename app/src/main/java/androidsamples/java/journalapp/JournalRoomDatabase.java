package androidsamples.java.journalapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {JournalEntry.class}, version = 1, exportSchema = false)
public abstract class JournalRoomDatabase extends RoomDatabase {
    public abstract JournalEntryDao journalEntryDao();

    private static JournalRoomDatabase sInstance;

    public static JournalRoomDatabase getDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (JournalRoomDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), JournalRoomDatabase.class, "journal_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sInstance;
    }
}
