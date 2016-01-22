package TTS2016;

import org.newdawn.slick.state.*;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.newdawn.slick.Animation;

import org.newdawn.slick.Color;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.Font;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.TrueTypeFont;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;

class Item {

    public int x;
    public int y;
    public boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image healthpotion = new Image(
            "res/health_potion.png");

    Item(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = healthpotion;

    }

}

class Item1 {

    public int x;
    public int y;
    public boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image healthpotion = new Image("res/speed_potion.png");

    Item1(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = healthpotion;

    }

}

class Itemwin {

    public int x;
    public int y;
    public static boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image antidote = new Image("res/antidote.png");

    Itemwin(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = antidote;

    }

}

public class TTS2016 extends BasicGameState {

    public Item healthpotion, healthpotion1;
    public Item1 speedpotion, speedpotion1;
    public Itemwin antidote;
    public static Candy candy1, candy2;
    public static Soda soda1, soda2;
    public Maid Mary, Esperanza;
    public Butler Thomas, Alfred;
    public static Destroyable1 destroyable1a, destroyable1b, destroyable1c,
            destroyable1d, destroyable1e;
    public static Destroyable2 destroyable2a, destroyable2b, destroyable2c,
            destroyable2d, destroyable2e;

    public ArrayList<Item> stuff = new ArrayList();

    public ArrayList<Item1> stuff1 = new ArrayList();

    public ArrayList<Itemwin> stuffwin = new ArrayList();

    public ArrayList<Candy> Candyshop = new ArrayList();

    public ArrayList<Soda> Sodashop = new ArrayList();

    public static ArrayList<Maid> brushes = new ArrayList();
    
    public static ArrayList<Butler> platters = new ArrayList();
    
    public ArrayList<Destroyable1> desks = new ArrayList();
    
    public ArrayList<Destroyable2> tables = new ArrayList();

    private boolean[][] hostiles;

    private static TiledMap grassMap;

    private static AppGameContainer app;

    private static Camera camera;

    public static int counter = 0;

    // Player stuff
    private Animation sprite, up, down, left, right, wait;

    /**
     *
     * The collision map indicating which tiles block movement - generated based
     *
     * on tile properties
     */
    // changed to match size of sprites & map
    private static final int SIZE = 64;

    // screen width and height won't change
    private static final int SCREEN_WIDTH = 1000;

    private static final int SCREEN_HEIGHT = 750;

    public TTS2016(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);

        gc.setShowFPS(false);

		// *******************
        // Scenerey Stuff
        // ****************
        grassMap = new TiledMap("res/Mansion.tmx");

        // Ongoing checks are useful
        System.out.println("Tile map is this wide: " + grassMap.getWidth());

        camera = new Camera(gc, grassMap);

		// *********************************************************************************
        // Player stuff --- these things should probably be chunked into methods
        // and classes
        // *********************************************************************************
        SpriteSheet runningSS = new SpriteSheet(
                "res/BratSpriteSheet.png", 64, 64, 0);

		// System.out.println("Horizontal count: "
        // +runningSS.getHorizontalCount());
        // System.out.println("Vertical count: " +runningSS.getVerticalCount());
        up = new Animation();

        up.setAutoUpdate(true);

        up.addFrame(runningSS.getSprite(0, 8), 330);

        up.addFrame(runningSS.getSprite(1, 8), 330);

        up.addFrame(runningSS.getSprite(2, 8), 330);

        up.addFrame(runningSS.getSprite(3, 8), 330);

        up.addFrame(runningSS.getSprite(4, 8), 330);

        up.addFrame(runningSS.getSprite(5, 8), 330);

        up.addFrame(runningSS.getSprite(6, 8), 330);

        up.addFrame(runningSS.getSprite(7, 8), 330);

        up.addFrame(runningSS.getSprite(8, 8), 330);

        down = new Animation();

        down.setAutoUpdate(false);

        down.addFrame(runningSS.getSprite(0, 10), 330);

        down.addFrame(runningSS.getSprite(1, 10), 330);

        down.addFrame(runningSS.getSprite(2, 10), 330);

        down.addFrame(runningSS.getSprite(3, 10), 330);

        down.addFrame(runningSS.getSprite(4, 10), 330);

        down.addFrame(runningSS.getSprite(5, 10), 330);

        down.addFrame(runningSS.getSprite(6, 10), 330);

