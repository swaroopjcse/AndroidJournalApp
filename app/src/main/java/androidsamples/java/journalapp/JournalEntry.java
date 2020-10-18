package androidsamples.java.journalapp;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "journal_table")
public class JournalEntry {
    @PrimaryKey
    @NonNull
    private final String mUid;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "duration")
    private int mDuration;

    public JournalEntry(@NonNull String title, int duration) {
        mUid = UUID.randomUUID().toString();
        mTitle = title;
        mDuration = duration;
    }

    @NonNull
    public String getUid() {
        return mUid;
    }

    void setUid(String uid) {
    }

    public String title() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int duration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }
}
