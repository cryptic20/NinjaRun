package util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {

  private List<BufferedImage> list;
  private long deltaTime = 90; // default
  private int currentFrame = 0;
  private long previousTime;

  /**
   * 
   * @param deltaTime Sets the animation speed. Lower the faster
   */
  public Animation() {
    list = new ArrayList<BufferedImage>();
    previousTime = 0;
  }

  public void setAnimSpeed(long speed) {
    deltaTime = speed;
  }

  public void updateFrame() {
    if (System.currentTimeMillis() - previousTime >= deltaTime) {
      currentFrame++;
      if (currentFrame >= list.size()) {
        currentFrame = 0;
      }
      previousTime = System.currentTimeMillis();
    }
  }

  public void addFrame(BufferedImage image, int width, int height) {
    list.add(resize(image, width, height));
  }

  public static BufferedImage resize(BufferedImage img, int newW, int newH) {
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
  }

  public BufferedImage getFrame() {
    return list.get(currentFrame);
  }

}
