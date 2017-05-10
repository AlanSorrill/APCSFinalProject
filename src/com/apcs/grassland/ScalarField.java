/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland;

/**
 *
 * @author Alan
 */
public abstract class ScalarField {

    public float getValue(float x, float y) {
        return getValue(x, y, 0);
    }

    public abstract float getValue(float x, float y, float z);

    public float[] getCorrectedValueRange() {
        float[] vr = getValueRange();
        return new float[]{Math.min(vr[0], vr[1]), Math.max(vr[0], vr[1])};
    }

    public float[] getValueRange() {
        return new float[]{0f, 1f};
    }
}
