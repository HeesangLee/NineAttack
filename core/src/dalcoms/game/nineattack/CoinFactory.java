package dalcoms.game.nineattack;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by hslee on 2018-05-05.
 */

public class CoinFactory {
    final NineAttack game;
    final GameScreen gameScreen;

    private Array<Coin> coinArray;
    private Array<Coin> bank;

    public CoinFactory(NineAttack game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        coinArray = new Array<Coin>();
        bank = new Array<Coin>();
    }

    public CoinFactory prepareBank(int blockCNum) {
        for (int i = 0; i < blockCNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    addToBank();
                }
            }).run();
        }
        return this;
    }

    private void addToBank() {
        bank.add(new Coin(game.resourcesManager.getTexture_coin(),
                0,
                game.gameConfig.getViewportHeight())
                .setSpriteBatch(game.spriteBatch)
                .setResizeFactor(game.gameConfig.getResizeFactor()));

//        Gdx.app.log("pool",  ", PreBank : " + String.valueOf(bank.size) );
    }

    public Array<Coin> getCoin() {
        return coinArray;
    }

    private Coin getCoinFromBank() {
        if (bank.size != 0) {
            return bank.pop();
        } else {
            return new Coin(game.resourcesManager.getTexture_coin(),
                    0,
                    game.gameConfig.getViewportHeight())
                    .setSpriteBatch(game.spriteBatch)
                    .setResizeFactor(game.gameConfig.getResizeFactor());
        }
    }

    public void addCoinWithMap(ItemTag[] itemTags, float duration) {
        for (int i = 0; i < itemTags.length; i++) {
            if (itemTags[i].getItemKind() == Item.ITEM_COIN) {
                final Coin tempCoin = getCoinFromBank();
                tempCoin.show(true);
                tempCoin.setLocation(game.gameConfig.getTrackLocationX(i),
                        game.gameConfig.getViewportHeight());
                tempCoin.moveY(game.gameConfig.getViewportHeight(), 0, duration);
                tempCoin.addActionListener(new GameObjectActionListener() {
                    @Override
                    public boolean onMoveCompleted(boolean direction) {
                        returnToBank(tempCoin);
                        return false;
                    }
                });

                coinArray.add(tempCoin);
            }
        }
    }

    private void returnToBank(Coin coin) {
        coin.show(false);
        coinArray.removeValue(coin, true);
        bank.add(coin);

//        Gdx.app.log("pool", "coinArray : " + String.valueOf(coinArray.size)
//                + ", Bank : " + String.valueOf(bank.size) + ", T : " +
//                String.valueOf(coinArray.size + bank.size));

    }

    public void render(float delta) {
        for (Iterator<Coin> it = coinArray.iterator(); it.hasNext(); ) {
            it.next().render(delta);
        }

    }

}
