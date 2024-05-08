package com.example.test.main;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Cursor cursor;

    public UserAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        String index = cursor.getString(0);

        String firstName = cursor.getString(1);
        String lastName = cursor.getString(2);
        String email = cursor.getString(3);

        holder.textIndex.setText(index);
        holder.textViewFirstName.setText(firstName);
        holder.textViewLastName.setText(lastName);
        holder.textViewEmail.setText(email);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textIndex ,textViewFirstName, textViewLastName, textViewEmail;

        public UserViewHolder(View itemView) {
            super(itemView);
            textIndex = itemView.findViewById(R.id.text_view_index);
            textViewFirstName = itemView.findViewById(R.id.text_view_first_name);
            textViewLastName = itemView.findViewById(R.id.text_view_last_name);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
        }
    }
}

