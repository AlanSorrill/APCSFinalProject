/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.fields;

import com.flowpowered.noise.module.Module;
import com.flowpowered.noise.module.source.Voronoi;

/**
 *
 * @author Alan
 */
public class VoronoiNoiseScalarField extends NoiseScalarField {

    @Override
    protected Module getModule() {
        return getVoronoi();
    }
    private Voronoi voronoi;

    public Voronoi getVoronoi() {
        if (voronoi == null) {
            voronoi = new Voronoi();
        }
        return voronoi;
    }

    

}
