package com.example.zoo_application.VA;

public class Character {
    private EyesMotion mEyesMorph;
    private MouthMotion mMouthMorph;

    private int BodyDrawableId;
    private int[] EyesMorphIds;
    private int[] MouthMorphIds;

    private CharacterParts Eyes;
    private CharacterParts Mouth;

    //endregion

    //region Constructor

    public Character() {
        mEyesMorph = new EyesMotion();
        mMouthMorph = new MouthMotion();
    }

    //endregion

    //region Methods

    public int getBodyDrawableId() {
        return BodyDrawableId;
    }

    public void setBodyDrawableId(int bodyDrawableId) {
        BodyDrawableId = bodyDrawableId;
    }

    public int[] getEyesMorphIds() {
        return EyesMorphIds;
    }

    public void setEyesMorphIds(int[] eyesMorphIds) {
        EyesMorphIds = eyesMorphIds;
    }

    public int[] getMouthMorphIds() {
        return MouthMorphIds;
    }

    public void setMouthMorphIds(int[] mouthMorphIds) {
        MouthMorphIds = mouthMorphIds;
    }

    public int getEyesMorph(long currentTime) {
        return mEyesMorph.getMotion(currentTime);
    }

    public int getMouthMorph(long currentTime) {
        return mMouthMorph.getMotion(currentTime);
    }

    public void setMouthMorphs(int[] timeMorphs) {
        mMouthMorph.setMouthMorphs(timeMorphs);
    }

    public void endMouthMorphs() {
        mMouthMorph.endMouthMotion();
    }

    public void setEyes(CharacterParts eyes) {
        Eyes = eyes;
    }

    public CharacterParts getEyes() {
        return Eyes;
    }

    public void setMouth(CharacterParts mouth) {
        Mouth = mouth;
    }

    public CharacterParts getMouth() {
        return Mouth;
    }

    //endregion

}

