package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class tank {
    private Texture tank_image;
    private int damage;
    private int fuel;
    private int price;

    public tank(int damage, int fuel) {
        this.tank_image =new Texture(Gdx.files.internal("tank_image.png"));;
        this.damage = damage;
        this.fuel = fuel;
    }

    public tank(int health, Texture tank_image, int damage, int fuel) {
    }

    private void moveRight(){

    }
    private void moveLeft(){

    }
    private void fire(){

    }
}
