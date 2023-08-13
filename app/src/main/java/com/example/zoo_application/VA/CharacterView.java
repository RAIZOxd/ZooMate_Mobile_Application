package com.example.zoo_application.VA;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CharacterView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int BORDER_WIDTH = 0;

    private Context context;

    private CharacterThreads characterThreads;

    public void setMouthMorphs(int[] timeMorphs) {
        characterThreads.setMouthMorphs(timeMorphs);
    }

    public void endMouthMorphs() {
        characterThreads.endMouthMorphs();
    }

    public CharacterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        test(context);
    }

    public CharacterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        test(context);
    }

    private void test(Context context) {
        this.context = context;

        int sizeType = CharacterSize.getSizeType();
        int size = CharacterSize.calculateSize(600, sizeType);
        size += BORDER_WIDTH;

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSLUCENT);
        holder.setFixedSize(size, size);

        characterThreads = new CharacterThreads(holder, context, sizeType);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        characterThreads.setRunning(true);

        if(characterThreads.isStarted()) return;

        characterThreads.start();
        characterThreads.setAvatar();
        characterThreads.setStarted();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        characterThreads.setRunning(false);

        while (retry) {
            try {
                characterThreads.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }
}

