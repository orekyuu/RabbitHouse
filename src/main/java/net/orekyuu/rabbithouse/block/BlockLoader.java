package net.orekyuu.rabbithouse.block;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Blockのロードを行うクラスです。
 */
public class BlockLoader {
    public void load() {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("Blocks.json")))
        );
        JsonBlocks blocks = gson.fromJson(reader, JsonBlocks.class);
        System.out.println(blocks);
    }
}