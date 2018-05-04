package dalcoms.game.nineattack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * Created by hslee on 2018-04-04.
 */

public class ResourcesManager {
    private static ResourcesManager instance = new ResourcesManager();
    private String assetFolderPrefix = "L_";

    private Texture texture_track;
    private Texture texture_me;
    private Texture texture_circleMe;
    private Texture texture_9icon;
    private Texture texture_text_dalcoms_blue;
    private Texture texture_text_attack_blue;
    private Texture texture_text_nineAttack;
    private Texture texture_text_tabToStart;
    private Texture texture_thumbUp;
    private Texture texture_meMovingEffect;
    private Texture texture_itemCircle;
    private Texture texture_bullet1;
    private Texture texture_bullet2;
    private Texture texture_bullet3;
    private Texture texture_block;

    private Array<Texture> textures_numInfoSmall;

    public static ResourcesManager getInstance() {
        return instance;
    }

    public void loadResouces() {
        texture_track = new Texture(Gdx.files.internal(assetFolderPrefix + "track.png"));
        texture_me = new Texture(Gdx.files.internal(assetFolderPrefix + "me.png"));
        texture_circleMe = new Texture(Gdx.files.internal(assetFolderPrefix + "circle_me.png"));
        texture_text_nineAttack = new Texture(Gdx.files.internal(assetFolderPrefix + "text_nineattack.png"));
        texture_text_tabToStart = new Texture(Gdx.files.internal(assetFolderPrefix + "text_tabtostart.png"));
        texture_thumbUp = new Texture(Gdx.files.internal(assetFolderPrefix + "thumbup.png"));
        texture_meMovingEffect = new Texture(Gdx.files.internal(assetFolderPrefix + "meMovingEffect.png"));

        texture_itemCircle = new Texture(Gdx.files.internal(assetFolderPrefix + "itemCircle.png"));
        texture_bullet1 = new Texture(Gdx.files.internal(assetFolderPrefix + "bullet1.png"));
        texture_bullet2 = new Texture(Gdx.files.internal(assetFolderPrefix + "bullet2.png"));
        texture_bullet3 = new Texture(Gdx.files.internal(assetFolderPrefix + "bullet3.png"));

        texture_block = new Texture(Gdx.files.internal(assetFolderPrefix + "block.png"));

        textures_numInfoSmall = new Array<Texture>() {
            {
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_0.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_1.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_2.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_3.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_4.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_5.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_6.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_7.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_8.png")));
                add(new Texture(Gdx.files.internal(assetFolderPrefix + "numInfoSmall_9.png")));
            }
        };
    }

    public void loadResouces(String assetsFolder) {
        setAssetFolderPrefix(assetsFolder);
        loadResouces();
    }

    public void loadResoucesSplash() {
        texture_9icon = new Texture(Gdx.files.internal(assetFolderPrefix + "9icon.png"));
        texture_text_dalcoms_blue = new Texture(Gdx.files.internal(assetFolderPrefix + "text_dalcoms_blue.png"));
        texture_text_attack_blue = new Texture(Gdx.files.internal(assetFolderPrefix + "text_attack_blue.png"));
    }

    public void loadResoucesSplash(String assetsFolder) {
        setAssetFolderPrefix(assetsFolder);
        loadResoucesSplash();
    }

    public void setAssetFolderPrefix(String assetFolderPrefix) {
        this.assetFolderPrefix = assetFolderPrefix;
    }

    public Texture getTexture_track() {
        return texture_track;
    }

    public Texture getTexture_me() {
        return texture_me;
    }

    public Texture getTexture_circleMe() {
        return texture_circleMe;
    }

    public Texture getTexture_9icon() {
        return texture_9icon;
    }

    public Texture getTexture_text_dalcoms_blue() {
        return texture_text_dalcoms_blue;
    }

    public Texture getTexture_text_attack_blue() {
        return texture_text_attack_blue;
    }

    public Texture getTexture_text_nineAttack() {
        return texture_text_nineAttack;
    }

    public Texture getTexture_text_tabToStart() {
        return texture_text_tabToStart;
    }

    public Texture getTexture_thumbUp() {
        return texture_thumbUp;
    }

    public Texture getTexture_meMovingEffect() {
        return texture_meMovingEffect;
    }

    public Texture getTexture_itemCircle() {
        return texture_itemCircle;
    }

    public Texture getTexture_bullet1() {
        return texture_bullet1;
    }

    public Texture getTexture_bullet2() {
        return texture_bullet2;
    }

    public Texture getTexture_bullet3() {
        return texture_bullet3;
    }

    public Texture getTexture_block() {
        return texture_block;
    }

    public Texture getNumInfoSmall(int num) {
        return textures_numInfoSmall.get(num);
    }

    public Array<Texture> getNumInforSmallArray() {
        return textures_numInfoSmall;
    }

    public void dispose() {
        texture_track.dispose();
        texture_me.dispose();
        texture_circleMe.dispose();
        texture_text_nineAttack.dispose();
        texture_text_tabToStart.dispose();
        texture_thumbUp.dispose();
        texture_meMovingEffect.dispose();

        texture_itemCircle.dispose();
        texture_bullet1.dispose();
        texture_bullet2.dispose();
        texture_bullet3.dispose();

        texture_block.dispose();

        for (Texture t : textures_numInfoSmall) {
            t.dispose();
        }

        disposeSplash();
    }

    public void disposeSplash() {
        texture_9icon.dispose();
        texture_text_dalcoms_blue.dispose();
        texture_text_attack_blue.dispose();
    }

}
