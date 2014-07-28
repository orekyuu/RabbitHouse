package net.orekyuu.rabbithouse.position;

import net.minecraft.world.World;

/**
 * ワールドと座標をもつクラス<br>
 */
public class Location {
    private World world;
    private Position position;

    /**
     * @param world World
     * @param position 引数のWorld内の位置
     */
    public Location(World world, Position position) {
        if(world == null)
            throw new NullPointerException("World is null");
        this.world = world;
        setPosition(position);
    }

    /**
     * @param world World
     */
    public Location(World world) {
        this(world, new Position(0, 0, 0));
    }

    /**
     * @param world World
     * @param x x座標
     * @param y y座標
     * @param z z座標
     */
    public Location(World world, float x, float y, float z) {
        this(world, new Position(x, y, z));
    }

    /**
     * ワールドを返します
     * @return World
     */
    public World getWorld() {
        return world;
    }

    /**
     * 座標を返します
     * @return 座標
     */
    public Position getPosition() {
        return position;
    }

    /**
     * 位置を設定します。nullを与えることはできません
     * @param position 座標
     */
    public void setPosition(Position position) {
        if(position == null)
            throw new NullPointerException("Position is null");
        this.position = position;
    }
}
