package zz.dbrvkf.minecraft_study.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.entity.custom.RhinoEntity;

public class NewEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinecraftStudy.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino",
                    () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                            .sized(2.5f, 2.5f)
                            .build("rhino"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
