package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorsFragment extends Fragment {
    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    // Audio manager instance to manage or
    // handle the audio interruptions
    private AudioManager mAudioManager;


    // media player is handled according to the
    // change in the focus which Android system grants for
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };



    public ColorsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_colors, container, false);

        AudioManager mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Initializing ArrayList of Word objects to store words
        final ArrayList<Word> numbersWords = new ArrayList<>();
        numbersWords.add(new Word("red", "красный", R.drawable.color_red, R.raw.color_red));
        numbersWords.add(new Word("green", "зеленый", R.drawable.color_green, R.raw.color_green));
        numbersWords.add(new Word("brown", "коричневый", R.drawable.color_brown, R.raw.color_brown));
        numbersWords.add(new Word("gray", "серый", R.drawable.color_gray, R.raw.color_gray));
        numbersWords.add(new Word("black", "черный", R.drawable.color_black, R.raw.color_black));
        numbersWords.add(new Word("white", "белый", R.drawable.color_white, R.raw.color_white));
        numbersWords.add(new Word("dusty yellow", "пыльно желтый", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        numbersWords.add(new Word("mustard yellow", "горчично желтый", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        numbersWords.add(new Word("orange", "оранжевый", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        numbersWords.add(new Word("purple", "фиолетовый", R.drawable.color_brown, R.raw.color_brown));

        //Solution for recycleViews,ListView and adapter

        WordAdapter adapter = new WordAdapter
                (getActivity(), numbersWords, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word numbersWord = numbersWords.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    MediaPlayer mMediaPlayer = MediaPlayer.create(getContext(), numbersWord.getmAudioResourceId());
                    mMediaPlayer.start();
                    //Setup a listener on the media player,so that we can stop and release the
                    //media player once the sound finished playing
                    mMediaPlayer.setOnCompletionListener(mp -> {
                        mMediaPlayer.release();

                    });
                }
            }

        });

        return rootView;

    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying())
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
    }
