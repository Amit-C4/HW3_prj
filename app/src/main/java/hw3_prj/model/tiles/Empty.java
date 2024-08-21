package hw3_prj.model.tiles;

import hw3_prj.model.tiles.Units.Unit;

public class Empty extends Tile {
    private static final char EMPTY_TILE = '.';

    public Empty() {
        super(EMPTY_TILE);
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}