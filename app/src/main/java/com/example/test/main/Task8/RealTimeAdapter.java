package com.example.test.main.Task8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.main.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RealTimeAdapter extends RecyclerView.Adapter<RealTimeAdapter.RealTimeViewHolder> {


    List<UserModel> Lists = new ArrayList<>();;
    Context context;


    public RealTimeAdapter(java.util.List<UserModel> list, Context context) {
        Lists = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RealTimeAdapter.RealTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_layout, parent, false);
        return new RealTimeAdapter.RealTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealTimeAdapter.RealTimeViewHolder holder, int position) {

        holder.textIndex.setText(String.valueOf(position+1));
        holder.textViewFirstName.setText(Lists.get(position).getName());
        holder.textViewEmail.setText(Lists.get(position).getEmailId());
    }

    @Override
    public int getItemCount() {
        return Lists.size();
    }

    public class RealTimeViewHolder  extends RecyclerView.ViewHolder{

        public TextView textIndex ,textViewFirstName, textViewEmail;
        ImageView delete,editUser;
        public RealTimeViewHolder(@NonNull View itemView) {
            super(itemView);

            textIndex = itemView.findViewById(R.id.t8Index);
            textViewFirstName = itemView.findViewById(R.id.t8UserName);
            textViewEmail = itemView.findViewById(R.id.t8UserEmailId);
            delete = itemView.findViewById(R.id.t8DeleteUser);
            editUser = itemView.findViewById(R.id.t8EditUser);
        }
    }
}
