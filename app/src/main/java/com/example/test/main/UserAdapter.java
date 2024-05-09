package com.example.test.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.main.SQL.DBHelper;
import com.example.test.main.SQL.UserOpration;
import com.google.android.material.textfield.TextInputEditText;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Cursor cursor;
    Context context;

    public UserAdapter(Cursor cursor,Context context) {
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(context,position);

            }
        });
        holder.editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser(position,context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    private void editUser(int position,Context context) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        String firstName = cursor.getString(1);
        String lastName = cursor.getString(2);
        String email = cursor.getString(3);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_edit_user, null);
        builder.setView(dialogView);

        TextInputEditText editTextFirstName = dialogView.findViewById(R.id.edit_text_first_name);
        TextInputEditText editTextLastName = dialogView.findViewById(R.id.edit_text_last_name);
        TextInputEditText editTextEmail = dialogView.findViewById(R.id.edit_text_email);

        editTextFirstName.setText(firstName);
        editTextLastName.setText(lastName);
        editTextEmail.setText(email);

        builder.setPositiveButton("Save", (dialog, which) -> {
            // Retrieve edited data from EditTexts
            String newFirstName = editTextFirstName.getText().toString().trim();
            String newLastName = editTextLastName.getText().toString().trim();
            String newEmail = editTextEmail.getText().toString().trim();

            // Perform update operation in the database
            UserOpration op = new UserOpration(context);
            try{
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Updating...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                new Thread(() -> {
                    op.updateUser(position+1, newFirstName, newLastName, newEmail);
                    cursor = op.getAllUsers();
                    // Perform update operation in the background
                    // Update user details in the SQLite database
                    // This is where you should call your UserOperation.updateUser() method
                    // Make sure to handle any errors or exceptions that may occur during the update process

                    ((Activity) context).runOnUiThread(() -> {
                        progressDialog.dismiss();
                        notifyDataSetChanged();
                    });
                }).start();

            }catch (Error e){
                Log.i("TAG", "editUser: "+ e.toString());
            }

        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    private void removeItem(int position,Context context) {
        if (!cursor.moveToPosition(position)) {
            return;
        }
        int userId = cursor.getInt(0);
        UserOpration op = new UserOpration(context);
        op.deleteUser(userId);
        cursor = op.getAllUsers();
        notifyDataSetChanged();

    }

    private void showConfirmationDialog(Context context, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Item");
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setPositiveButton("OK", (dialog, which) -> {
            // If OK is clicked, delete the item
            removeItem(position,context);
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textIndex ,textViewFirstName, textViewLastName, textViewEmail;
        ImageView imageView,editUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            textIndex = itemView.findViewById(R.id.text_view_index);
            textViewFirstName = itemView.findViewById(R.id.text_view_first_name);
            textViewLastName = itemView.findViewById(R.id.text_view_last_name);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
            imageView = itemView.findViewById(R.id.DeleteUser);
            editUser = itemView.findViewById(R.id.EditUser);
        }
    }
}

