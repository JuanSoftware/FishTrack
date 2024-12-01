package com.example.fishtrack.shared.utils;

import java.lang.reflect.Field;

public class ObjUtils {
    public static <T> T deepCopy(Class<? extends T> teste, T literalObject){
        try{
            Field[] fields = teste.getDeclaredFields();
//            Class<?>[] argsType = new Class<?>[fields.length];
            Object[] argsValue = new Object[fields.length];
            for(int i = 0; i < fields.length; i++){
                fields[i].setAccessible(true);
//                argsType[i] = fields[i].getType();
//                argsValue[i] = fields[i].get(literalObject);
            }
            return teste.getDeclaredConstructor().newInstance();

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
