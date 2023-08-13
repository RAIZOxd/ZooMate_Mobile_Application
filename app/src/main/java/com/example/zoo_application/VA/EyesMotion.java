package com.example.zoo_application.VA;

public class EyesMotion extends Motion {
    public EyesMotion() {
        mCurrentIndex = 0;
        mChangeTime = 0;
        mCurrentMorph = 0;

        mLength = 21;

        mNextTimeout = new long[mLength];
        mMorphs = new int[mLength];

        openEyes(0, 0);

        BlinkEyes(1, 2100);

        BlinkEyes(5, 2500);

        BlinkEyes(9, 2900);
        BlinkEyes(13, 70);

        BlinkEyes(17, 2200);
    }

    public int getMotion(long currentTime) {
        if(currentTime - mChangeTime >= mNextTimeout[mCurrentIndex]) {
            mCurrentMorph = mMorphs[mCurrentIndex];
            mChangeTime = currentTime;

            mCurrentIndex++;
            if(mCurrentIndex == mLength) {
                mCurrentIndex = 0;
            }
        }

        return mCurrentMorph;
    }

    private void openEyes(int startIndex, int startAfter) {
        mNextTimeout[startIndex] = milliToNano(startAfter);
        mMorphs[startIndex] = 0;
    }

    private void BlinkEyes(int startIndex, int startAfter) {
        mNextTimeout[startIndex] = milliToNano(startAfter);
        mMorphs[startIndex++] = 1;
        mNextTimeout[startIndex] = milliToNano(70);
        mMorphs[startIndex++] = 2;
        mNextTimeout[startIndex] = milliToNano(70);
        mMorphs[startIndex++] = 1;
        mNextTimeout[startIndex] = milliToNano(70);
        mMorphs[startIndex++] = 0;
    }
}

