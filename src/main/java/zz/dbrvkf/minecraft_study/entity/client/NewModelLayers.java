package zz.dbrvkf.minecraft_study.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import zz.dbrvkf.minecraft_study.MinecraftStudy;

public class NewModelLayers {
    public static final ModelLayerLocation RHINO_LAYER =
            new ModelLayerLocation(modLoc("rhino_layer"), "main");
    public static final ModelLayerLocation PINE_BOAT_LAYER =
            new ModelLayerLocation(modLoc("boat/pine"), "main");
    public static final ModelLayerLocation PINE_CHEST_BOAT_LAYER =
            new ModelLayerLocation(modLoc("chest_boat/pine"), "main");

    private static ResourceLocation modLoc(String path) {
        return new ResourceLocation(MinecraftStudy.MOD_ID, path);
    }
}