        down.addFrame(runningSS.getSprite(7, 10), 330);

        down.addFrame(runningSS.getSprite(8, 10), 330);

        left = new Animation();

        left.setAutoUpdate(false);

        left.addFrame(runningSS.getSprite(0, 9), 330);

        left.addFrame(runningSS.getSprite(1, 9), 330);

        left.addFrame(runningSS.getSprite(2, 9), 330);

        left.addFrame(runningSS.getSprite(3, 9), 330);

        left.addFrame(runningSS.getSprite(4, 9), 330);

        left.addFrame(runningSS.getSprite(5, 9), 330);

        left.addFrame(runningSS.getSprite(6, 9), 330);

        left.addFrame(runningSS.getSprite(7, 9), 330);

        left.addFrame(runningSS.getSprite(8, 9), 330);

        right = new Animation();

        right.setAutoUpdate(false);

        right.addFrame(runningSS.getSprite(0, 11), 330);

        right.addFrame(runningSS.getSprite(1, 11), 330);

        right.addFrame(runningSS.getSprite(2, 11), 330);

        right.addFrame(runningSS.getSprite(3, 11), 330);

        right.addFrame(runningSS.getSprite(4, 11), 330);

        right.addFrame(runningSS.getSprite(5, 11), 330);

        right.addFrame(runningSS.getSprite(6, 11), 330);

        right.addFrame(runningSS.getSprite(7, 11), 330);

        right.addFrame(runningSS.getSprite(8, 11), 330);

        wait = new Animation();

        wait.setAutoUpdate(true);

        wait.addFrame(runningSS.getSprite(0, 14), 733);

        wait.addFrame(runningSS.getSprite(1, 14), 733);

        wait.addFrame(runningSS.getSprite(2, 14), 733);

        wait.addFrame(runningSS.getSprite(3, 14), 733);

		// wait.addFrame(runningSS.getSprite(2, 14), 733);
        // wait.addFrame(runningSS.getSprite(5, 14), 333);
        sprite = wait;

		// *****************************************************************
        // Obstacles etc.
        // build a collision map based on tile properties in the TileD map
        Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

		// System.out.println("Map height:" + grassMap.getHeight());
        // System.out.println("Map width:" + grassMap.getWidth());
        // There can be more than 1 layer. You'll check whatever layer has the
        // obstacles.
        // You could also use this for planning traps, etc.
        // System.out.println("Number of tile layers: "
        // +grassMap.getLayerCount());
        System.out.println("The grassmap is " + grassMap.getWidth() + "by "
                + grassMap.getHeight());

        
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

				// int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                // Why was this changed?
                // It's a Different Layer.
                // You should read the TMX file. It's xml, i.e.,human-readable
                // for a reason
                int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");

