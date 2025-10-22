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
        ModelLayerLocation modellayerlocation = pChestBoat ? NewBoatRenderer.createChestBoatModelName(pType) : NewBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    private static ModelLayerLocation createChestBoatModelName(NewBoatEntity.Type pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createBoatModelName(NewBoatEntity.Type pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(MinecraftStudy.MOD_ID, pPath), pModel);
    }

    private static String getTextureLocation(NewBoatEntity.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
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
