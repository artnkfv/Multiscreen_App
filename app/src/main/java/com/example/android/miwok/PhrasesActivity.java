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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        //Initializing ArrayList of Word objects to store words
        ArrayList<Word> numbersWords = new ArrayList<Word>();
        numbersWords.add(new Word("Where are you going?", "Куда ты идёшь?", R.drawable.phrases_1, R.raw.phrase_where_are_you_going));
        numbersWords.add(new Word("What is your name", "Как тебя зовут?", R.drawable.phrases_2, R.raw.phrase_what_is_your_name));
        numbersWords.add(new Word("My name is...", "Меня зовут...", R.drawable.phrases_3, R.raw.phrase_my_name_is));
        numbersWords.add(new Word("How are you feeling?", "Как ты себя чуствуешь?", R.drawable.phrases_4, R.raw.phrase_how_are_you_feeling));
        numbersWords.add(new Word("I'm feeling good.", "Чуствую себя хорошо.", R.drawable.phrases_5, R.raw.phrase_im_feeling_good));
        numbersWords.add(new Word("Are you coming?", "Ты идешь?", R.drawable.phrases_6, R.raw.phrase_are_you_coming));
        numbersWords.add(new Word("Yes,I'm coming.", "Да я иду.", R.drawable.phrases_7, R.raw.phrase_yes_im_coming));
        numbersWords.add(new Word("I'm coming.", "Я иду.", R.drawable.phrases_8, R.raw.phrase_im_coming));
        numbersWords.add(new Word("Let's go.", "Идем.", R.drawable.phrases_9, R.raw.phrase_lets_go));
        numbersWords.add(new Word("Come here.", "Подойди.", R.drawable.phrases_10, R.raw.phrase_come_here));


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
                (this, numbersWords, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long l) {
                releaseMediaPlayer();
                Word numbersWord = numbersWords.get(index);
                MediaPlayer mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, numbersWord.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mp -> {
                    mMediaPlayer.release();
                });
            }

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

