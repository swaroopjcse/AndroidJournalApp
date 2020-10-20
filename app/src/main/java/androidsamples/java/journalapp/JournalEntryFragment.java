package androidsamples.java.journalapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class JournalEntryFragment extends Fragment {
    private static final String TAG = "JournalEntryFragment";
    private EditText mEditTitle, mEditDuration;
    private Button mBtnSave;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // view is not inflated here
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.journal_entry_detail, container, false);
        mEditTitle = view.findViewById(R.id.edit_title);
        mEditDuration = view.findViewById(R.id.edit_duration);
        mBtnSave = view.findViewById(R.id.btn_save);

        mBtnSave.setOnClickListener((v) -> {
            Log.d(TAG, "Save button clicked");
        });

        return view;
    }
}
