/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class holds data about a specific biom. It will load it's properties
 * from a file with the name given in the constructor plus the extension .biom
 */
public class BiomData {

    public BiomData(String name) {
        this.name = name;
        props = new Properties();
        File lFile = getFileForName(name);
        if (lFile.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(lFile);
                props.load(fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BiomData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BiomData.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(BiomData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private String name;
    private Properties props;

    public String getName() {
        return name;
    }

    public float getHeightCoefficient() {
        return Float.parseFloat(props.getProperty("height"));
    }

    public float getPercipCoefficient() {
        return Float.parseFloat(props.getProperty("percip"));
    }

    private static File getFileForName(String name) {
        return new File("bioms" + System.getProperty("file.separator") + name + ".biom");
    }

    /**
     * Call this function from some debug method only once to create a fresh
     * biom file
     *
     * @param name name of the biom, and the file which will be created
     */
    public static void createNewDatafile(String name) {
        FileOutputStream fos = null;
        try {
            File lFile = getFileForName(name);
            Properties np = new Properties();
            np.setProperty("height", "1");
            np.setProperty("percip", "1");
            fos = new FileOutputStream(lFile);
            np.store(fos, "Biom Data for " + name + " bioms");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BiomData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BiomData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(BiomData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
