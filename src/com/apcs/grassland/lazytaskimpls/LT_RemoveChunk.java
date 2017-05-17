/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.lazytaskimpls;

import com.apcs.grassland.LazyTask;
import com.apcs.grassland.Main;
import com.apcs.grassland.Vector2i;
import com.apcs.grassland.ground.DynamicTurrain;
import com.jme3.scene.Spatial;

/**
 *
 * @author alans
 */
public class LT_RemoveChunk extends LazyTask {

    private Vector2i loc;
    private DynamicTurrain turrain;
    private Spatial chunk;

    public LT_RemoveChunk(Vector2i i, DynamicTurrain t) {
        turrain = t;
        loc = i;
    }

    @Override
    public void lazyExecute(Main m) {

        chunk = turrain.chunks.get(loc);
        chunk.removeFromParent();
        turrain.chunks.remove(chunk);
        System.out.println("Removed chunk " + loc.getX() + ", " + loc.getY());
        turrain.setChunkState(loc, DynamicTurrain.CHUNK_UNLOADED);
    }

}
