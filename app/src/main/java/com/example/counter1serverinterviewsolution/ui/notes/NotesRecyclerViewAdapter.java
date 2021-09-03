package com.example.counter1serverinterviewsolution.ui.notes;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.counter1serverinterviewsolution.R;
import com.example.counter1serverinterviewsolution.data.model.Note;
import com.example.counter1serverinterviewsolution.databinding.NotesListItemBinding;

import java.util.ArrayList;
import java.util.List;


public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Note> mValues;

    public NotesRecyclerViewAdapter(ArrayList<Note> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(NotesListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Note note = mValues.get(position);

        TextView titleTextView = holder.titleTextView;
        titleTextView.setText(note.getTitle());

        TextView bodyTextView = holder.bodyTextView;
        bodyTextView.setText(note.getBody());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView bodyTextView;

        public Note mItem;

        public ViewHolder(NotesListItemBinding binding) {
            super(binding.getRoot());
            titleTextView = (TextView) itemView.findViewById(R.id.text_view_note_title);
            bodyTextView = (TextView) itemView.findViewById(R.id.text_view_note_body);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}