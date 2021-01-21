/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * this listener gets triggered when the MediaPlayer has completed
     * playing th audio file
     * <p>
     * private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
     *
     * @Override public void onCompletion(MediaPlayer mediaPlayer) {
     * releaseMediaPlayer();
     * }
     * };
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Initializing ArrayList of Word objects to store words
        ArrayList<Word> numbersWords = new ArrayList<>();
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


        //Defining textViews for xml layout

        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        //Create a variable to keep track of the current index position
        int index = 0;

        //while loop solution for creating textVies in xml from arrayList of stings

        /*while (index < numbersWords.size()) {
            //Create a new textView
            TextView wordView = new TextView(this);

            //set the text to be word at the current index
            wordView.setText(numbersWords.get(index));

            //add this textView as another child to the root view of this layout
            rootView.addView(wordView);

            //increment the index variable by 1
            index++;

        }*/

        //for loop solution for creating textVies in xml from arrayList of stings

        /*for (index = 0; index < numbersWords.size(); index++ ) {
            TextView wordView = new TextView(this);
            wordView.setText(numbersWords.get(index));
            assert rootView != null;
            rootView.addView(wordView);
        }*/


        //Solution for recycleViews,ListView and adapter

        WordAdapter adapter = new WordAdapter
                (this, numbersWords, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, index1, l) -> {
            releaseMediaPlayer();
            Word numbersWord = numbersWords.get(index1);
            MediaPlayer mMediaPlayer = MediaPlayer.create(NumbersActivity.this, numbersWord.getmAudioResourceId());
            mMediaPlayer.start();
            //Setup a listener on the media player,so that we can stop and release the
            //media player once the sound finished playing
            mMediaPlayer.setOnCompletionListener(mp -> {
                mMediaPlayer.release();

            });
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}