                if ("true".equals(value)) {

                    System.out.println("The tile at x " + xAxis + " andy axis "
                            + yAxis + " is blocked.");

                    Blocked.blocked[xAxis][yAxis] = true;

                }

            }

        }
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

				// int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                // Why was this changed?
                // It's a Different Layer.
                // You should read the TMX file. It's xml, i.e.,human-readable
                // for a reason
                int tileID = grassMap.getTileId(xAxis, yAxis, 3);

                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");

                if ("true".equals(value)) {

                    System.out.println("The tile at x " + xAxis + " andy axis "
                            + yAxis + " is blocked.");

                    Blocked.blocked[xAxis][yAxis] = true;

                }

            }

        }

        System.out.println("Array length" + Blocked.blocked[0].length);

        // A remarkably similar process for finding hostiles
        hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        Item i = new Item(xAxis * SIZE, yAxis * SIZE);
                        stuff.add(i);
                        //stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (xBlock % 9 == 0 && yBlock % 25 == 0) {
                        Item1 h = new Item1(xAxis * SIZE, yAxis * SIZE);
                        //	stuff.add(i);
                        stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

       // healthpotion = new Item(100, 100);
        //healthpotion1 = new Item(450, 400);
        candy1 = new Candy(2335, 345);
        candy2 = new Candy(3450, 365);
        //stuff.add(healthpotion);
        //stuff.add(healthpotion1);
        Candyshop.add(candy1);
        Candyshop.add(candy2);
        //speedpotion = new Item1(100, 150);
        //speedpotion1 = new Item1(450, 100);
        //stuff1.add(speedpotion);
        //stuff1.add(speedpotion1);
        soda1 = new Soda(90, 220);
        soda2 = new Soda(6120, 1065);
        Sodashop.add(soda1);
        Sodashop.add(soda2);
        Mary = new Maid(125, 750);
        Esperanza = new Maid(6100, 325);
        Thomas = new Butler(3315,345);
        Alfred = new Butler(5000,475);
        brushes.add(Mary);
        brushes.add(Esperanza);
        platters.add(Thomas);
        platters.add(Alfred);
        //antidote = new Itemwin(3004, 92);
       // stuffwin.add(antidote);
        destroyable1a = new Destroyable1(256,256);
        destroyable1b = new Destroyable1(256,128);
        destroyable1c = new Destroyable1(128,256);
        destroyable1d = new Destroyable1(64,64);
        destroyable1e = new Destroyable1(1096,256);
        desks.add(destroyable1a);
        desks.add(destroyable1b);
        desks.add(destroyable1c);
        desks.add(destroyable1d);
        desks.add(destroyable1e);
        destroyable2a = new Destroyable2(512,512);
        destroyable2b = new Destroyable2(512,256);
        destroyable2c = new Destroyable2(256,512);
        destroyable2d = new Destroyable2(128,128);
        destroyable2e = new Destroyable2(1099,512);
        tables.add(destroyable2a);
        tables.add(destroyable2b);
        tables.add(destroyable2c);
        tables.add(destroyable2d);
        tables.add(destroyable2e);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) Player.x, (int) Player.y);

        camera.drawMap();

        camera.translateGraphics();

		// it helps to add status reports to see what's going on
        // but it gets old quickly
        // System.out.println("Current X: " +player.x + " \n Current Y: "+ y);
        sprite.draw((int) Player.x, (int) Player.y);

       g.drawString("x: " + (int)Player.x + " y: " +(int)Player.y , Player.x, Player.y - 10);
        g.drawString("Health: " + Player.health / 1000, camera.cameraX + 10,
                camera.cameraY + 10);

        g.drawString("speed: " + (int) (Player.speed * 10), camera.cameraX + 10,
                camera.cameraY + 25);

        //g.draw(player.rect);
        g.drawString("time passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
        // moveenemies();

        /*for (Item i : stuff) {
            if (i.isvisible) {
                i.currentImage.draw(i.x, i.y);
                // draw the hitbox
                //g.draw(i.hitbox);

            }
        }

        for (Item1 h : stuff1) {
            if (h.isvisible) {
                h.currentImage.draw(h.x, h.y);
                // draw the hitbox
                //g.draw(h.hitbox);

            }
        }

        for (Itemwin w : stuffwin) {
            if (w.isvisible) {
                w.currentImage.draw(w.x, w.y);
                // draw the hitbox
                //g.draw(w.hitbox);

            }
        }
*/
        for (Candy n : Candyshop) {
            if (n.isvisible) {
                n.currentImage.draw(n.x, n.y);
                // draw the hitbox
                //g.draw(n.hitbox);

            }
        }
        for (Soda s : Sodashop) {
            if (s.isvisible) {
                s.currentImage.draw(s.x, s.y);
                // draw the hitbox
                //g.draw(s.hitbox);

            }
        }
        for (Maid e : brushes) {
             if (e.isAlive) {
                e.currentanime.draw(e.Bx, e.By);
                // draw the hitbox
                //g.draw(e.hitbox);
        }
        }
        for (Butler e : platters) {
             if (e.isAlive) {
                e.currentanime.draw(e.Bx, e.By);
                // draw the hitbox
                //g.draw(e.hitbox);
        }
        }
        for(Destroyable1 d1 : desks) {
            if(d1.isvisible){
               d1.currentImage.draw(d1.x,d1.y);
               //g.draw(d1.hitbox);
            }
        }
        
        for(Destroyable2 d2 : tables){
            if (d2.isvisible){
                d2.currentImage.draw(d2.x,d2.y);
                //g.draw(d2.hitbox);
            }
        }
        //stormy.currentImage.draw(stormy.x, stormy.y);
        //daniel.currentImage.draw(daniel.x, daniel.y);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        if(Player.counter>5){
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                }
        counter += delta;

        Input input = gc.getInput();

        float fdelta = delta * Player.speed;
        
        //float fdelta = delta * Player.speed;

        Player.setpdelta(fdelta);

        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

        // System.out.println("Right limit: " + rightlimit);
        float projectedright = Player.x + fdelta + SIZE;

                   
        boolean cangoright = projectedright < rightlimit;

        // there are two types of fixes. A kludge and a hack. This is a kludge.
        if (input.isKeyDown(Input.KEY_UP)) {

            sprite = up;

            float fdsc = (float) (fdelta - (SIZE * .15));

            if (!(isBlocked(Player.x, Player.y - fdelta) || isBlocked((float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {

                sprite.update(delta);

                // The lower the delta the slower the sprite will animate.
                Player.y -= fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_DOWN)) {

            sprite = down;

            if (!isBlocked(Player.x, Player.y + SIZE + fdelta)
                    || !isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta)) {

                sprite.update(delta);

                Player.y += fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_LEFT)) {

            sprite = left;

            if (!(isBlocked(Player.x - fdelta, Player.y) || isBlocked(Player.x
                    - fdelta, Player.y + SIZE - 1))) {

                sprite.update(delta);

                Player.x -= fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {

            sprite = right;

            // the boolean-kludge-implementation
            if (cangoright
                    && (!(isBlocked(Player.x + SIZE + fdelta,
                            Player.y) || isBlocked(Player.x + SIZE + fdelta, Player.y
                            + SIZE - 1)))) {

                sprite.update(delta);

                Player.x += fdelta;

            } // else { System.out.println("Right limit reached: " +
            // rightlimit);}

        }

        Player.rect.setLocation(Player.getplayershitboxX(),
                Player.getplayershitboxY());

        /*for (Item i : stuff) {

            if (Player.rect.intersects(i.hitbox)) {
                //System.out.println("yay");
                if (i.isvisible) {

                    Player.health += 100000;
                    i.isvisible = false;
                }

            }
        }*/
        for (Candy n : Candyshop) {

            if (Player.rect.intersects(n.hitbox)) {
                //System.out.println("yay");
                if (n.isvisible) {

                    Player.health += 100000;
                    n.isvisible = false;
                }

            }
        }

       /* for (Item1 h : stuff1) {

            if (Player.rect.intersects(h.hitbox)) {
                //System.out.println("yay");
                if (h.isvisible) {

                    Player.speed += .1f;
                    h.isvisible = false;
                }

            }
        }

        for (Itemwin w : stuffwin) {

            if (Player.rect.intersects(w.hitbox)) {
                //System.out.println("yay");
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    

                }

            }
        }
        */
        for (Maid m : brushes) {
            if (Math.abs(Player.x - m.Bx) < 512){
                m.move();
            }
        }
        
        for(Maid e : brushes){
            if (Player.rect.intersects(e.rect)) {
                //System.out.println("yay");
                {

                    Player.health -= 150;
                    
                }

            }
        }
        
        for (Butler b : platters){
            if (Math.abs(Player.x - b.Bx) < 512){
                b.move();
            }
        }
        
        for(Butler e : platters){
            if (Player.rect.intersects(e.rect)) {
                //System.out.println("yay");
                {

                    Player.speed -= .01f;
                    
                }

            }
        }
        
        for (Soda s : Sodashop) {

            if (Player.rect.intersects(s.hitbox)) {
                //System.out.println("yay");
                if (s.isvisible) {

                    Player.speed += .3f;
                    s.isvisible = false;
                }

            }
        }
         for (Destroyable1 d : desks) {

            if (Player.rect.intersects(d.hitbox)) {
                //System.out.println("yay");
                if (d.isvisible) {

                    Player.counter += 1;
                    d.isvisible = false;
                    
                }
            }
        }
        for (Destroyable2 d : tables) {

            if (Player.rect.intersects(d.hitbox)) {
                //System.out.println("yay");
                if (d.isvisible) {

                    Player.counter += 1;
                    d.isvisible = false;
                    
                }
            }
        }
         

        Player.health -= counter / 1000;
        if (Player.health <= 0||Player.speed <= 0f) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (Item1 h : stuff1) {

            h.isvisible = true;
        }

        for (Item i : stuff) {

            i.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return Blocked.blocked[xBlock][yBlock];

        // this could make a better kludge
    }
    
    public static void moveEnemies() throws SlickException{
        for(Maid m : brushes){
            m.move();
         }
        for(Butler b : platters){
            b.move();
        }
    }
    

}
