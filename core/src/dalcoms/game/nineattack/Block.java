package dalcoms.game.nineattack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dalcoms.game.GameObject;

/**
 * Created by hslee on 2018-04-26.
 */

public class Block extends GameObject {
    private SpriteBatch batch;
    private Sprite sprite;
    private float resizeFactor = 720f / 1080f;


    public Block(Texture texture, float locationX, float locationY) {
        super(texture, locationX, locationY);
        createSprite(texture);
    }

    private void createSprite(Texture texture) {
        sprite = new Sprite(texture);
    }

    public Block setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
        return this;
    }

    public Block setResizeFactor(float resizeFactor) {
        this.resizeFactor = resizeFactor;
        return this;
    }

    public Block setBaseColor(Color color) {
        if (sprite != null) {
            sprite.setColor(color);
        }
        return this;
    }


    public void show(boolean flag_show) {
        setShow(flag_show);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        draw(delta);
    }

    private void draw(float delta) {
        if (isShow()) {
            changeColorAlphaByPosition();
            drawSelf();
        }
    }

    private void changeColorAlphaByPosition() {
        float position = getLocationY();
        float alpha = 1f;
        if (position < 650f * resizeFactor) {//under the me
            alpha = (0.8f / 650f) * position + 0.2f;
        } else if (position > (650f + 500f) * resizeFactor) {
            alpha = (-0.8f / 770f) * (position - 1150f) + 1f;
        }
        sprite.setAlpha(alpha);
    }

    private void drawSelf() {

        sprite.setPosition(getLocationX(), getLocationY());
        sprite.draw(batch);
    }

}
