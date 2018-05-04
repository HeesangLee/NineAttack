package dalcoms.game.nineattack;

/**
 * Created by hslee on 2018-05-03.
 */

public class ItemTag implements Item {

    private int itemKind = Item.ITEM_BLANK;
    private int bulletKind = Item.BULLET_1;

    public ItemTag(int itemKind) {
        setItemKind(itemKind);
    }

    public ItemTag(int itemKind, int bulletKind) {
        setItemKind(itemKind);
        setBulletKind(bulletKind);
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
