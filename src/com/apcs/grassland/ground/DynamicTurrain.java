/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.ground;

import com.apcs.grassland.Main;
import com.apcs.grassland.lazytaskimpls.LT_AddChunk;
import com.apcs.grassland.lazytaskimpls.LT_RemoveChunk;
import com.jme3.math.Vector3f;
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

    public void setFocusLocation(Vector3f foc) {
        loadedChunks.clear();
        int fx = Math.round(foc.getX() / this.getChunkSize());
        int fy = Math.round(foc.getZ() / this.getChunkSize());
        int dist = this.getRenderDistance();
        int sx = fx - dist;
        int sy = fy - dist;
        int width = dist * 2;
        System.out.println("Setting dturrain foacus location to " + fx + ", " + fy + ". \n"
                + "Adding chunks (" + sx + "," + sy + ") to (" + (sx + width) + "," + (sy + width) + ")");
        int total = 0;
        for (int x = sx; x < sx + width; x++) {
            for (int y = sy; y < sy + width; y++) {
                System.out.println("Adding focused chunk " + x + ", " + y);
                loadedChunks.add(new Integer[]{x, y});
                total++;
            }
        }
        System.out.println("Commiting turrain chunk load (" + total + " chunks)");
        this.updateChunks();
    }

    private void init() {
        FileInputStream fis = null;
        try {
            this.baseNode.setName("DynamicTurrain");
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

    public static final int CHUNK_UNLOADED = 0, CHUNK_LOADED = 1, CHUNK_LOADING = 2;
    private ArrayList<ArrayList<Integer>> chunkStates = new ArrayList();

    public int getChunkState(int x, int y) {
        try {
            return chunkStates.get(x).get(y);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            setChunkState(x, y, CHUNK_UNLOADED);
        }
        return getChunkState(x, y);
    }

    public void setChunkState(int x, int y, int state) {
        if (chunkStates.get(x) == null) {
            chunkStates.set(x, new ArrayList());
        }
        chunkStates.get(x).set(y, state);
    }

    private void updateChunks() {
        Spatial n;
        float chunkSize = this.getChunkSize();
        for (Integer[] i : loadedChunks) {// add in new chunks
            if (getChunkState(i[0], i[1]) == CHUNK_UNLOADED) {
                this.getGameInstance().addLazyTask(new LT_AddChunk(i[0], i[1], this));
            }
        }
        for(int x = 0;x<chunkStates.size();x++){
            for(int y = 0;y<chunkStates.get(x).size();y++){
                if(getChunkState(x,y)==CHUNK_LOADED && loadedChunks.contains(new Integer[]{x,y})){
                    getGameInstance().addLazyTask(new LT_RemoveChunk(x, y, this, chunks.get(i)));
                }
            }
        }
    }

    //each chunk identified by int[]{x,y}
    private ArrayList<Integer[]> loadedChunks = new ArrayList();
    public HashMap<Integer[], Spatial> chunks = new HashMap();
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
