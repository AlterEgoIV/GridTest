package com.gridtest;

import com.badlogic.gdx.Input;

/**
 * Created by Carl on 24/05/2019.
 */
public class InputState
{
    private boolean[] keys;

    public InputState()
    {
        keys = new boolean[Input.Keys.class.getFields().length];

        for(boolean key : keys)
        {
            key = false;
        }
    }

    public void keyDown(int keyCode)
    {
        keys[keyCode] = true;
    }

    public void keyUp(int keyCode)
    {
        keys[keyCode] = false;
    }

    public boolean isDown(int keyCode)
    {
        return keys[keyCode];
    }
}
