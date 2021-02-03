package com.example.android.miwok;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    /**
     * Resource ID for the background color for the list of words
     */
    private int mColorResourceId;
    private MediaPlayer mediaPlayer;

    public WordAdapter(Activity context, ArrayList<Word> numbersWords, int ColorResourceId) {
        super(context, 0, numbersWords);
        mColorResourceId = ColorResourceId;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView originalTextView = (TextView) listItemView.findViewById(R.id.textView);

        originalTextView.setText(currentWord.getmDefaultTranslation());

        Drawable drawable1=getContext().getDrawable(R.drawable.baseline_play_circle_outline_black_24dp);
        drawable1.setBounds(0,0,55,55);
        originalTextView.setCompoundDrawables(null,null,drawable1,null);


        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.textView2);

        miwokTextView.setText(currentWord.getmMiwokTranslation());

  /*      ImageView playButtImageView = (ImageView) listItemView.findViewById(R.id.play_butt);

        playButtImageView.setImageResource(R.drawable.baseline_play_circle_outline_black_24dp);*/

        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.image1);
        // Check if an image is provided
        if (currentWord.hasImage()) {
            iconImageView.setImageResource(currentWord.getmImageResourceId());
            iconImageView.setVisibility(View.VISIBLE);
        } else {
            iconImageView.setVisibility(View.GONE);
        }

        /** Set background color for list item */
        View textContainer = listItemView.findViewById(R.id.text_container);
        /** Find the color that the Resource ID maps to */
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        /** Set background color for the text container View */
        textContainer.setBackgroundColor(color);

        /*//Adding the audio files to each list item and making them play onClick
        LinearLayout clickItem = (LinearLayout) listItemView.findViewById(R.id.wholeView);


        clickItem.setOnClickListener(new View.OnClickListener() {

            MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), currentWord.getmAudioResourceId());

            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());

            }
        });*/


        return listItemView;

    }
    /**
     * Clean up the media player by releasing its resources.
     *//*
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.stop();
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }*/



}

