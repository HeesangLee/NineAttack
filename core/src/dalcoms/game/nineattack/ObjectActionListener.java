package dalcoms.game.nineattack;

/**
 * Created by hslee on 2018-04-19.
 */

public interface ObjectActionListener {
    public static boolean DIR_X = false;
    public static boolean DIR_Y = true;

    public boolean onMoveCompleted(boolean direction);
    public boolean onMoveStarted(boolean direction);

}
