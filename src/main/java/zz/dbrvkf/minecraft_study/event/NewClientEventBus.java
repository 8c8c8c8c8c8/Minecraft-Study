package zz.dbrvkf.minecraft_study.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.entity.client.NewModelLayers;
import zz.dbrvkf.minecraft_study.entity.client.RhinoModel;

@Mod.EventBusSubscriber(modid = MinecraftStudy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NewClientEventBus {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NewModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);
    }
}
