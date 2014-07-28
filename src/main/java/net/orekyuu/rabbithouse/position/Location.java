package net.orekyuu.rabbithouse.position;

import net.minecraft.world.World;

/**
 * ワールドと座標をもつクラス<br>
 */
public class Location {
    private World world;
    private Position position;

    /**
     * @param world    World
     * @param position 引数のWorld内の位置
     */
    public Location(World world, Position position) {
        if (world == null)
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
     * @param x     x座標
     * @param y     y座標
     * @param z     z座標
     */
    public Location(World world, float x, float y, float z) {
        this(world, new Position(x, y, z));
    }

    /**
     * ワールドを返します
     *
     * @return World
     */
    public World getWorld() {
        return world;
    }

    /**
     * 座標を返します
     *
     * @return 座標
     */
    public Position getPosition() {
        return position;
    }

    /**
     * 位置を設定します。nullを与えることはできません
     *
     * @param position 座標
     */
    public void setPosition(Position position) {
        if (position == null)
            throw new NullPointerException("Position is null");
        this.position = position;
    }

    /**
     * 引数との距離の長さの自乗の値を返します。
     *
     * @param position 距離を求めたいPosition
     * @return 二点間の距離の自乗
     */
    public float getMultiplyDistance(Position position) {
        return this.position.getMultiplyDistance(position);
    }

    /**
     * 引数との距離の長さの自乗の値を返します。
     *
     * @param location 距離を求めたいLocation
     * @return 二点間の距離の自乗
     */
    public float getMultiplyDistance(Location location) {
        return this.position.getMultiplyDistance(location.getPosition());
    }

    /**
     * 引数との距離の長さを返します。
     *
     * @param position 距離を求めたいPosition
     * @return 二点間の距離
     */
    public float getDistance(Position position) {
        return this.position.getDistance(position);
    }

    /**
     * 引数との距離の長さを返します。
     *
     * @param location 距離を求めたいLocation
     * @return 二点間の距離
     */
    public float getDistance(Location location) {
        return this.position.getDistance(location.getPosition());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        return position.equals(location.position) && world.equals(location.world);

    }

    @Override
    public int hashCode() {
        int result = world.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("world=").append(world);
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }
}
