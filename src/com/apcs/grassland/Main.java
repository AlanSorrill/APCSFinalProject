package com.apcs.grassland;

import com.apcs.grassland.ground.DynamicMeshTurrain;
import com.jme3.app.SimpleApplication;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("nosettings")) {
                AppSettings s = new AppSettings(true);
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                s.setResolution(d.width,d.height);
                s.setFullscreen(true);
                app.setSettings(s);
                app.setShowSettings(false);
            }
        }
        app.start();
    }

    private void initSky() {
        Texture east = getAssetManager().loadTexture("Skyboxes/ely_cloudtop/cloudtop_bk.png");
        Texture south = getAssetManager().loadTexture("Skyboxes/ely_cloudtop/cloudtop_rt.png");
        Texture west = getAssetManager().loadTexture("Skyboxes/ely_cloudtop/cloudtop_ft.png");
        Texture north = getAssetManager().loadTexture("Skyboxes/ely_cloudtop/cloudtop_lf.png");
        Texture up = getAssetManager().loadTexture("Skyboxes/ely_cloudtop/cloudtop_up.png");
        Texture down = getAssetManager().loadTexture("Skyboxes/ely_cloudtop/cloudtop_dn.png");
        getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), west, east, north, south, up, down));
    }

    @Override
    public void simpleInitApp() {
        WorldData.initWorld(false);
        cam.setLocation(new Vector3f(0, 12, 0));
        /**
         * A white, directional light source
         */
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);

        rootNode.addLight(sun);
        initSky();
        tur = new DynamicMeshTurrain(this);
        camLocation = cam.getLocation().clone();
        tur.setFocusLocation(camLocation);

        getRootNode().attachChild(tur.getBaseNode());

        this.getFlyByCamera().setMoveSpeed(5f);

    }
    private DynamicMeshTurrain tur;
    private boolean saveOnStop = false;

    @Override
    public void stop() {
        if (saveOnStop) {
            BinaryExporter exporter = BinaryExporter.getInstance();
            File file = new File("assets\\Models\\saves\\SceneSave.j3o");
            System.out.println("Saving to " + file.getAbsolutePath());
            try {
                exporter.save(rootNode, file);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
            }
        }
        super.stop(); // continue quitting the game
    }

    private Vector3f camLocation = new Vector3f(0, 0, 0);

    public void addLazyTask(LazyTask lt) {
        lazyTasks.add(lt);
    }
    private ArrayList<LazyTask> lazyTasks = new ArrayList();

    @Override
    public void simpleUpdate(float tpf) {
        if (lazyTasks.size() > 0) {
            lazyTasks.get(0).lazyExecute(this);
            lazyTasks.remove(0);
            //System.out.println(cam.getLocation().distance(camLocation));
            if (cam.getLocation().distance(camLocation) > tur.getChunkSize()) {
                System.out.println("Refocusing turrain");
                camLocation = cam.getLocation().clone();
                tur.setFocusLocation(camLocation);
            }
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
