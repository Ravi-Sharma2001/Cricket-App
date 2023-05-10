package com.example.sportsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecentAdapter extends
        RecyclerView.Adapter<RecentAdapter.ViewHolder> {
    ArrayList<Recent> Match;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recent_matches, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recent match = Match.get(position);

        // Set item views based on your views and data model
        TextView hometeam = holder.hometeam;
        TextView awayteam = holder.awayteam;
        TextView date = holder.date;
        TextView stadium = holder.result;
        hometeam.setText(match.getHome());
        awayteam.setText(match.getAway());
        date.setText(match.getDate());
        stadium.setText(match.getResult());
    }

    @Override
    public int getItemCount() {
        return Match.size();
    }
    public RecentAdapter(ArrayList<Recent> matches) {
        Match = matches;
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView hometeam;
        public TextView awayteam;
        public TextView date;
        public TextView result;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            hometeam = (TextView) itemView.findViewById(R.id.hometeam);
            awayteam = (TextView) itemView.findViewById(R.id.awayteam);
            date = (TextView) itemView.findViewById(R.id.date);
            result = (TextView) itemView.findViewById(R.id.desc);
        }
    }
}