package zz.dbrvkf.minecraft_study.entity.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.entity.custom.NewBoatEntity;
import zz.dbrvkf.minecraft_study.entity.custom.NewChestBoatEntity;

import java.util.Map;
import java.util.stream.Stream;

public class NewBoatRenderer extends BoatRenderer {
    private final Map<NewBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public NewBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(NewBoatEntity.Type.values())
                .collect(ImmutableMap.toImmutableMap(
                        type -> type, type -> Pair.of(new ResourceLocation(MinecraftStudy.MOD_ID, getTextureLocation(type, pChestBoat)),
                                createBoatModel(pContext, type, pChestBoat))));
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, NewBoatEntity.Type pType, boolean pChestBoat) {
        ModelLayerLocation modelLayerLocation = createBoatModelName(pType, pChestBoat);
        ModelPart modelPart = pContext.bakeLayer(modelLayerLocation);
        return pChestBoat ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }

    private ModelLayerLocation createBoatModelName(NewBoatEntity.Type pType, boolean isChestBoat) {
        String dirName = getDirectoryName(isChestBoat);
        return createLocation(dirName + pType.getName(), "main");
    }

    private ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(MinecraftStudy.MOD_ID, pPath), pModel);
    }

    private String getTextureLocation(NewBoatEntity.Type pType, boolean pChestBoat) {
        String dirName = getDirectoryName(pChestBoat);
        return String.format("textures/entity/%s%s.png", dirName, pType.getName());
    }

    private String getDirectoryName(boolean isChestBoat) {
        return isChestBoat ? "chest_boat/" : "boat/";
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if (boat instanceof NewBoatEntity newBoat)
            return this.boatResources.get(newBoat.getNewVariant());
        if (boat instanceof NewChestBoatEntity newBoat)
            return this.boatResources.get(newBoat.getNewVariant());
        return null;
    }
}
