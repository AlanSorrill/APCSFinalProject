/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland.lazytaskimpls;

import com.apcs.grassland.LazyTask;
import com.apcs.grassland.Main;
import com.jme3.scene.Node;

/**
 *
 * @author alans
 */
public class LT_RemoveFromParent extends LazyTask{

        private Node node;

    /**
     * Get the value of node
     *
     * @return the value of node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Set the value of node
     *
     * @param node new value of node
     */
    public void setNode(Node node) {
        this.node = node;
    }

    public LT_RemoveFromParent(Node node) {
        this.node = node;
    }

    @Override
    public void lazyExecute(Main m) {
        node.removeFromParent();
    }
    
}
