package com.example.loginregisterapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<Post> postsList = new ArrayList<>();
    private OnClickItemListener onClickItemListener;


    public Adapter(Context mContext, List<Post> postsList, OnClickItemListener onClickItemListener) {
        this.mContext = mContext;
        this.postsList = postsList;
        this.onClickItemListener = onClickItemListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.list_row_main, parent, false);
        return new MyViewHolder(v, onClickItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(postsList.get(position).getName());
        holder.date.setText(postsList.get(position).getDate());

        Glide.with(mContext)
                .load(postsList.get(position).getImg())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView name;
        TextView date;
        ImageView img;

        OnClickItemListener onClickItemListener;

        public MyViewHolder(@NonNull View itemView, OnClickItemListener onClickItemListener) {
            super(itemView);

            this.onClickItemListener = onClickItemListener;

            name = itemView.findViewById(R.id.text_view_name);
            date = itemView.findViewById(R.id.text_view_date);
            img = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickItemListener.onClickItem(getAdapterPosition());
        }
    }

    public interface OnClickItemListener {
        void onClickItem(int position);
    }
}
