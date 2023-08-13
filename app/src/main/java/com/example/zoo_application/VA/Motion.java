package com.example.zoo_application.VA;

public class Motion {
    protected long[] mNextTimeout;
    protected int[] mMorphs;
    protected int mLength;

    protected long mChangeTime;

    protected int mCurrentIndex;
    protected int mCurrentMorph;

    protected long milliToNano(int milli) {
        return (long)milli * 1000000;
    }
}
