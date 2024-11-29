package com.example.fishtrack.shared.utils.style;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class StyleUtils {

    public  static <T extends View> void setBackgroundBorderColor(
        T element,
        String color,
        float borderRadius,
        int widthStroke,
        String colorStroke
    ){
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(Color.parseColor(color));
        shape.setCornerRadius(borderRadius);
        shape.setStroke(widthStroke,Color.parseColor(colorStroke));
        element.setBackground(shape);
    }

}
