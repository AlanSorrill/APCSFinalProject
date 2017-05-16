/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.ground;

import com.apcs.grassland.Main;
import com.apcs.grassland.voxel.Block;
import com.apcs.grassland.voxel.BlockManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 *
 * @author Alan
 */
public class DynamicMeshTurrain extends DynamicTurrain {
    private Spatial[][][] chunk = new Spatial[16][16][512];

    public DynamicMeshTurrain(Main game) {
        super(game);

        for (Spatial[][] i: chunk)
            for (Spatial[] j: i)
                for (Spatial block: j)
                    block = BlockManager.createCube("rockmat01.j3m", game.getAssetManager());
    }

    @Override
    public Spatial getChunkSpatial(int x, int y) {
        Node node = new Node("chunk(" + x + "," + y + ")");
        
        for (int i = 0; i < chunk.length; i++)
            for (int j = 0; j < chunk[i].length; j++)
                for (int h = 0; h < chunk[i][j].length; h++) {
                    Spatial block = chunk[i][j][h];
                    node.attachChild(block);
                    block.move(i, j, h);
                }
        
        float size = this.getChunkSize();
        node.move(x * size, 0, y * size);
        
        return node;
    }
}
