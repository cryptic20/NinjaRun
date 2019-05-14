package userinterface;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

  public static final int SCREEN_WIDTH = 900;
  public static final int SCREEN_HEIGHT = 400;
  private GameScreen gameScreen;

  public GameWindow() {
    super("Run Ninja Run");
    setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    setLocation(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    gameScreen = new GameScreen();
    addKeyListener(gameScreen);
    add(gameScreen);
  }

  public void startGame() {
    setVisible(true);
    gameScreen.startGame();
  }

  public static void main(String args[]) {
    (new GameWindow()).startGame();
  }
}
