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
public class LT_AddChunk extends LazyTask {

    private Vector2i loc;
    private DynamicTurrain turrain;

    public LT_AddChunk(Vector2i l, DynamicTurrain t) {
        this.loc = l;
        turrain = t;
        t.setChunkState(loc, DynamicTurrain.CHUNK_LOADING);
    }


    @Override
    public void lazyExecute(Main m) {
        Spatial n = turrain.getChunkSpatial(loc.getX(),loc.getY());
        //n.setUserData("loc", loc);
        turrain.chunks.put(loc, n);
        turrain.getBaseNode().attachChild(n);
        System.out.println("Attaching chunk " + loc.getX() + ", " + loc.getY());
        n.setLocalTranslation(loc.getX() * turrain.getChunkSize(), 0, loc.getY() * turrain.getChunkSize());
        turrain.setChunkState(loc, DynamicTurrain.CHUNK_LOADED);
    }

}
