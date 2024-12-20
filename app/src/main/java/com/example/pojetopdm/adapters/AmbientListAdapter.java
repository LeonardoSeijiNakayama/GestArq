package com.example.pojetopdm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pojetopdm.R;
import com.example.pojetopdm.classes.Ambient;
import com.example.pojetopdm.classes.Project;
import com.example.pojetopdm.classes.UserHandler;

public class AmbientListAdapter extends RecyclerView.Adapter<AmbientListAdapter.MyViewHolder> {
    private onItemClickListener listener;
    private onItemLongClickListener longListener;

    private Project project;

    public AmbientListAdapter(Project project, onItemClickListener clickListener, onItemLongClickListener longClickListener) {
        this.project = project;
        this.listener = clickListener;
        this.longListener = longClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_items_ambient, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (project.getAmbients() != null && position < project.getAmbients().size()) {
            Ambient ambient = project.getAmbients().get(position);

            String displayArea = ambient.getArea() + " " + holder.itemView.getContext().getString(R.string.SquareMeters);

            holder.txtName.setText(ambient.getName());
            holder.txtArea.setText(displayArea);
        }
    }

    @Override
    public int getItemCount() {
        return project.getAmbients().size();
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public interface onItemLongClickListener {
        void onItemLongClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtArea;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtArea = itemView.findViewById(R.id.txtArea);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longListener != null) {
                        longListener.onItemLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
