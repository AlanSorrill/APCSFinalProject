/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.voxel;

/**
 *
 * @author PhillipKobylinski
 */
public class Block {
    // All blocks are just an id number in memory, as having many blocks can add up
    private int blockId;
    
    public Block(int blockId) {
        this.blockId = blockId;
    }
    
    public int getBlockId(){
        return blockId;
    }
}
