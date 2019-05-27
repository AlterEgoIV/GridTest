package com.gridtest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Carl on 06/05/2019.
 */
public class Shape
{
    public Vector2 position;
    public float width, height;
    public ShapeName shapeName;
    public Color fillColor, outlineColor;

    public Shape(Vector2 position, float width, float height, ShapeName shapeName, Color fillColor, Color outlineColor)
    {
        this.position = position;
        this.width = width;
        this.height = height;
        this.shapeName = shapeName;
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
    }
}
