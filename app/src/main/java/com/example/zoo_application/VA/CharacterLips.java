package com.example.zoo_application.VA;

import com.example.zoo_application.R;

public class CharacterLips {
    public static Character getEmbedded() {
        Character character = new Character();

        character.setBodyDrawableId(R.drawable.jessica_p0);

        int[] eyesMorphs = {
                R.drawable.jessica_p0_e0, R.drawable.jessica_p0_e1, R.drawable.jessica_p0_e2
        };
        character.setEyesMorphIds(eyesMorphs);

        int[] mouthMorphs = {
                R.drawable.jessica_p0_v0, R.drawable.jessica_p0_v1, R.drawable.jessica_p0_v2,
                R.drawable.jessica_p0_v3, R.drawable.jessica_p0_v4, R.drawable.jessica_p0_v5,
                R.drawable.jessica_p0_v6, R.drawable.jessica_p0_v7, R.drawable.jessica_p0_v8,
                R.drawable.jessica_p0_v9, R.drawable.jessica_p0_v10
        };

        character.setMouthMorphIds(mouthMorphs);

        character.setEyes(new CharacterParts(210, 150, 193, 70));
        character.setMouth(new CharacterParts(220, 260, 159, 99));

        return character;
    }

}



