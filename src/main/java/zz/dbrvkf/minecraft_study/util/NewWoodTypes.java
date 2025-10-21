package zz.dbrvkf.minecraft_study.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import zz.dbrvkf.minecraft_study.MinecraftStudy;

public class NewWoodTypes {
    public static final WoodType PINE = WoodType.register(
            new WoodType(MinecraftStudy.MOD_ID + ":pine", BlockSetType.OAK)
    );
}
