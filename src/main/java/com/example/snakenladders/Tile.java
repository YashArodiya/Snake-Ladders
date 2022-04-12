package com.example.snakenladders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    //Framework of a specific tile
    public Tile(int x, int y) {
        setWidth(DiceRollSnake.Tile_size) ;
        setHeight(DiceRollSnake.Tile_size) ;
        setFill(Color.WHITE);
        setStroke(Color.BLACK) ;
    }
}
