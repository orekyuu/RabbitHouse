package net.orekyuu.rabbithouse.position;

import net.minecraft.entity.Entity;

/**
 * 座標を表すクラスです。<br>
 * Positionは不変なオブジェクトでスレッドセーフです。
 */
public class Position implements Cloneable {
    private final float x;
    private final float y;
    private final float z;

    /**
     * 座標を指定してPositionを作成します。
     *
     * @param x x座標
     * @param y y座標
     * @param z z座標
     */
    public Position(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * EntityのPositionを作成します
     *
     * @param entity Entity
     */
    public Position(Entity entity) {
        this.x = (float) entity.posX;
        this.y = (float) entity.posY;
        this.z = (float) entity.posZ;
    }

    /**
     * 原点のPositionを返します。
     */
    public Position() {
        this(0, 0, 0);
    }

    /**
     * x座標を返します
     *
     * @return x座標
     */
    public float getX() {
        return x;
    }

    /**
     * y座標を返します
     *
     * @return y座標
     */
    public float getY() {
        return y;
    }

    /**
     * z座標を返します
     *
     * @return z座標
     */
    public float getZ() {
        return z;
    }

    /**
     * xの値を加算し、結果の新しいPositionを返します。
     *
     * @param x x座標にプラスする値
     * @return 演算結果
     */
    public Position plusX(int x) {
        return new Position(this.x + x, this.y, this.z);
    }

    /**
     * yの値を加算し、結果の新しいPositionを返します。
     *
     * @param y y座標にプラスする値
     * @return 演算結果
     */
    public Position plusY(int y) {
        return new Position(this.x, this.y + y, this.z);
    }

    /**
     * zの値を加算し、結果の新しいPositionを返します。
     *
     * @param z z座標にプラスする値
     * @return 演算結果
     */
    public Position plusZ(int z) {
        return new Position(this.x, this.y, this.z + z);
    }

    /**
     * 現在のPositionと引数のPositionで加算し、結果の新しいPositionを返します。
     *
     * @param position 加算するposition
     * @return 演算結果
     */
    public Position plus(Position position) {
        return new Position(this.x + position.getX(), this.y + position.getY(), this.getZ() + position.getZ());
    }

    /**
     * xの値を減算し、結果の新しいPositionを返します。
     *
     * @param x x座標にマイナスする値
     * @return 演算結果
     */
    public Position minusX(int x) {
        return new Position(this.x - x, this.y, this.z);
    }

    /**
     * yの値を減算し、結果の新しいPositionを返します。
     *
     * @param y y座標にマイナスする値
     * @return 演算結果
     */
    public Position minusY(int y) {
        return new Position(this.x, this.y - y, this.z);
    }

    /**
     * zの値を減算し、結果の新しいPositionを返します。
     *
     * @param z z座標にマイナスする値
     * @return 演算結果
     */
    public Position minusZ(int z) {
        return new Position(this.x, this.y, this.z - z);
    }

    /**
     * 現在のPositionと引数のPositionで減算し、結果の新しいPositionを返します。
     *
     * @param position 減算するposition
     * @return 演算結果
     */
    public Position minus(Position position) {
        return new Position(this.x - position.getX(), this.y - position.getY(), this.getZ() - position.getZ());
    }

    /**
     * 引数との距離の長さの自乗の値を返します。
     *
     * @param position 距離を求めたいPosition
     * @return 二点間の距離の自乗
     */
    public float getMultiplyDistance(Position position) {
        float vx = this.x - position.getX();
        float vy = this.y - position.getY();
        float vz = this.z - position.getZ();
        return vx * vx + vy * vy + vz * vz;
    }

    /**
     * 二点間の距離を求めます。
     *
     * @param position 距離を求めたいPosition
     * @return 二点間の距離
     */
    public float getDistance(Position position) {
        return (float) Math.sqrt(getMultiplyDistance(position));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return Float.compare(position.x, x) == 0 && Float.compare(position.y, y) == 0 && Float.compare(position.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Position{x=%s, y=%s, z=%s}", x, y, z);
    }
}
