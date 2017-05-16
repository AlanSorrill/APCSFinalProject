package com.apcs.grassland.voxel;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;

public class BlockManager {

    private BlockManager() {}; // Singleton
    private static BlockManager instance;

    static BlockManager getInstance() {
        if (instance == null)
            instance = new BlockManager();

        return instance;
    }

    public static Spatial createCube(String material, AssetManager manager) {
        Spatial cube = manager.loadModel("Models/block.j3o");
        TangentBinormalGenerator.generate(cube);
        Material mat = manager.loadMaterial("Materials/" + material);
        cube.setMaterial(mat);

        return cube;
    }
}
