package zz.dbrvkf.minecraft_study.world.biome;

import terrablender.api.RegionType;
import terrablender.api.Regions;
import zz.dbrvkf.minecraft_study.util.Utils;

public class NewTerraBlender {
    public static void registerBiomes() {
        Regions.register(new NewOverworldRegion(Utils.modLoc("overworld"), RegionType.OVERWORLD,5));
    }
}
