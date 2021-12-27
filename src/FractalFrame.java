import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FractalFrame extends JFrame implements ActionListener {

    //Menu bar
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fractalMenu = new JMenu("Choose Fractals");
    private JMenuItem[] fractalArray = new JMenuItem[4];

    //Panel
    private JPanel controlJPanel = new JPanel();
    private FractalJPanel fractalJPanel = new FractalJPanel(0);

    //Buttons and labels for the control panel
    private JButton changeColorJButton = new JButton("Color");
    private JButton increaseLevelJButton = new JButton("Increase Level");
    private JButton decreaseLevelJButton = new JButton("Decrease Level");
    private JLabel levelJLabel = new JLabel("Level 0");

    private static final int MIN_LEVEL = 0, MAX_LEVEL = 30;

    public static String fractalType = "";

    public FractalFrame() {
        this.setLayout(new FlowLayout());
        setTitle("The One and Only Siddhesh Fractal App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 680);

        fractalArray[0] = new JMenuItem("Lo Fractal");
        fractalArray[1] = new JMenuItem("Lo Star Fractal");
        fractalArray[2] = new JMenuItem("Dragon Curve");
        fractalArray[3] = new JMenuItem("Koch Snowflake");

        for (JMenuItem item: fractalArray) {
            fractalMenu.add(item);
            item.addActionListener(this);
        }

        menuBar.add(fractalMenu);
        setJMenuBar(menuBar);

        setupControlButtons();

        controlJPanel.add(levelJLabel);

        add(controlJPanel);
        add(fractalJPanel);

        setVisible(true);
    }

    private void setupControlButtons() {

        //Set up the button to manage the color chooser
        controlJPanel.add(changeColorJButton);
        changeColorJButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        Color color = JColorChooser.showDialog(FractalFrame.this, "Choose a color", Color.BLUE);

                        if (color == null)
                            color = Color.blue;

                        fractalJPanel.setColor(color);
                    }
                }
        );

        //Set up the button to manage the level decrease
        controlJPanel.add(decreaseLevelJButton);
        decreaseLevelJButton.addActionListener(
                event -> {
                    int level = fractalJPanel.getLevel();
                    --level;

                    if (level >= MIN_LEVEL && level <= MAX_LEVEL) {
                        levelJLabel.setText("Level: " + level);
                        fractalJPanel.setLevel(level);
                        repaint();
                    }
                }
        );

        // Set up button to manage the level increase
        controlJPanel.add(increaseLevelJButton);
        increaseLevelJButton.addActionListener(
                event -> {
                    int level = fractalJPanel.getLevel();
                    level++;

                    if (level >= MIN_LEVEL && level <= MAX_LEVEL) {
                        levelJLabel.setText("Level: " + level);
                        fractalJPanel.setLevel(level);
                        repaint();
                    }

                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JMenuItem menuItem) {
            fractalType = menuItem.getText();
            levelJLabel.setText("Level: 0");
            fractalJPanel.setLevel(0);
            repaint();
        }
    }
}
