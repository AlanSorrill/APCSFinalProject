/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fields;

import com.flowpowered.noise.module.Module;
import com.flowpowered.noise.module.source.Perlin;

/**
 *
 * @author Alan
 */
public class PerlinNoiseScalarField extends NoiseScalarField {

    @Override
    protected Module getModule() {
        return getPerlin();
    }
    private Perlin perlin;

    public Perlin getPerlin() {
        if (perlin == null) {
            perlin = new Perlin();
        }
        return perlin;
    }

}
