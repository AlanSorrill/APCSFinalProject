/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fields;

import com.apcs.grassland.ScalarField;
import com.flowpowered.noise.module.Module;

/**
 *
 * @author Alan This is an abstract wraper for noise fields. Both
 * PerlinScalarFields and VoronoiScalarFields extend this class, and allow an
 * easy abstract way to organize noise fields
 */
public abstract class NoiseScalarField extends ScalarField {

    protected abstract Module getModule();
    private float period = 1;

    public float getPeriod() {
        return period;
    }

    public void setPeriod(float period) {
        this.period = period;
    }

    @Override
    public float getValue(float x, float y, float z) {
        return (float) getModule().getValue(period * x, period * y, period * z);
    }

    @Override
    public float[] getValueRange() {
        return new float[]{0, 1.3f}; //To change body of generated methods, choose Tools | Templates.
    }
}
