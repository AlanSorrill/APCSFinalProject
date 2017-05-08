/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apcs.grassland;

/**
 *
 * @author Alan
 */
public abstract class ScalarField {

    public float getValue(float x, float y) {
        return getValue(x, y, 0);
    }

    public abstract float getValue(float x, float y, float z);
}
