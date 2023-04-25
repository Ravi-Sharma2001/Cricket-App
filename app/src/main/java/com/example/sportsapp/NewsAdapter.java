package com.example.sportsapp;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class NewsAdapter  extends
        RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    NewsFeed n=new NewsFeed();
    ArrayList<newsModel> news;
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.news_display, parent, false);

        // Return a new holder instance
        NewsAdapter.ViewHolder viewHolder = new NewsAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        newsModel feed = news.get(position);
        Log.e("data",feed.getAuthor());
        // Set item views based on your views and data model
        TextView title = holder.title;
        TextView heading = holder.heading;

        title.setText(feed.getAuthor());
        heading.setText(feed.getTitle());
    }
    public NewsAdapter(ArrayList<newsModel> feed) {
        news = feed;
    }
    @Override
    public int getItemCount() {
        return news.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView title;
        public TextView heading;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.newsTitle);
            heading = (TextView) itemView.findViewById(R.id.newsHeading);

        }
    }
}
