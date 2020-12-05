import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainPass {

  public static void main(String[] args) {
    PasswordGeneration.setDefaultLookAndFeelDecorated(false);
    PasswordGeneration p = new PasswordGeneration();
    p.setVisible(true);

    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (IllegalAccessException | InstantiationException
        | UnsupportedLookAndFeelException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
