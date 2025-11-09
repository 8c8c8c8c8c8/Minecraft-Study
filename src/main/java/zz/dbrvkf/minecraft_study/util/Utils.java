package zz.dbrvkf.minecraft_study.util;

import net.minecraft.resources.ResourceLocation;
import zz.dbrvkf.minecraft_study.MinecraftStudy;

public class Utils {
    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(MinecraftStudy.MOD_ID, path);
    }

    public static ResourceLocation mcLoc(String path) {
        return new ResourceLocation(path);
    }
}
