/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fields;

import com.apcs.grassland.ScalarField;

/**
 *
 * @author Alan
 */
public class ConstantScalarField extends ScalarField {

    public ConstantScalarField(float val) {
        this.val = val;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConstantScalarField other = (ConstantScalarField) obj;
        if (Float.floatToIntBits(this.val) != Float.floatToIntBits(other.val)) {
            return false;
        }
        return true;
    }

    private float val;

    @Override
    public float getValue(float x, float y, float z) {
        return val;
    }

    @Override
    public float[] getValueRange() {
        return new float[]{0-val, val};

    }

}
