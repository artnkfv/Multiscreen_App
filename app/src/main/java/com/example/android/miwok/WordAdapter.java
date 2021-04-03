package com.example.android.miwok;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    /**
     * Resource ID for the background color for the list of words
     */
    private int mColorResourceId;
    private ArrayList<Word> numbersWords;
    private ArrayList<Word> searchList;
    private SearchFilter filter;
    private MediaPlayer mediaPlayer;

    public WordAdapter(Context context, ArrayList<Word> numbersWords, int ColorResourceId) {
        super(context, 0, numbersWords);
        mColorResourceId = ColorResourceId;
        this.numbersWords = numbersWords;
        this.searchList = numbersWords;
    }

    @Override
    public int getCount() {
        return numbersWords.size();
    }

    @Nullable
    @Override
    public Word getItem(int position) {
        return numbersWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return numbersWords.indexOf(getItem(position));
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new SearchFilter();
        }
        return filter;
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

        Drawable drawable1= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            drawable1 = getContext().getDrawable(R.drawable.baseline_play_circle_outline_black_24dp);
        }
        drawable1.setBounds(0,0,55,55);
        originalTextView.setCompoundDrawables(null,null,drawable1,null);


        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.textView2);

        miwokTextView.setText(currentWord.getmMiwokTranslation());


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


        return listItemView;

    }
    private class SearchFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence s) {

            FilterResults result = new FilterResults();
            if(s != null && s.toString().length() > 0)
            {
                s = s.toString().toLowerCase();
                ArrayList<Word> filteredItems = new ArrayList<Word>();

                for(int i = 0, l = searchList.size(); i < l; i++)
                {
                    if(searchList.get(i).getmDefaultTranslation().toLowerCase().contains(s))
                    {
                        Word x = new Word(searchList.get(i).getmDefaultTranslation(),searchList.get(i).getmMiwokTranslation(),
                                searchList.get(i).getmImageResourceId(),searchList.get(i).getmAudioResourceId());
                        filteredItems.add(x);
                        /**
                         * Perform filtering of array base on search query string,
                         * find index of object in array that contain search query,
                         * add this object in new array,and Override getView in
                         * publishResults with this new array
                         */
                    }
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }else {
                result.count = searchList.size();
                result.values = searchList;
            }

            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence s,
                                      FilterResults results) {

            numbersWords = (ArrayList<Word>)results.values;
            Log.i("new search", String.valueOf(results));
            notifyDataSetChanged();
            /*clear();
            for(int i = 0, l = numbersWords.size(); i < l; i++)
                add(numbersWords.get(i));
            notifyDataSetInvalidated();*/
        }
    }






}

