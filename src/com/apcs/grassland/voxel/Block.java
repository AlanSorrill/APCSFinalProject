/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.voxel;

import com.jme3.scene.Spatial;

/**
 *
 * @author PhillipKobylinski
 */
public abstract class Block extends Spatial {
    private int blockId;
    
    public Block(int blockId) {
        this.blockId = blockId;
    }
    
    public int getBlockId(){
        return blockId;
    }
}
