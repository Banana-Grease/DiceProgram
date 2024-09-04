package me.oscarcusick;

import me.oscarcusick.Engine.Elements.VisualElements.TextBox;
import me.oscarcusick.Engine.Math.Vector2;
import me.oscarcusick.Engine.Timing;
import me.oscarcusick.Engine.UserInput.InteractionHandler;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JComponent {

    static final int ScreenX = 0, ScreenY = 1; // definition of element iterator thing (Better to use enum but whatever)
    int[] ScreenDimensions = new int[2]; // to store size of window when initializing and for calculations

    InteractionHandler IH;
    Timing Time;

    // constructor
    public Canvas(int WindowSizeX, int WindowSizeY, InteractionHandler IH) {
        ScreenDimensions[ScreenX] = WindowSizeX;
        ScreenDimensions[ScreenY] = WindowSizeY;

        this.IH = IH;
        this.Time = new Timing(60);

    }

    public void paint(Graphics g) { // main paint loop
        // Graphics Interface Setup
        Graphics2D g2 = (Graphics2D) g; // extend Graphics to Graphics2D to enable more methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Time.Update();

        // --- Start Render Cycle

        // Draw Background


        g2.setColor(Color.black);
        TextBox FPSCounter = new TextBox(g2, new Vector2<Integer>(10, ScreenDimensions[ScreenY] - 75), new Vector2<Integer>(150, 25));
        FPSCounter.SetTextWrap(false);
        FPSCounter.SetTextContent("FPS: " + (int)Time.GetRealFPS());
        FPSCounter.SetFont(new Font("Cascadia Code Regular", Font.BOLD, 15));
        FPSCounter.SetDrawCenteringType(TextBox.CenteringTypes.Center);
        FPSCounter.SetDrawBoundingBox(true);
        FPSCounter.Draw();

        // done with this draw cycle
        repaint(); // re-draw
    }

}
