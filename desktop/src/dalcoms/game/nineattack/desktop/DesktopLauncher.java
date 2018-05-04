package dalcoms.game.nineattack.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dalcoms.game.nineattack.NineAttack;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Nine attack desktop";
        config.width = 480;
        config.height = 800;
        new LwjglApplication(new NineAttack(), config);
    }
}
