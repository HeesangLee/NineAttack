package dalcoms.game.nineattack;

/**
 * Created by hslee on 2018-05-03.
 */

public interface Item {
    public static final int ITEM_BLANK = 0;
    public static final int ITEM_BULLET = 1;
    public static final int ITEM_COIN = 2;
    public static final int ITEM_BLOCK = 3;


    public static final int BULLET_1 = 0;
    public static final int BULLET_2 = 1;
    public static final int BULLET_3 = 2;

    public void setItemKind(int itemKind);

    public int getItemKind();

    public void setBulletKind(int bulletKind);

    public int getBulletKind();

}
