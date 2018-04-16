package com.example.ga.movieapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ga on 4/15/2018.
 */

public class VideosAdapter extends ArrayAdapter<Videos> {
private Context context;

public VideosAdapter(Context context, List<Videos> videos) {
        super(context, 0, videos);
        this.context = context;
        }
public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(R.layout.video_list_item, parent, false);
        }
        Videos currentVideo = getItem(position);
        TextView key = (TextView)listItemView.findViewById(R.id.KeyText);
        key.setText(currentVideo.getKey());

        return listItemView;
        }
        }