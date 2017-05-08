/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.unittests;

import com.apcs.grassland.ScalarField;
import com.apcs.grassland.fields.VoronoiNoiseScalarField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
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
        VoronoiNoiseScalarField p = new VoronoiNoiseScalarField();
        p.setPeriod(0.01f);
        return p;
    }
    
    //The netbeans shortcut to run just this file is {Shift f6}
    public static void main(String[] args) {
        new ScalarFieldVisualization().setVisible(true);
    }
    
    
    
    
    
    
    

    /**
     * Sets up window
     */
    public ScalarFieldVisualization() {
        setSize(500, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setTitle("Scalar field test viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        ScalarField field = setupField();
        float val;
        int col;
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                //sample field
                val = field.getValue(x, y);
                //translate 0-1 range into color value range
                col = (int) (val * 255);
                col = Math.min(col, 255);
                col = Math.max(col, 0);

                g.setColor(new Color(col, col, col));
                g.drawLine(x, y, x, y);
            }
        }
    }

    
}
