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

import android.content.Context;;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class PhrasesActivity extends AppCompatActivity {

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
                if(mMediaPlayer != null){
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Initializing ArrayList of Word objects to store words
        final ArrayList<Word> numbersWords = new ArrayList<>();
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


        //Solution for recycleViews,ListView and adapter

        WordAdapter adapter = new WordAdapter
                (this, numbersWords, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

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

                    MediaPlayer mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, numbersWord.getmAudioResourceId());
                    mMediaPlayer.start();
                    //Setup a listener on the media player,so that we can stop and release the
                    //media player once the sound finished playing
                    mMediaPlayer.setOnCompletionListener(mp -> {
                        mMediaPlayer.release();

                    });
                }
            }

            ;

        });
    }

    @Override
    protected void onStop() {
        if(mMediaPlayer != null) mMediaPlayer.stop();
        super.onStop();
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

            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

}

