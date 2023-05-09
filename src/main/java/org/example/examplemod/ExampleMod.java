package org.example.examplemod;

import cpw.mods.fml.common.Mod;

@Mod(modid = ExampleMod.MODID, useMetadata = true)
public class ExampleMod {
    public static final String MODID = "examplemod";

    @Mod.Instance
    public static ExampleMod INSTANCE;
}
