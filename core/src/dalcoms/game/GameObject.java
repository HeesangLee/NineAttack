package dalcoms.game;

import com.badlogic.gdx.graphics.Texture;

import dalcoms.game.nineattack.ObjectActionListener;

/**
 * Created by hslee on 2018-04-02.
 */

public class GameObject {
    private int width, height;
    private float locationX, locationY;
    private Texture texture;
    private boolean flag_show = false;

    private boolean flag_moveX, flag_moveY = false;
    private float velocityMoveX, velocityMoveY = 0f;
    private float toX, toY = 0f;

    private int tempInt = 0;
    private float tempFloat = 0;
    private double tempDouble = 0;
    private boolean tempBoolean = false;
    private ObjectActionListener actionListener;


    public GameObject(Texture texture, float locationX, float locationY) {
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public GameObject(Texture texture, float locationX, float locationY, boolean isShow) {
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.locationX = locationX;
        this.locationY = locationY;

        setShow(isShow);
    }

    public GameObject(int width, int height, float locationX, float locationY) {
        this.width = width;
        this.height = height;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void render(float delta) {
        if (isMoveX()) {
            setMoveX(checkMoveX(delta));
        }
        if (isMoveY()) {
            setMoveY(checkMoveY(delta));
        }

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public void setCenterLocation(float locationX, float locationY) {
        setLocation(locationX - getWidth() / 2, locationY - getHeight() / 2);
    }

    public void setLocation(float locationX, float locationY) {
        setLocationX(locationX);
        setLocationY(locationY);
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationX() {
        return this.locationX;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public float getLocationY() {
        return this.locationY;
    }

    public float getCenterX() {
        return getLocationX() + (float) getWidth() / 2;
    }

    public float getCenterY() {
        return getLocationY() + (float) getHeight() / 2;
    }

    public boolean isShow() {
        return this.flag_show;
    }

    public void setShow(boolean flag_show) {
        this.flag_show = flag_show;
    }


    public boolean checkMoveX(float delta) {
        boolean ret = true;
        float toLocation;

        toLocation = getLocationX() + delta * getVelocityMoveX();
        if (getVelocityMoveX() > 0) {
            if (toLocation >= getToX()) {
                toLocation = getToX();
                ret = false;
                if (actionListener != null) {
                    actionListener.onMoveCompleted(ObjectActionListener.DIR_X);
                }
            }
        } else {
            if (toLocation <= getToX()) {
                toLocation = getToX();
                ret = false;
                if (actionListener != null) {
                    actionListener.onMoveCompleted(ObjectActionListener.DIR_X);
                }
            }
        }
        setLocationX(toLocation);

        return ret;
    }

    public boolean checkMoveY(float delta) {
        boolean ret = true;
        float toLocation;

        toLocation = getLocationY() + delta * getVelocityMoveY();
        if (getVelocityMoveY() > 0) {
            if (toLocation >= getToY()) {
                toLocation = getToY();
                ret = false;
                if (actionListener != null) {
                    actionListener.onMoveCompleted(ObjectActionListener.DIR_Y);
                }
            }
        } else {
            if (toLocation <= getToY()) {
                toLocation = getToY();
                ret = false;
                if (actionListener != null) {
                    actionListener.onMoveCompleted(ObjectActionListener.DIR_Y);
                }
            }
        }
        setLocationY(toLocation);

        return ret;
    }

    public void moveX(float fromX, float toX, float duration) {
        setMoveX(true);
        setVelocityMoveX((toX - fromX) / duration);
        setLocationX(fromX);
        this.toX = toX;
        if (actionListener != null) {
            actionListener.onMoveStarted(ObjectActionListener.DIR_X);
        }
    }

    public void moveY(float fromY, float toY, float duration) {
        setMoveY(true);
        setVelocityMoveY((toY - fromY) / duration);
        setLocationY(fromY);
        this.toY = toY;
        if (actionListener != null) {
            actionListener.onMoveStarted(ObjectActionListener.DIR_Y);
        }
    }


    public float getVelocityMoveX() {
        return this.velocityMoveX;
    }

    public void setVelocityMoveX(float vel) {
        this.velocityMoveX = vel;
    }

    public float getVelocityMoveY() {
        return this.velocityMoveY;
    }

    public void setVelocityMoveY(float vel) {
        this.velocityMoveY = vel;
    }

    public boolean isMoveX() {
        return flag_moveX;
    }

    public boolean isMoveY() {
        return flag_moveY;
    }

    public void setMoveX(boolean flag_move) {
        this.flag_moveX = flag_move;
    }

    public void setMoveY(boolean flag_move) {
        this.flag_moveY = flag_move;
    }

    public float getToX() {
        return this.toX;
    }

    public float getToY() {
        return this.toY;
    }

    public void setTempInt(int tempInt) {
        this.tempInt = tempInt;
    }

    public void setTempFloat(float tempFloat) {
        this.tempFloat = tempFloat;
    }

    public void setTempDouble(double tempDouble) {
        this.tempDouble = tempDouble;
    }

    public void setTempBoolean(boolean tempBoolean) {
        this.tempBoolean = tempBoolean;
    }

    public int getTempInt() {
        return tempInt;
    }

    public float getTempFloat() {
        return tempFloat;
    }

    public double getTempDouble() {
        return tempDouble;
    }

    public boolean isTempBoolean() {
        return tempBoolean;
    }

    public void addActionListener(ObjectActionListener listener) {
        actionListener = listener;
    }

}
