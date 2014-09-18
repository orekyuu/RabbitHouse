package net.orekyuu.demo;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.orekyuu.rabbithouse.loader.block.BlockField;
import net.orekyuu.rabbithouse.loader.block.BlockLoader;

@Mod(modid = DemoMod.MODID)
public class DemoMod {
    public static final String MODID = "demo";
    @Mod.Instance
    public static DemoMod instance;

    @BlockField("test block")
    public static Block block;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BlockLoader loader = new BlockLoader(this, MODID);
        loader.load();
        System.out.println(block);
    }

}
