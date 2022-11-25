package com.badlogic.mygame;

import com.badlogic.gdx.graphics.Texture;

public class player extends tank{
    private int health;
    private int coins;
    private String username;
    private tank tank1;

    public player(int health, Texture tank_image, int damage, int fuel,String username) {
        super(health, tank_image, damage, fuel);
        this.health=100;
        this.coins=0;
        this.username=username;
        this.tank1=new tank(10,100);
    }
    public void addCoins(int coins){
        this.coins+=coins;
    }

}
