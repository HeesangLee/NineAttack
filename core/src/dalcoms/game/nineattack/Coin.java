package dalcoms.game.nineattack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by hslee on 2018-05-04.
 */

public class Coin extends ItemObject{
    private SpriteBatch batch;
    private float resizeFactor = 720f / 1080f;

    public Coin(Texture texture, float locationX, float locationY) {
        super(texture, locationX, locationY);
    }

    public Coin(Texture texture, float locationX, float locationY, boolean isShow) {
        super(texture, locationX, locationY, isShow);
    }

    public Coin(int width, int height, float locationX, float locationY) {
        super(width, height, locationX, locationY);
    }

    public void show(boolean flag_show) {
        setShow(flag_show);
    }

    public Coin setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
        return this;
    }

    public Coin setResizeFactor(float resizeFactor) {
        this.resizeFactor = resizeFactor;
        return this;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        draw(delta);
    }

    private void draw(float delta) {
        if (isShow()) {
            drawSelf();
        }
    }
    private void drawSelf() {
        batch.draw(getTexture(), getLocationX(), getLocationY());
    }
}
