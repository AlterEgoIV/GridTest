package com.gridtest;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Carl on 06/05/2019.
 */
public abstract class GameObject
{
    public Vector2 position;
    public float width, height;
    public Shape shape;

    public GameObject(Vector2 position, float width, float height, Shape shape)
    {
        this.position = position;
        this.width = width;
        this.height = height;
        this.shape = shape;
    }

    public abstract void update();
}
