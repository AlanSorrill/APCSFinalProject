/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.lazytaskimpls;

import com.apcs.grassland.LazyTask;
import com.apcs.grassland.Main;
import com.apcs.grassland.ground.DynamicTurrain;
import com.jme3.scene.Spatial;
import java.util.Arrays;

/**
 *
 * @author alans
 */
public class LT_RemoveChunk extends LazyTask {

    private int x;
    private int y;
    private DynamicTurrain turrain;
    private Spatial chunk;

    public LT_RemoveChunk(int x, int y, DynamicTurrain t, Spatial chunk) {
        this.x = x;
        this.y = y;
        turrain = t;
    }

    @Override
    public void lazyExecute(Main m) {
        chunk.removeFromParent();
        turrain.chunks.remove(chunk);
        System.out.println("Removed chunk " + x + ", " + y);
        turrain.setChunkState(x, y, DynamicTurrain.CHUNK_UNLOADED);
    }

}
