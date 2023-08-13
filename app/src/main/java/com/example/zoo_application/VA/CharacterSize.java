package com.example.zoo_application.VA;

import android.content.res.Resources;

public class CharacterSize {
    public static int HEIGHT = 600;

    public static int getSizeType() {
        int dWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        if(dWidth > 1000) return 3;
        if(dWidth > 700) return 2;

        return 1;
    }

    public static int calculateSize(int size, int sizeType) {
        return size / 3 * sizeType;
    }

    public static int getWrapperHeight() {
        int sizetype = CharacterSize.getSizeType();

        switch (sizetype) {
            case 3:
                return 600;
            case 2:
                return 400;
            default:
                return 220;
        }
    }

    public static int dpToPixels(int dp) {
        return dp * (int)Resources.getSystem().getDisplayMetrics().density;
    }
}
