package androidsamples.java.journalapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EntryListFragment extends Fragment {
    private static final String TAG = "EntryListFragment";
    private EntryListViewModel mEntryListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEntryListViewModel = new ViewModelProvider(this).get(EntryListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry_list, container, false);
        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(this::launchAddEntryActivity);

        RecyclerView entriesList = view.findViewById(R.id.recyclerView);
        entriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        EntryListAdapter adapter = new EntryListAdapter(getActivity());
        entriesList.setAdapter(adapter);

        mEntryListViewModel.getAllEntries().observe(getActivity(), adapter::setEntries);

        return view;
    }

    public void launchAddEntryActivity(View view) {
        Log.d(TAG, "launchAddEntryActivity");
    }

    private static class EntryViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTxtTitle;
        private final TextView mTxtDuration;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtTitle = itemView.findViewById(R.id.txt_item_title);
            mTxtDuration = itemView.findViewById(R.id.txt_item_duration);
        }
    }

    private static class EntryListAdapter extends RecyclerView.Adapter<EntryViewHolder> {
        private final LayoutInflater mInflater;
        private List<JournalEntry> mEntries;

        public EntryListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.journal_item, parent, false);
            return new EntryViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
            if (mEntries != null) {
                JournalEntry current = mEntries.get(position);
                holder.mTxtTitle.setText(current.title());
                holder.mTxtDuration.setText(Helper.toLocalizedString(current.duration()));
            }
        }

        @Override
        public int getItemCount() {
            return (mEntries == null) ? 0 : mEntries.size();
        }

        public void setEntries(List<JournalEntry> entries) {
            mEntries = entries;
            notifyDataSetChanged();
        }
    }
}
