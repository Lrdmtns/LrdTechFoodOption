package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.utils.GTFOConfigOverrider;
import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;

@Mod(modid = GregTechFoodOption.MODID, name = GregTechFoodOption.NAME, version = GregTechFoodOption.VERSION,
        dependencies = "required-after:gtadditions")
public class GregTechFoodOption {
    public static final String MODID = "gregtechfoodoption";
    public static final String NAME = "GregTech Food Option";
    public static final String VERSION = "@VERSION@";


    @SidedProxy(modId = MODID, clientSide = "com.bruberu.gregtechfoodoption.ClientProxy", serverSide = "com.bruberu.gregtechfoodoption.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GTFOLog.init(event.getModLog());
        GTFOConfigOverrider.init();
        proxy.preLoad();

        MinecraftForge.EVENT_BUS.register(new GTFOEventHandler());
        MinecraftForge.EVENT_BUS.register(new GTFOSeedDropsEventHandler());

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        proxy.onLoad();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
