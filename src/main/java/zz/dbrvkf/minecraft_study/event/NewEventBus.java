package zz.dbrvkf.minecraft_study.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.entity.NewEntities;
import zz.dbrvkf.minecraft_study.entity.client.NewModelLayers;
import zz.dbrvkf.minecraft_study.entity.client.RhinoModel;
import zz.dbrvkf.minecraft_study.entity.custom.RhinoEntity;

@Mod.EventBusSubscriber(modid = MinecraftStudy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NewEventBus {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(NewEntities.RHINO.get(), RhinoEntity.createAttribute().build());
    }
}
