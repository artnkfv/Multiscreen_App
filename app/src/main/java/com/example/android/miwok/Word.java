package com.example.android.miwok;

/**
 * this class represent a vocabulary word that the user wants to learn.
 *It contain a defoult translation and a Miwok translation for that word.
 */
public class Word {

    /** Default translation for the word */
    private  String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    public Word(String mDefaultTranslation, String mMiwokTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
    }

    /** Get the default translation of the word */
    public String getmDefaultTranslation(){
        return mDefaultTranslation;
    }

    /** Get the Miwok translation of the word */
    public String getmMiwokTranslation(){
        return mMiwokTranslation;
    }
}
