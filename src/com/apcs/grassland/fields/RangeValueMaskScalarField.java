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
public class RangeValueMaskScalarField extends ConditionMaskScalarField {

    private float low = 0;
    private float high = 0;

    public RangeValueMaskScalarField(ScalarField input, float low, float high) {
        super(input);
        this.low = low;
        this.high = high;
    }

    @Override
    public boolean includeValue(float f) {
        return f >= low && f <= high;
    }

}
