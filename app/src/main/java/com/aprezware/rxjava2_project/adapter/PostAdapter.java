package com.aprezware.rxjava2_project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aprezware.rxjava2_project.R;
import com.aprezware.rxjava2_project.model.Post;

import java.util.List;

/**
 * Created by alvaro.perez on 18/10/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView txvTitle, txvContent, txvAuthor;

        public PostViewHolder(View itemView) {
            super(itemView);

            txvTitle = itemView.findViewById(R.id.txvTitle);
            txvContent = itemView.findViewById(R.id.txvContent);
            txvAuthor = itemView.findViewById(R.id.txvAuthor);
        }
    }

    Context context;
    List<Post> listPost;

    public PostAdapter(Context context, List<Post> listPost) {
        this.context = context;
        this.listPost = listPost;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.txvTitle.setText(listPost.get(position).getTitle());
        holder.txvContent.setText(listPost.get(position).getBody());
        holder.txvAuthor.setText(String.valueOf(listPost.get(position).getUserId()));
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }


}
