package gameobject;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import userinterface.GameWindow;
import util.Animation;
import util.Resource;

public class MainCharacter {

  public static final int LAND_POSY = GameWindow.SCREEN_HEIGHT - 110;
  public static final float GRAVITY = 0.4f;
  private static final int NORMAL_RUN = 0;
  private static final int JUMPING = 1;
  private static final int DOWN_RUN = 2;
  private static final int DEATH = 3;

  // sprite image size
  private static final int CWidth = 50;
  private static final int CHeight = 60;
  private static final int slideW = 60;
  private static final int slideH = 35;

  private double posY;
  private double posX;
  private double speedX;
  private double speedY;
  private Rectangle rectBound;

  public int score = 0;

  private int state = NORMAL_RUN;

  private Animation normalRunAnim;
  private Animation jumping;
  private Animation downRunAnim;
  private Animation deathImage;
  private AudioClip jumpSound;
  private AudioClip deadSound;
  private AudioClip scoreUpSound;

  public MainCharacter() {
    posX = 50;
    posY = GameWindow.SCREEN_HEIGHT - 140;
    rectBound = new Rectangle();
    normalRunAnim = new Animation();
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__001.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__002.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__003.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__004.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__005.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__006.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__007.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__008.png"), CWidth,
        CHeight);
    normalRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Run__009.png"), CWidth,
        CHeight);
    jumping = new Animation();

    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__000.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__001.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__002.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__003.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__004.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__005.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__006.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__007.png"), CWidth,
        CHeight);
    jumping.addFrame(Resource.getResouceImage("data/player_sprites/Jump__008.png"), CWidth,
        CHeight);

    downRunAnim = new Animation();
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__000.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__001.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__002.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__003.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__004.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__005.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__006.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__007.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__008.png"), slideW,
        slideH);
    downRunAnim.addFrame(Resource.getResouceImage("data/player_sprites/Slide__009.png"), slideW,
        slideH);

    deathImage = new Animation();
    deathImage.setAnimSpeed(5);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__000.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__001.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__002.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__003.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__004.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__005.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__006.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__007.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__008.png"), CWidth,
        CHeight);
    deathImage.addFrame(Resource.getResouceImage("data/player_sprites/Dead__009.png"), CWidth,
        CHeight);


    try {
      jumpSound = Applet.newAudioClip(new URL("file", "", "data/jump.wav"));
      deadSound = Applet.newAudioClip(new URL("file", "", "data/dead.wav"));
      scoreUpSound = Applet.newAudioClip(new URL("file", "", "data/scoreup.wav"));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  public double getSpeedX() {
    return speedX;
  }

  public void setSpeedX(double speedX) {
    this.speedX = speedX;
  }

  public void draw(Graphics g) {
    switch (state) {
      case NORMAL_RUN:
        g.drawImage(normalRunAnim.getFrame(), (int) posX, (int) posY, null);
        break;
      case JUMPING:
        g.drawImage(jumping.getFrame(), (int) posX, (int) posY, null);
        break;
      case DOWN_RUN:
        g.drawImage(downRunAnim.getFrame(), (int) posX, (int) (posY + 20), null);
        break;
      case DEATH:
        g.drawImage(deathImage.getFrame(), (int) posX, (int) posY, null);
        break;
    }
    // Rectangle bound = getBound();
    // g.setColor(Color.RED);
    // g.drawRect(bound.x, bound.y, bound.width, bound.height);
  }

  public void update() {
    normalRunAnim.updateFrame();
    jumping.updateFrame();
    downRunAnim.updateFrame();
    deathImage.updateFrame();
    if (posY >= LAND_POSY) {
      posY = LAND_POSY;
      if (state != DOWN_RUN) {
        state = NORMAL_RUN;
      }
    } else {
      speedY += GRAVITY;
      posY += speedY;
    }

    switch (score) {
      case 100:
        setSpeedX(5);
        normalRunAnim.setAnimSpeed(60);
        break;
      case 200:
        setSpeedX(7);
        normalRunAnim.setAnimSpeed(40);
        break;
      case 300:
        normalRunAnim.setAnimSpeed(20);
        setSpeedX(9);
        break;
      case 400:
        // max speed
        normalRunAnim.setAnimSpeed(5);
        setSpeedX(12);
      default:
        break;
    }
  }

  public void jump() {
    if (posY >= LAND_POSY) {
      if (jumpSound != null) {
        jumpSound.play();
      }
      speedY = -7.5f;
      posY += speedY;
      state = JUMPING;
    }
  }

  public void down(boolean isDown) {
    if (state == JUMPING) {
      return;
    }
    if (isDown) {
      state = DOWN_RUN;
    } else {
      state = NORMAL_RUN;
    }
  }

  public Rectangle getBound() {
    rectBound = new Rectangle();
    if (state == DOWN_RUN) {
      rectBound.x = (int) posX + 5;
      rectBound.y = (int) posY + 20;
      rectBound.width = downRunAnim.getFrame().getWidth() - 10;
      rectBound.height = downRunAnim.getFrame().getHeight();
    } else {
      rectBound.x = (int) posX + 5;
      rectBound.y = (int) posY;
      rectBound.width = normalRunAnim.getFrame().getWidth() - 10;
      rectBound.height = normalRunAnim.getFrame().getHeight();
    }
    return rectBound;
  }

  public void dead(boolean isDeath) {
    if (isDeath) {
      state = DEATH;
    } else {
      state = NORMAL_RUN;
    }
  }

  public void reset() {
    posY = LAND_POSY;
    score = 0;
    // default speed
    setSpeedX(4);
    normalRunAnim.setAnimSpeed(90);
    downRunAnim.setAnimSpeed(90);
    jumping.setAnimSpeed(90);
    deathImage.setAnimSpeed(90);
  }

  public void playDeadSound() {
    deadSound.play();
  }

  public void upScore() {
    score += 50;
    if (score % 100 == 0) {
      scoreUpSound.play();
    }
  }

}
