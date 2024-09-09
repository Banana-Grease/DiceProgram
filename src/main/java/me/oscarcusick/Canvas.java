package me.oscarcusick;

import me.oscarcusick.Engine.Elements.VisualElements.TextBox;
import me.oscarcusick.Engine.Math.RandomGenerator;
import me.oscarcusick.Engine.Math.Vector2;
import me.oscarcusick.Engine.Timing;
import me.oscarcusick.Engine.UserInput.InteractionHandler;

import javax.swing.*;
import javax.swing.text.Highlighter;
import java.awt.*;

public class Canvas extends JComponent {

    static final int ScreenX = 0, ScreenY = 1; // definition of element iterator thing (Better to use enum but whatever)
    int[] ScreenDimensions = new int[2]; // to store size of window when initializing and for calculations

    InteractionHandler IH;
    Timing Time = new Timing(60);

    RandomGenerator Rand = new RandomGenerator();

    String DrawContent = "Standard Dice Roll Result: " + Rand.RollAStandardDice();

    // Sketch way to draw a new number every x seconds
    double NanoSinceLastRandDraw = 0;

    // constructor
    public Canvas(int WindowSizeX, int WindowSizeY, InteractionHandler IH) {
        ScreenDimensions[ScreenX] = WindowSizeX;
        ScreenDimensions[ScreenY] = WindowSizeY;

        this.IH = IH;
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

        TextBox RandomNumber = new TextBox(g2, new Vector2<>(50, 50), new Vector2<>(500, 40));
        RandomNumber.SetDrawCenteringType(TextBox.CenteringTypes.Center);
        RandomNumber.SetDrawBoundingBox(true);

        if ( (Time.ConvertNanoSeconds(System.nanoTime(), Timing.TimeUnits.S) - Time.ConvertNanoSeconds(NanoSinceLastRandDraw, Timing.TimeUnits.S)) > .5f ) {
            DrawContent = "Standard Dice Roll Result: " + Rand.RollAStandardDice();
            RandomNumber.SetTextContent(DrawContent);
            NanoSinceLastRandDraw = System.nanoTime();
        } else {
            RandomNumber.SetTextContent(DrawContent);
        }

        g2.setColor(Color.black);
        RandomNumber.Draw();

        // done with this draw cycle
        repaint(); // re-draw
    }

}