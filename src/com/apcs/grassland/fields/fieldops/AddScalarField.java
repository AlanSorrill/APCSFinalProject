/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fields.fieldops;

import com.apcs.grassland.ScalarField;
import java.util.ArrayList;

/**
 *
 * @author Alan
 */
public class AddScalarField extends ScalarField {

    private ArrayList<ScalarField> fields = new ArrayList();

    public AddScalarField(ScalarField fa, ScalarField fb) {
        fields.add(fa);
        fields.add(fb);
    }

    public AddScalarField(ArrayList<ScalarField> fields) {
        this.fields.addAll(fields);
    }

    @Override
    public float getValue(float x, float y, float z) {
        float total = 0;
        for (ScalarField f : fields) {
            total += f.getValue(x, y, z);
        }
        return total;
    }

    @Override
    public float[] getValueRange() {
        float totalMax = 0;
        float totalMin = 0;
        float[] vr;
        for (ScalarField f : fields) {
            vr = f.getCorrectedValueRange();
            System.out.println("Adding value range " + vr[0] + ", " + vr[1]);
            totalMax+=vr[1];
            totalMin+=vr[0];
        }
        return new float[]{totalMin, totalMax};
    }

}
