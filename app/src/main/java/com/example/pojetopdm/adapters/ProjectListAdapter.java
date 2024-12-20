package com.example.pojetopdm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pojetopdm.R;
import com.example.pojetopdm.classes.Project;
import com.example.pojetopdm.classes.UserHandler;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.MyViewHolder>{
    private onItemClickListener listener;
    private onItemLongClickListener longListener;

    public ProjectListAdapter(onItemClickListener clickListener, onItemLongClickListener longClickListener) {
        this.listener = clickListener;
        this.longListener = longClickListener;
    }

    @NonNull
    @Override
    public ProjectListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_items_project, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectListAdapter.MyViewHolder holder, int position) {
        if(UserHandler.getUser().getProjects()!=null && position < UserHandler.getUser().getProjects().size()){
            Project project = UserHandler.getUser().getProjects().get(position);

            holder.txtName.setText(project.getName());
            holder.txtClient.setText(project.getClient().getName());
            holder.txtDate.setText(project.getStartDate());
        }
    }

    @Override
    public int getItemCount() {
        return UserHandler.getUser().getProjects().size();
    }

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public interface onItemLongClickListener{
        void onItemLongClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtClient, txtDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtClient = itemView.findViewById(R.id.txtClient);
            txtDate = itemView.findViewById(R.id.txtDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(longListener!=null){
                        longListener.onItemLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }


}
