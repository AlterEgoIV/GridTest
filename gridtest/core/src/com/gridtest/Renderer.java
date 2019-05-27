package com.gridtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carl on 06/05/2019.
 */
public class Renderer
{
    private ShapeRenderer shapeRenderer;
    private List<Shape> shapes;

    public Renderer()
    {
        shapeRenderer = new ShapeRenderer();
        shapes = new ArrayList<Shape>();

        shapeRenderer.setAutoShapeType(true);
    }

    void addShape(Shape shape)
    {
        shapes.add(shape);
    }

    void render()
    {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin();
        for(Shape shape : shapes)
        {
            renderShape(shape, false);
            renderShape(shape, true);
        }
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void renderShape(Shape shape, boolean renderOutline)
    {
        shapeRenderer.set(renderOutline ? ShapeType.Line : ShapeType.Filled);
        shapeRenderer.setColor(renderOutline ? shape.outlineColor : shape.fillColor);

        switch(shape.shapeName)
        {
            case RECT:
            {
                shapeRenderer.rect(shape.position.x - shape.width / 2, shape.position.y - shape.height / 2, shape.width, shape.height);
                break;
            }

            case ELLIPSE:
            {
                shapeRenderer.ellipse(shape.position.x - shape.width / 2, shape.position.y - shape.height / 2, shape.width, shape.height);
                break;
            }
        }
    }

    void dispose()
    {
        shapeRenderer.dispose();
    }
}
