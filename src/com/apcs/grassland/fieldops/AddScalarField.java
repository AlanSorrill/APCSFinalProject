/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fieldops;

import com.apcs.grassland.ScalarField;

/**
 *
 * @author Alan
 */
public class AddScalarField extends ScalarField {

    private ScalarField a;
    private ScalarField b;

    public AddScalarField(ScalarField fa, ScalarField fb) {
        a = fa;
        b = fb;
    }

    @Override
    public float getValue(float x, float y, float z) {
        return a.getValue(x, y, z) + b.getValue(x, y, z);
    }

}
