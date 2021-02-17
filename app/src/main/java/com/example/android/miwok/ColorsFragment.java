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
        final ArrayList<Word> numbersWords = new ArrayList<Word>();
        numbersWords.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numbersWords.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbersWords.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbersWords.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbersWords.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbersWords.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbersWords.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbersWords.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbersWords.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbersWords.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

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
