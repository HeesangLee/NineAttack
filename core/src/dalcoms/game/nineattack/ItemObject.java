package dalcoms.game.nineattack;

import com.badlogic.gdx.graphics.Texture;

import dalcoms.game.GameObject;

/**
 * Created by hslee on 2018-05-03.
 */

public class ItemObject extends GameObject implements Item {
    private int itemKind = Item.ITEM_BLANK;
    private int bulletKind = Item.BULLET_1;

    public ItemObject(Texture texture, float locationX, float locationY) {
        super(texture, locationX, locationY);
    }

    public ItemObject(Texture texture, float locationX, float locationY, boolean isShow) {
        super(texture, locationX, locationY, isShow);
    }

    public ItemObject(int width, int height, float locationX, float locationY) {
        super(width, height, locationX, locationY);
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
}
