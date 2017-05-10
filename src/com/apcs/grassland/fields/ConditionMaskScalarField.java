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
public abstract class ConditionMaskScalarField extends ScalarField {

    public ConditionMaskScalarField(ScalarField in) {
        input = in;
    }
    protected ScalarField input;

    public abstract boolean includeValue(float f);

    @Override
    public float getValue(float x, float y, float z) {
        return includeValue(input.getValue(x, y, z)) ? 1 : 0;
    }

}
