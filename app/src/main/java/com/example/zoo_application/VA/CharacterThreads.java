package com.example.zoo_application.VA;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.view.SurfaceHolder;

public class CharacterThreads extends Thread {

    private boolean mRun = false;
    private boolean mStarted = false;

    private SurfaceHolder SurfaceHolder;
    private Context Context;
    private int SizeType;

    private Character Character;

    private CharacterParts EyesPart;
    private int CurrentEyesMorph;

    private CharacterParts MouthPart;
    private int CurrentMouthMorph;

    private Bitmap[] Eyes;
    private Bitmap[] Mouths;
    private Paint BodyPaint;
    private Paint BorderPaint;
    private int Size;

    private int EyesX, EyesY, EyesWidth, EyesHeight;
    private int MouthX, MouthY, MouthWidth, MouthHeight;


    public CharacterThreads(SurfaceHolder surfaceHolder, Context context, int sizeType) {
        SurfaceHolder = surfaceHolder;
        Context = context;
        SizeType = sizeType;
    }
    public void setRunning(boolean run) {
        mRun = run;
    }

    public void setStarted() {
        mStarted = true;
    }

    public boolean isStarted() {
        return mStarted;
    }

    public void setMouthMorphs(int[] timeMorphs) {
        Character.setMouthMorphs(timeMorphs);
    }

    public void endMouthMorphs() {
        Character.endMouthMorphs();
    }

    public void setAvatar() {
        Character = CharacterLips.getEmbedded();
        loadAvatarBitmaps();
    }

    private void loadAvatarBitmaps() {
        EyesPart = Character.getEyes();
        MouthPart = Character.getMouth();

        EyesX = CharacterSize.calculateSize(EyesPart.getX(), SizeType);
        EyesY = CharacterSize.calculateSize(EyesPart.getY(), SizeType);
        EyesWidth = CharacterSize.calculateSize(EyesPart.getWidth(), SizeType);
        EyesHeight = CharacterSize.calculateSize(EyesPart.getHeight(), SizeType);

        MouthX = CharacterSize.calculateSize(MouthPart.getX(), SizeType);
        MouthY = CharacterSize.calculateSize(MouthPart.getY(), SizeType);
        MouthWidth = CharacterSize.calculateSize(MouthPart.getWidth(), SizeType);
        MouthHeight = CharacterSize.calculateSize(MouthPart.getHeight(), SizeType);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Size = CharacterSize.calculateSize(CharacterSize.HEIGHT, SizeType);

        Bitmap body = BitmapFactory.decodeResource(Context.getResources(), Character.getBodyDrawableId(), options);
        body = Bitmap.createScaledBitmap(body, Size, Size, false);
        BitmapShader bodyShader = new BitmapShader(body, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        BodyPaint = new Paint();
        BodyPaint.setShader(bodyShader);
        BorderPaint = new Paint();
        BorderPaint.setColor(Color.BLACK);

        Size += CharacterView.BORDER_WIDTH;

        Bitmap temp;
        int[] ids = Character.getEyesMorphIds();
        Eyes = new Bitmap[ids.length];

        for(int i = 0; i < ids.length; i++) {
            temp = BitmapFactory.decodeResource(Context.getResources(), ids[i], options);
            Eyes[i] = Bitmap.createScaledBitmap(temp, EyesWidth, EyesHeight, false);
        }

        ids = Character.getMouthMorphIds();
        Mouths = new Bitmap[ids.length];

        for(int i = 0; i < ids.length; i++) {
            temp = BitmapFactory.decodeResource(Context.getResources(), ids[i], options);
            Mouths[i] = Bitmap.createScaledBitmap(temp, MouthWidth, MouthHeight, false);
        }
    }

    @Override
    public void run() {
        while(mRun) {
            Canvas c = null;
            try {
                c = SurfaceHolder.lockCanvas(null);
                synchronized (SurfaceHolder) {
                    if(isStarted()) {
                        doDraw(c);
                    }
                }
            } finally {
                if (c != null) {
                    SurfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    private void doDraw(Canvas canvas) {
        if(canvas == null) return;

        long currentTime = System.nanoTime();
        CurrentEyesMorph = Character.getEyesMorph(currentTime);
        CurrentMouthMorph = Character.getMouthMorph(currentTime);

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        if(BodyPaint != null) {
            canvas.drawCircle(Size /2, Size /2, Size /2, BorderPaint);
            canvas.drawCircle(Size /2, Size /2, (Size - CharacterView.BORDER_WIDTH)/2, BodyPaint);
        }

        if(Eyes[CurrentEyesMorph] != null) {
            canvas.drawBitmap(Eyes[CurrentEyesMorph], EyesX, EyesY, null);
        }
        if(Mouths[CurrentMouthMorph] != null) {
            canvas.drawBitmap(Mouths[CurrentMouthMorph], MouthX, MouthY, null);
        }
    }
}

