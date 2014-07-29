package net.orekyuu.rabbithouse.block;

class HarvestLevel {
    private String key;
    private int value;

    String getKey() {
        return key;
    }

    int getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HarvestLevel{");
        sb.append("key='").append(key).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
