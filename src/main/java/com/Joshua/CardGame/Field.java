package com.Joshua.CardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Field extends Stack<Card> {
    private static ArrayList<Integer> fieldSizeArray = new ArrayList<>();

    public Field() {
        super();
    }

    public static int getFieldMaxSize(Field[] fields) {
        fieldSizeArray.clear();
        for (int i = 0; i < fields.length; i++) {
            fieldSizeArray.add(fields[i].size());
        }
        return Collections.max(fieldSizeArray);
    }
}
