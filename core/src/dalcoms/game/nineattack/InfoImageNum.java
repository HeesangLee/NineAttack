package dalcoms.game.nineattack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import dalcoms.game.GameObject;

/**
 * Created by hslee on 2018-04-12.
 */

public class InfoImageNum extends GameObject {
    private float gapImageNum, gapNums = 0f;
    private int num = 0;
    private SpriteBatch batch;
    private float resizeFactor = 720f / 1080f;
    private Array<Texture> textures_numArray;

    public InfoImageNum(Texture texture, float locationX, float locationY) {
        super(texture, locationX, locationY);
    }

    public InfoImageNum(Texture texture, float locationX, float locationY, boolean isShow) {
        super(texture, locationX, locationY, isShow);
    }

    public InfoImageNum setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
        return this;
    }

    public InfoImageNum setTexureNumArray(Array<Texture> ar) {
        this.textures_numArray = ar;
        return this;
    }

    public void setGapImageNum(float gapImageNum) {
        this.gapImageNum = gapImageNum;
    }

    public void setGapNums(float gapNums) {
        this.gapNums = gapNums;
    }

    public void setNumber(int num) {
        this.num = num;
    }

    public int getNumber() {
        return this.num;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        draw(delta);
    }

    public InfoImageNum setResizeFactor(float resizeFactor) {
        this.resizeFactor = resizeFactor;
        return this;
    }

    private void draw(float delta) {
        if (isShow()) {
            drawIcon();
            drawNumber();
        }
    }

    private void drawIcon() {
        batch.draw(getTexture(), getLocationX(), getLocationY());
    }

    private void drawNumber() {
        int num = getNumber();
        int num1kk, num100k, num10k, num1k, num100, num10, num1 = 0;
        boolean dpZero = false;
        float gapX = getLocationX() + getWidth() + gapImageNum;

        num1kk = num / 1000000;
        num = num % 1000000;//num1kk;
        if (num1kk != 0) {
            dpZero = true;
            batch.draw(textures_numArray.get(num1kk), gapX, getLocationY());
            gapX += textures_numArray.get(num1kk).getWidth() + gapNums;
        }

        num100k = num / 100000;
        num = num % 100000;//% num100k;
        if ((num100k != 0) || (dpZero == true)) {
            dpZero = true;
            batch.draw(textures_numArray.get(num100k), gapX, getLocationY());
            gapX += textures_numArray.get(num100k).getWidth() + gapNums;
        }

        num10k = num / 10000;
        num = num % 10000;//% num10k;
        if ((num10k != 0) || (dpZero == true)) {
            dpZero = true;
            batch.draw(textures_numArray.get(num10k), gapX, getLocationY());
            gapX += textures_numArray.get(num10k).getWidth() + gapNums;
        }

        num1k = num / 1000;
        num = num % 1000;//% num1k;
        if ((num1k != 0) || (dpZero == true)) {
            dpZero = true;
            batch.draw(textures_numArray.get(num1k), gapX, getLocationY());
            gapX += textures_numArray.get(num1k).getWidth() + gapNums;
        }

        num100 = num / 100;
        num = num % 100;//% num100;
        if ((num100 != 0) || (dpZero == true)) {
            dpZero = true;
            batch.draw(textures_numArray.get(num100), gapX, getLocationY());
            gapX += textures_numArray.get(num100).getWidth() + gapNums;
        }

        num10 = num / 10;
        if ((num10 != 0) || (dpZero == true)) {
            dpZero = true;
            batch.draw(textures_numArray.get(num10), gapX, getLocationY());
            gapX += textures_numArray.get(num10).getWidth() + gapNums;
        }

        num1 = num % 10;//% num10;
        batch.draw(textures_numArray.get(num1), gapX, getLocationY());
    }

}
