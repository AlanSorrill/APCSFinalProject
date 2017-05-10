/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.unittests;

import com.apcs.grassland.ScalarField;
import com.apcs.grassland.WorldData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 *
 * @author Alan
 */
public class ScalarFieldVisualization extends JFrame {

    /**
     * feel free to modify this method to test out any scalar field / scalar
     * field settings you would like to visualize
     *
     * @return scalar field to be drawn
     */
    public ScalarField setupField() {
        //PerlinNoiseScalarField p = new PerlinNoiseScalarField();
        //VoronoiNoiseScalarField p = new VoronoiNoiseScalarField();
        //p.setPeriod(0.01f);
        WorldData d = WorldData.initWorld(false);
        return d.getHeightMap();
    }

    //The netbeans shortcut to run just this file is {Shift f6}
    public static void main(String[] args) {
        new ScalarFieldVisualization().setVisible(true);
    }

    /**
     * Sets up window
     */
    public ScalarFieldVisualization() {
        setSize(800, 800);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setTitle("Scalar field test viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        field = setupField();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                setTitle("Scalar field test viewer " + field.getValue(me.getX(), me.getY()));
            }
        });
    }
    private ScalarField field;

    @Override
    public void paint(Graphics g) {

        float val;
        int col;
        float[] fRange = field.getValueRange();
        System.out.println("SFV fRange: " + Arrays.toString(fRange));
        boolean oceanLevelColorSwitch = false;
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                //sample field
                val = field.getValue(x, y);
                //move value into 0-1 range
                col = Math.round(255 * ((val - fRange[0]) / (fRange[1] - fRange[0])));
                //System.out.println(col);
                if (col <= 255 && col >= 0) {
                    if (oceanLevelColorSwitch) {
                        g.setColor((field.getValue(x, y) >= 0) ? new Color(0, col, 0) : new Color(0, 0, col));
                    } else {
                        g.setColor(new Color(col, col, col));
                    }
                } else if (col > 255) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.yellow);
                }
                g.drawLine(x, y, x, y);
            }
        }
    }

}
