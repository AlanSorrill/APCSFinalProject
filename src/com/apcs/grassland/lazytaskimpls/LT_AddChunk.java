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
public class LT_AddChunk extends LazyTask {

    private int x;
    private int y;
    private DynamicTurrain turrain;

    public LT_AddChunk(int x, int y, DynamicTurrain t) {
        this.x = x;
        this.y = y;
        turrain = t;
        t.setChunkState(x, y, DynamicTurrain.CHUNK_LOADING);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void lazyExecute(Main m) {
        Spatial n = turrain.getChunkSpatial(x, y);
        n.setUserData("xCord", x);
        n.setUserData("yCoord", y);
        turrain.chunks.put(new Integer[]{x, y}, n);
        turrain.getBaseNode().attachChild(n);
        System.out.println("Attaching chunk " + x + ", " + y);
        n.setLocalTranslation(x * turrain.getChunkSize(), 0, y * turrain.getChunkSize());
        turrain.setChunkState(x, y, DynamicTurrain.CHUNK_LOADED);
    }

}
