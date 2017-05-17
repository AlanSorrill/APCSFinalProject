/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.ground;

import com.apcs.grassland.Main;
import com.apcs.grassland.WorldData;
import com.apcs.grassland.voxel.BlockManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Alan
 */
public class DynamicMeshTurrain extends DynamicTurrain {

    public DynamicMeshTurrain(Main game) {
        super(game);

    }

    @Override
    public Spatial getChunkSpatial(int x, int y) {
        Spatial[][][] chunk;

        Spatial block;
        chunk = new Spatial[(int) getChunkSize()][(int) getChunkSize()][512];
        float height;
        for (int bx = 0; bx < chunk.length; bx++) {
            for (int by = 0; by < chunk[bx].length; by++) {
                block = BlockManager.createCube("RockMat01.j3m", this.getGameInstance().getAssetManager());
                block.move(bx, 0, by);
                block.setName("Block " + bx + ", " + by);
                
                height = WorldData.getWorldData().getHeightMap().getValue(x*getChunkSize()+bx,y*getChunkSize()+by);
                //height = height*10;
                chunk[bx][by][(int)height] = block;
            }
        }

        Node node = new Node("chunk(" + x + "," + y + ")");

        for (int i = 0; i < chunk.length; i++) {
            for (int j = 0; j < chunk[i].length; j++) {
                for (int h = 0; h < chunk[i][j].length; h++) {
                    block = chunk[i][j][h];
                    if (block != null) {
                        node.attachChild(block);
                        //System.out.println("Attaching " + block.getName() + " to loc (" + i + ", " + h + ", " + j);
                        block.setLocalTranslation(i, h, j);
                    }
                }
            }
        }

        return node;
    }
}
