package com.apcs.grassland.voxel;

import com.jme3.math.Vector4f;
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
    
    private BlockManager() {}; // Singleton
    private static BlockManager instance;
    
    static private void initBlockData() {
        blockData.put("grass", new BlockInfo("grass", "grass.png"));
        blockData.put("rock", new BlockInfo("rock", "rock.png"));
    }

    static BlockManager getInstance() {
        if (instance == null) {
            instance = new BlockManager();
            initBlockData();
        }

        return instance;
    }
}
