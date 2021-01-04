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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        //Initializing ArrayList of Word objects to store words
        ArrayList<Word> numbersWords = new ArrayList<Word>();
        numbersWords.add(new Word("red","красный"));
        numbersWords.add(new Word("green","зеленый"));
        numbersWords.add(new Word("brown","коричневый"));
        numbersWords.add(new Word("gray","серый"));
        numbersWords.add(new Word("black","черный"));
        numbersWords.add(new Word("white","белый"));
        numbersWords.add(new Word("dusty yellow","пыльно желтый"));
        numbersWords.add(new Word("mustard yellow","горчично желтый"));
        numbersWords.add(new Word("orange","оранжевый"));
        numbersWords.add(new Word("purple","фиолетовый"));


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
                (this, numbersWords);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

    }


}
