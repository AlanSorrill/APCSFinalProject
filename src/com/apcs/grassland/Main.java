package com.apcs.grassland;

import com.apcs.grassland.ground.DynamicMeshTurrain;
import com.apcs.grassland.voxel.BlockManager;
import com.jme3.app.SimpleApplication;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        WorldData.initWorld(false);
cam.setLocation(new Vector3f(0,5,10));
        /**
         * A white, directional light source
         */
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        tur = new DynamicMeshTurrain(this);
        tur.addLoadedChunk(0, 0);
        tur.addLoadedChunk(-1, 0);
        tur.addLoadedChunk(0, -1);
        camLocation = cam.getLocation();
        tur.setFocusLocation(camLocation);
        //tur.addLoadedChunk(-1, -1);

        getRootNode().attachChild(tur.getBaseNode());

        this.getFlyByCamera().setMoveSpeed(2f);
        
    }
    private DynamicMeshTurrain tur;

    @Override
    public void stop() {
        BinaryExporter exporter = BinaryExporter.getInstance();
        File file = new File("assets\\Models\\saves\\SceneSave.j3o");
        System.out.println("Saving to " + file.getAbsolutePath());
        try {
            exporter.save(rootNode, file);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
        }
        super.stop(); // continue quitting the game
    }
    private Vector3f camLocation = new Vector3f(0,0,0);

    @Override
    public void simpleUpdate(float tpf) {
        if(cam.getLocation().distance(camLocation)>tur.getChunkSize()){
            camLocation = cam.getLocation();
            tur.setFocusLocation(camLocation);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
