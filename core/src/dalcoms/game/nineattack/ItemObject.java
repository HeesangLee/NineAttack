package dalcoms.game.nineattack;

import com.badlogic.gdx.graphics.Texture;

import dalcoms.game.GameObject;

/**
 * Created by hslee on 2018-05-03.
 */

public class ItemObject extends GameObject implements Item {
    private int itemKind = Item.ITEM_BLANK;
    private int bulletKind = Item.BULLET_1;

    private GameObject collisionGameObject;
    private boolean flag_checkCollision = false;
    private boolean isCollideWith = false;

    public ItemObject(Texture texture, float locationX, float locationY) {
        super(texture, locationX, locationY);
    }

    public ItemObject(Texture texture, float locationX, float locationY, boolean isShow) {
        super(texture, locationX, locationY, isShow);
    }

    public ItemObject(int width, int height, float locationX, float locationY) {
        super(width, height, locationX, locationY);
    }

    public void checkCollideWithGameObject(GameObject gameObject, boolean flagCheckCollision) {
        collisionGameObject = gameObject;
        this.flag_checkCollision = flagCheckCollision;
    }

    public void checkCollideWithGameObject(boolean flagCheckCollision) {
        if (collisionGameObject != null) {
            flag_checkCollision = flagCheckCollision;
        } else {
            flag_checkCollision = false;
        }
    }

    private boolean checkCollisionX() {
        boolean condition1, condition2;
        condition1 = (collisionGameObject.getLocationX() > this.getLocationX())
                && (collisionGameObject.getLocationX() < this.getLocationX() + this.getWidth());
        condition2 = (collisionGameObject.getLocationX() + collisionGameObject.getWidth()
                > this.getLocationX())
                && (collisionGameObject.getLocationX() + collisionGameObject.getWidth()
                < this.getLocationX() + this.getWidth());

        return condition1 | condition2;
    }

    private boolean checkCollisionY() {
        boolean condition1, condition2;
        condition1 = (collisionGameObject.getLocationY() > this.getLocationY())
                && (collisionGameObject.getLocationY() < this.getLocationY() + this.getHeight());
        condition2 = (collisionGameObject.getLocationY() + collisionGameObject.getHeight()
                > this.getLocationY())
                && (collisionGameObject.getLocationY() + collisionGameObject.getHeight()
                < this.getLocationY() + this.getHeight());

        return condition1 | condition2;
    }

    private void checkCollideWith() {
        if (checkCollisionX() | checkCollisionY()) {
            isCollideWith();
        } else {
            if (isCollideWith == true) {
                isCollideWith = false;
                exitCollideWidth();
            }
        }
    }

    public void isCollideWith() {
        if (isCollideWith == false) {
            startCollideWidh();
        }
        isCollideWith = true;
    }

    public void startCollideWidh() {

    }

    public void exitCollideWidth() {

    }

    @Override
    public void setItemKind(int itemKind) {
        this.itemKind = itemKind;
    }

    @Override
    public int getItemKind() {
        return this.itemKind;
    }


    @Override
    public void setBulletKind(int bulletKind) {
        this.bulletKind = bulletKind;
    }

    @Override
    public int getBulletKind() {
        return this.bulletKind;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (flag_checkCollision) {
            checkCollideWith();
        }
    }
}
