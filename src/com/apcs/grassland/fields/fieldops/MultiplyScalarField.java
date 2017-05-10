/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fields.fieldops;

import com.apcs.grassland.ScalarField;
import com.apcs.grassland.fields.ConstantScalarField;

/**
 *
 * @author Alan
 */
public class MultiplyScalarField extends ScalarField {

    private ScalarField a;
    private ScalarField b;

    public MultiplyScalarField(ScalarField fa, ScalarField fb) {
        a = fa;
        b = fb;
    }

    public MultiplyScalarField(ScalarField fa, float fb) {
        a = fa;
        b = new ConstantScalarField(fb);
    }

    @Override
    public float getValue(float x, float y, float z) {
        return a.getValue(x, y, z) * b.getValue(x, y, z);
    }

    @Override
    public float[] getValueRange() {
        float[] va = a.getCorrectedValueRange();
        float[] vb = b.getCorrectedValueRange();
        if (va[0] == 0 && vb[0] < 0) {
            va[0] = 1;
        }
        return new float[]{va[0] * vb[0], va[1] * vb[1]};
    }

}
