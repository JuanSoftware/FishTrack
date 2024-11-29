package com.example.fishtrack.activities.shared.utils.style;

import android.graphics.drawable.GradientDrawable;

public class BackgroundBorderStyle {

    public static void setBackgroundBorderColor(String color, float borderRadius){
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(borderRadius);
    }

}
