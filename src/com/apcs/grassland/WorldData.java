/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland;

import com.apcs.grassland.fields.PerlinNoiseScalarField;
import com.apcs.grassland.fields.RangeValueMaskScalarField;
import com.apcs.grassland.fields.fieldops.AddScalarField;
import com.apcs.grassland.fields.fieldops.MultiplyScalarField;
import com.apcs.grassland.unittests.ScalarFieldVisualization;
import com.flowpowered.noise.module.source.Perlin;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alan
 */
public class WorldData {

    private static WorldData singlton;

    public static WorldData initWorld(boolean load) { //A0
        if (singlton == null) {
            singlton = new WorldData(load);
        }
        return singlton;
    }

    public static WorldData getWorldData() {
        if (singlton == null) {
            initWorld(false);
        }

        return singlton;
    }

    public AddScalarField getHeightMap() {
        return heightmap;
    }
    private AddScalarField heightmap;
    private PerlinNoiseScalarField heightNoiseBase;
    private int seed = 0;
    private ArrayList<BiomData> bioms = new ArrayList();
    private static String[] biomsToLoad = new String[]{"field", "ocean", "mountian"};

    public ScalarField getBiomField() {
        PerlinNoiseScalarField vf = new PerlinNoiseScalarField();
        vf.setPeriod(0.005f);
        Perlin noise = vf.getPerlin();
        noise.setOctaveCount(1);
        noise.setPersistence(1 / 4);
        noise.setFrequency(1);
        return vf;
    }
    //If you want to be able to access a field that is inside of a function or private, place it in this hashmap, and pull it from the visualization tool
    public HashMap<String, ScalarField> exportedFields = new HashMap();

    private WorldData(boolean loadFromFile) {
        bioms.clear();
        for (String s : biomsToLoad) {
            System.out.println("Loading " + s);
            bioms.add(new BiomData(s));
        }
        //Create base noise in turrain
        heightNoiseBase = new PerlinNoiseScalarField();
        heightNoiseBase.setPeriod(0.01f);
        heightNoiseBase.getPerlin().setSeed(seed + 1);

        //create place to add up all heightmaps from all of the bioms
        ArrayList<ScalarField> heightFields = new ArrayList();

        //load in the biom field
        ScalarField biomField = getBiomField();
        ScalarField mask;
        ScalarField coeff;
        float vcount = biomField.getValueRange()[1] / bioms.size(); //the size of the target value for any given biom
        float vlow;
        float vhigh;

        //loop through list of bioms and add up theyr heightmaps
        for (int i = 0; i < bioms.size(); i++) {
            vlow = vcount * i;
            vhigh = vcount * (i + 1);

            System.out.println("Masking between " + vlow + " and " + vhigh);
            mask = new RangeValueMaskScalarField(biomField, vlow, vhigh);

            //this.exportedFields.put("mask" + i, mask);
            coeff = new MultiplyScalarField(heightNoiseBase, bioms.get(i).getHeightCoefficient());

            heightFields.add(new MultiplyScalarField(mask, coeff));
        }

        heightmap = new AddScalarField(heightFields);
    }

    //shortcut so you dont have to keep changing into the visualizer file to test your fields
    public static void main(String args[]) {
        ScalarFieldVisualization.main(args);
    }

}
