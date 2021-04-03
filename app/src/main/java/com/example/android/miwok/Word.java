package com.example.android.miwok;

/**
 * this class represent a vocabulary word that the user wants to learn.
 *It contain a default translation and a Miwok translation for that word.
 */
public class Word {

    /**
     * Default translation for the word
     */
    private String mDefaultTranslation;

    /**
     * Miwok translation for the word
     */
    private String mMiwokTranslation;

    /**
     * Image resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final  int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;

   /* public Word(String mDefaultTranslation, String mMiwokTranslation, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = mAudioResourceId;

    }*/
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceId, int mAudioResourceId){
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = mImageResourceId;
        this.mAudioResourceId = mAudioResourceId;
    }

    /**
     * Get the default translation of the word
     */
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word
     */
    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }
    /** Get image for the word */
    public int getmImageResourceId() {
        return mImageResourceId;
    }
    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceId() { return  mAudioResourceId; }

}