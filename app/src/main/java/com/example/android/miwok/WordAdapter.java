package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Activity context, ArrayList<Word> numbersWords) {
        super(context, 0,numbersWords);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView originalTextView = (TextView) listItemView.findViewById(R.id.textView);

        originalTextView.setText(currentWord.getmDefaultTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.textView2);

        miwokTextView.setText(currentWord.getmMiwokTranslation());

        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.image1);
        iconImageView.setImageResource(currentWord.getmImageResourceId());

        return listItemView;
    }
}
