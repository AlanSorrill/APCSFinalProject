/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.ground;

import com.apcs.grassland.Main;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan
 */
public abstract class DynamicTurrain {
    protected Main gameInstance;

    public DynamicTurrain(Main game) {
        gameInstance = game;
        init();
    }

    public Main getGameInstance() {
        return gameInstance;
    }

    private void init() {
        FileInputStream fis = null;
        try {
            this.turrainProperties = new Properties();
            fis = new FileInputStream(new File("turrainoptions.ops"));
            turrainProperties.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DynamicTurrain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DynamicTurrain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DynamicTurrain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //----Loaded chunk logic-----------------------------------------------------------------
    public void addLoadedChunk(int x, int y) {
        loadedChunks.add(new Integer[]{x, y});
        updateChunks();
    }

    public void removeLoadedChunk(int x, int y) {
        loadedChunks.remove(getLoadedChunkIndex(x, y));
        updateChunks();
    }

    public boolean isChunkLoaded(int x, int y) {
        return getLoadedChunkIndex(x, y) != -1;
    }

    private int getLoadedChunkIndex(int x, int y) {
        int i = 0;
        for (Integer[] val : loadedChunks) {
            if (val[0] == x && val[1] == y) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void updateChunks() {
        Spatial n;
        float chunkSize = this.getChunkSize();
        for (Integer[] i : loadedChunks) {// add in new chunks
            if (!chunks.containsKey(i)) {
                n = getChunkSpatial(i[0], i[1]);
                chunks.put(i, n);
                baseNode.attachChild(n);
                n.setLocalTranslation(i[0]*chunkSize, 0, i[1]*chunkSize);
            }
        }
        for (Integer[] i : chunks.keySet()) {// remove chunks which have been removed
            if (!loadedChunks.contains(i)) {
                chunks.get(i).removeFromParent();
                loadedChunks.remove(i);
                chunks.remove(i);
            }
        }
    }

    //each chunk identified by int[]{x,y}
    private ArrayList<Integer[]> loadedChunks = new ArrayList();
    private HashMap<Integer[], Spatial> chunks = new HashMap();
    private Node baseNode = new Node();

    public Node getBaseNode() {
        return baseNode;
    }

    //----abstract chunk aquizition----------------------------------------------------------
    /**
     *
     * @param x X chunk coordinate
     * @param y Y chunk coordinate
     * @return a spatial model to be placed at (x*chunkSizeX,y*chunkSizeY)
     */
    public abstract Spatial getChunkSpatial(int x, int y);
    //----Turrain properties-----------------------------------------------------------------
    private Properties turrainProperties;

    public Properties getTurrainProperties() {
        if (turrainProperties == null) {
            init();
        }
        return turrainProperties;
    }

    public float getChunkSize() {
        return Float.parseFloat(getTurrainProperties().getProperty("chunkSize"));
    }

    public int getRenderDistance() {
        return Integer.parseInt(getTurrainProperties().getProperty("renderDistance"));
    }
}
