import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.lang.Math;

//This class is used to display a selected fractal using a recursive algorithm
//Each fractal starts with unique values that define its initial rendering
//and then according to the depth level adjusts the rendering recursively
public class FractalJPanel extends JPanel{

    private Color color;

    private int level;

    public FractalJPanel(int currentLevel) {
        // initial values for the color and level
        color = Color.BLUE;
        level = currentLevel;

        setBackground(Color.pink);
        setPreferredSize(new Dimension(800, 600));

    }
    //Lo Fractal
    public void drawLoFractal (int level, int xA, int yA, int xB, int yB, Graphics g) {
        // Base case
        if (level == 0) {
            g.drawLine(xA, yA, xB, yB);
        }
        else {
            // Calculate midpoint of line.
            int xC = (xA + xB) / 2;
            int yC = (yA + yB) / 2;
            // Calculate point D left of AC.
            int xD = xA + (xC - xA) / 2 - (yC - yA) / 2;
            int yD = yA + (yC - yA) / 2 + (xC - xA) / 2;
            // Draw lines
            drawLoFractal(level - 1, xD, yD, xA, yA, g);
            drawLoFractal(level - 1, xD, yD, xC, yC, g);
            drawLoFractal(level - 1, xD, yD, xB, yB, g);
        }
    }

    //Dragon Curve
    public void drawDragonCurveFractal (int level, int x1, int y1, int x2, int y2, Graphics g) {
        // Base case
        if (level == 0) {
            g.drawLine(x1, y1, x2, y2);
        }

        //Recursive call
        else {
            int xn = (x1 + x2) / 2 + (y2 - y1) / 2;
            int yn = (y1 + y2) / 2 - (x2 - x1) / 2;

            //
            drawDragonCurveFractal(level - 1, x1, y1, xn, yn, g);
            drawDragonCurveFractal(level - 1, x2, y2, xn, yn, g);
        }
    }

    // Lo Star Fractal
    public void drawLoStarFractal (int level, int midPointX, int midPointY, Graphics g) {
        // Sides
        drawLoFractal(level, midPointX, midPointY, midPointX + 250, midPointY, g);
        drawLoFractal(level, midPointX, midPointY, midPointX - 250, midPointY, g);

        // Top
        int tempValue = (int)(250 * Math.cos(Math.toRadians(45)));
        drawLoFractal(level, midPointX, midPointY, midPointX + tempValue, midPointY - tempValue, g);
        drawLoFractal(level, midPointX, midPointY, midPointX, midPointY - 250, g);
        drawLoFractal(level, midPointX, midPointY, midPointX - tempValue, midPointY - tempValue, g);

        // Bottom
        drawLoFractal(level, midPointX, midPointY, midPointX + tempValue, midPointY + tempValue, g);
        drawLoFractal(level, midPointX, midPointY, midPointX, midPointY + 250, g);
        drawLoFractal(level, midPointX, midPointY, midPointX - tempValue, midPointY + tempValue, g);
    }

    public void drawKochSnowflake(int level, double x1, double y1, double xF, double yF, Graphics g) {
        if (level == 0) {
            g.drawLine((int)x1, (int)y1, (int)xF, (int)yF);
        }
        else {
            double deltaX, deltaY, x2, x3, x4, y2, y3, y4;

            deltaX = xF - x1;
            deltaY = yF - y1;

            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            x3 = (x1 + xF) / 2 + (Math.sqrt(3) / 6) * (y1 - yF);
            y3 = (y1 + yF) / 2 + (Math.sqrt(3) / 6) * (xF - x1);

            x4 = x1 + deltaX * 2/3;
            y4 = y1 + deltaY * 2/3;

            drawKochSnowflake(level - 1, x1, y1, x2, y2, g);
            drawKochSnowflake(level - 1, x2, y2, x3, y3, g);
            drawKochSnowflake(level - 1, x3, y3, x4, y4, g);
            drawKochSnowflake(level - 1, x4, y4, xF, yF, g);

        }

    }

    //This method manages the drawing of each fractal based on what is chosen
    public void paintComponent(Graphics g) {
        //calls the super classes paintComponent method first
        super.paintComponent(g);

        //Set the rendering color
        g.setColor(color);

        //Depending on which fractal type is selected
        switch (FractalFrame.fractalType) {
            case "Lo Fractal" -> drawLoFractal(level, 200, 400, 600, 400, g);
            case "Lo Star Fractal" -> drawLoStarFractal(level, 400, 300, g);
            case "Dragon Curve" -> drawDragonCurveFractal(level, 200, 400, 600, 400, g);
            case "Koch Snowflake" -> {
                drawKochSnowflake(level, 350, 34, 150, 467, g);
                drawKochSnowflake(level, 150, 467, 550, 467, g);
                drawKochSnowflake(level, 550, 467, 350, 34, g);
            }
        }
    }

    public void setColor (Color c) {
        color = c;
    }

    public void setLevel (int currentLevel) {
        level = currentLevel;
    }

    public int getLevel () {
        return level;
    }

}
