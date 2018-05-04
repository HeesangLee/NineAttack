package dalcoms.game.nineattack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dalcoms.game.GameObject;

/**
 * Created by hslee on 2018-04-02.
 */

public class Me extends GameObject {

    private SpriteBatch batch;
    private int trackNum = 0;
    private Texture texture_holo;
    private final float HOLO_Y_CENTER = 33.113f;
    private float resizeFactor = 720f / 1080f;
    private GameObject movingEffect;

    public static boolean MOVE_RIGHT = false;
    public static boolean MOVE_LEFT = true;

    private float[] trackLocationX;
    private float trackWidth;

    private final int TRACK_COUNT = 9;


    public Me(Texture texture, float locationX, float locationY) {
        super(texture, locationX, locationY);
    }

    public Me(int width, int height, float locationX, float locationY) {
        super(width, height, locationX, locationY);
    }

    public Me setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
        return this;
    }

    public Me setMovingEffectTexture(Texture texture) {
        movingEffect = new GameObject(texture, 0, 0);
        return this;
    }

    public Me setHoloTexture(Texture texture) {
        texture_holo = texture;
        return this;
    }

    public Me setTrack(int trackNum) {
        this.trackNum = trackNum;
        return this;
    }

    public Me setTrackInformation(float[] trackLocationX, float trackWidth) {
        this.trackLocationX = trackLocationX;
        this.trackWidth = trackWidth;
        return this;
    }

    private float getTrackCenterLocation(int trackNum) {
        return this.trackLocationX[trackNum] + this.trackWidth / 2;
    }

    private float getTrackLocationLeft(int trackNum) {
        return this.trackLocationX[trackNum];
    }

    private float getTrackLocationRight(int trackNum) {
        return this.trackLocationX[trackNum] + this.trackWidth;
    }

    public int getTrackNum() {
        return trackNum;
    }

    public Me setResizeFactor(float resizeFactor) {
        this.resizeFactor = resizeFactor;
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
            drawMovingEffect(delta);
            drawMe();
            drawHolo();
        }
    }

    private float getHoloLocationX() {
        return this.getCenterX() - texture_holo.getWidth() / 2;
    }

    private float getHoloLocationY() {
        return this.getLocationY() + this.resizeFactor * this.HOLO_Y_CENTER;
    }

    private void drawMovingEffect(float delta) {
        final float EFFECT_Y_OFFSET = resizeFactor * 50f;

        if (movingEffect.getTempFloat() > 0.3f) {
            movingEffect.setTempFloat(0f);
            movingEffect.setTempBoolean(!movingEffect.isTempBoolean());
        } else {
            movingEffect.setTempFloat(movingEffect.getTempFloat() + delta);
        }

        if (isShow() & (movingEffect != null)) {
            batch.draw(
                    movingEffect.getTexture(),
                    getCenterX() - movingEffect.getWidth() / 2f,
                    getLocationY()
                            - movingEffect.getHeight()
                            + (movingEffect.isTempBoolean() ? EFFECT_Y_OFFSET : EFFECT_Y_OFFSET * 1.5f));
        }
    }

    private void drawMe() {
        batch.draw(getTexture(), getLocationX(), getLocationY());
    }

    private void drawHolo() {
        batch.draw(this.texture_holo, getHoloLocationX(), getHoloLocationY());
    }

    public boolean moveToTrack(boolean direction) {
        boolean reachToEdge;
        if (direction == MOVE_RIGHT) {
            reachToEdge = moveToTrackRight();
        } else {//left
            reachToEdge = moveToTrackLeft();
        }
        return reachToEdge;
    }

    private boolean moveToTrackRight() {
        boolean reachToEdge = getTrackNum() == TRACK_COUNT-1 ? true : false;
        if (!reachToEdge) {
            setTrack(getTrackNum() + 1);
            moveX(this.getLocationX(),
                    getTrackCenterLocation(getTrackNum()) - this.getWidth() / 2,
                    0.05f);

        }

        return reachToEdge;
    }

    private boolean moveToTrackLeft() {
        boolean reachToEdge = getTrackNum() == 0 ? true : false;
        if (!reachToEdge) {
            setTrack(getTrackNum() - 1);
            moveX(this.getLocationX(),
                    getTrackCenterLocation(getTrackNum()) - this.getWidth() / 2,
                    0.05f);
        }

        return reachToEdge;
    }
}
