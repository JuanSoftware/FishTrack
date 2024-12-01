package com.example.fishtrack.shared.utils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class StyleUtils implements Cloneable {

    private  GradientDrawable shape = new GradientDrawable();

    public void StyleUtils(){}
    public void setBackground(
        String color,
        float borderRadius,
        int widthStroke,
        String colorStroke
    ){
        shape.setColor(Color.parseColor(color));
        shape.setCornerRadius(borderRadius);
        shape.setStroke(widthStroke,Color.parseColor(colorStroke));
    }

    public <T extends View> void setStyleInElement(T element){
        element.setBackground(shape);
    }


}
