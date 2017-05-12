/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.ground;

import com.apcs.grassland.Main;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

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
        Mesh mesh = new Mesh();
        float chunkSize = this.getChunkSize();

        //Follows tutorial found at https://jmonkeyengine.github.io/wiki/jme3/advanced/custom_meshes.html
        Vector3f[] vertices = new Vector3f[4];
        vertices[0] = new Vector3f(0, 0, 0);
        vertices[1] = new Vector3f(chunkSize, 0, 0);
        vertices[2] = new Vector3f(0, 0, chunkSize);
        vertices[3] = new Vector3f(chunkSize, 0, chunkSize);

        Vector2f[] texCoord = new Vector2f[4];
        texCoord[0] = new Vector2f(0, 0);
        texCoord[1] = new Vector2f(1, 0);
        texCoord[2] = new Vector2f(0, 1);
        texCoord[3] = new Vector2f(1, 1);

        int[] indexes = {2, 3, 1, 1, 0, 2};//2, 0, 1, 1, 3, 2

        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        mesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        mesh.updateBound();

        Geometry geo = new Geometry("DynamicMeshTurrain[" + x + "," + y + "]", mesh); // using our custom mesh object
        Material mat = new Material(this.getGameInstance().getAssetManager(),
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geo.setMaterial(mat);
        return geo;
    }

}
