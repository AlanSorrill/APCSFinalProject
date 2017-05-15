package com.apcs.grassland.voxel;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;
import java.util.HashMap;

class BlockInfo {

    protected String blockName;
    protected String texture;

    public BlockInfo(String blockName, String texture) {
        this.blockName = blockName;
        this.texture = texture;
    }
}

public class BlockManager {

    private static HashMap<String, BlockInfo> blockData;

    private BlockManager() {
    }
    ; // Singleton
    private static BlockManager instance;

    static private void initBlockData() {
        blockData.put("grass", new BlockInfo("grass", "grass01.png"));
        blockData.put("rock", new BlockInfo("rock", "rock.png"));
    }

    static BlockManager getInstance() {
        if (instance == null) {
            instance = new BlockManager();
            initBlockData();
        }

        return instance;
    }

    public static Spatial createCube(String material, AssetManager manager) {
        Spatial cube = manager.loadModel("Models/block.j3o");
        TangentBinormalGenerator.generate(cube);
        Material mat = manager.loadMaterial("Materials/"+material);
        cube.setMaterial(mat);

        return cube;
    }
}
