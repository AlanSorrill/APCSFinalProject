/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fields;

import com.apcs.grassland.ScalarField;
import java.util.HashMap;

/**
 *
 * @author Alan
 */
public class WeightedAverageScalarField extends ScalarField {

    private HashMap<ScalarField, Integer> fields = new HashMap();

    public void addField(ScalarField f, int weight) {
        fields.put(f, weight);
    }

    @Override
    public float getValue(float x, float y, float z) {
        float total = 0;
        int weight;
        int wTotal=0;
        for (ScalarField f : fields.keySet()) {
            weight = fields.get(f);
            wTotal+=weight;
            total += weight*f.getValue(x, y, z);
        }
        return total/wTotal;
    }

}
