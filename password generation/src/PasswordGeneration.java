import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PasswordGeneration extends JFrame implements ActionListener {

  private final JSlider passLength;
  private final JTextField passText;
  private final JLabel chooseLength;

  private final JButton genButton;
  private final JButton reset;

  private final JCheckBox lowerCase;
  private final JCheckBox upperCase;
  private final JCheckBox symbols;
  private final JCheckBox numbers;

  private final StringBuilder password;
  private final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String lower = "abcdefghijklmnopqrstuvwxyz";
  private final String num = "1234567890";
  private final String sym = "~`!@#$%^&*()-_=+[{]}|;:\",<>.?/";
  private final JFrame warningFrame;

  public PasswordGeneration() {
    super();
    this.password = new StringBuilder();
    this.setTitle("Password Generator");
    this.getContentPane().setLayout(null);
    this.setSize(420, 400);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setResizable(false);

    // warning frame
    warningFrame = new JFrame();
    warningFrame.setSize(new Dimension(200, 200));

    // add label choose length
    chooseLength = new JLabel("Choose the length of your password:");
    getContentPane().add(chooseLength);
    chooseLength.setBounds(100, 10, 250, 15);

    // make buttons.
    passLength = new JSlider();
    getContentPane().add(passLength);
    passLength.setSnapToTicks(true);
    passLength.setValue(0);
    passLength.setMaximum(50);
    passLength.setToolTipText("Password length: \n");
    passLength.setBounds(50, 30, 300, 50);
    passLength.setMajorTickSpacing(10);
    passLength.setMinorTickSpacing(1);
    passLength.setPaintTicks(true);
    passLength.setPaintLabels(true);

    // make checkbox
    lowerCase = new JCheckBox("LowerCase");
    getContentPane().add(lowerCase);
    lowerCase.addActionListener(this);
    lowerCase.setActionCommand("lower");
    lowerCase.setBounds(70, 90, 100, 25);

    upperCase = new JCheckBox("UpperCase");
    getContentPane().add(upperCase);
    upperCase.addActionListener(this);
    upperCase.setActionCommand("upper");
    upperCase.setBounds(200, 90, 100, 25);

    numbers = new JCheckBox("Numbers");
    getContentPane().add(numbers);
    numbers.addActionListener(this);
    numbers.setActionCommand("number");
    numbers.setBounds(70, 120, 100, 25);

    symbols = new JCheckBox("Symbols");
    getContentPane().add(symbols);
    symbols.addActionListener(this);
    symbols.setActionCommand("symbols");
    symbols.setBounds(200, 120, 100, 25);

    // generation button
    genButton = new JButton("Generate Password");
    getContentPane().add(genButton);
    genButton.addActionListener(this);
    genButton.setActionCommand("generate");
    genButton.setBounds(100, 150, 200, 25);

    // password field
    passText = new JTextField();
    getContentPane().add(passText);
    passText.setBounds(50, 190, 300, 100);

    // reset button
    reset = new JButton("Reset Password");
    getContentPane().add(reset);
    reset.addActionListener(this);
    reset.setActionCommand("reset");
    reset.setBounds(100, 300, 200, 25);
  }

  /**
   * check if none of check boxes are selected.
   * @return true is none of them are selected.
   */
  private boolean noneSelected() {
    return !lowerCase.isSelected() && !upperCase.isSelected()
        && !numbers.isSelected() && !symbols.isSelected();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Random rand = new Random();
    switch (e.getActionCommand()) {
      case "generate":
        if (this.noneSelected()) {
          JOptionPane.showMessageDialog(warningFrame,
              "Hey! Select the check boxed.",
              "Warning",
              JOptionPane.WARNING_MESSAGE);
          break;
        }
        if (passLength.getValue() <= 6 && passText.getText().equals("")) {
          JOptionPane.showMessageDialog(warningFrame,
              "Hey! Password length is too short\nIt should be greater than 6.",
              "Warning",
              JOptionPane.WARNING_MESSAGE);
          break;
        } else {
          StringBuilder characters = new StringBuilder();
          if (lowerCase.isSelected()) {
            characters.append(lower);
          }
          if (upperCase.isSelected()) {
            characters.append(upper);
          }
          if (numbers.isSelected()) {
            characters.append(num);
          }
          if (symbols.isSelected()) {
            characters.append(sym);
          }
          char[] listOfChar = characters.toString().toCharArray();
          for (int i = 0; i < passLength.getValue(); i++) {
            int indPass = rand.nextInt(listOfChar.length);
            Character passChar = listOfChar[indPass];
            password.append(passChar);
          }
          passText.setText(password.toString());
      }
      break;
      case "reset":
        if (!password.toString().equals("")) {
          password.delete(0, password.length() - 1);
        }
        passText.setText("");
        break;
      default:
        break;
    }
  }
}
