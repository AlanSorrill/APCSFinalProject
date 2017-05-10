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
public class SingleValueMaskScalarField extends ConditionMaskScalarField {

    private float target = 0;

    public SingleValueMaskScalarField(ScalarField input, float target) {
        super(input);
        this.target = target;
    }

    @Override
    public boolean includeValue(float f) {
        return (f == target);
    }

}
