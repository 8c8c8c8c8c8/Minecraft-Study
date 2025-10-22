package zz.dbrvkf.minecraft_study.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.entity.custom.NewBoatEntity;
import zz.dbrvkf.minecraft_study.entity.custom.NewChestBoatEntity;
import zz.dbrvkf.minecraft_study.entity.custom.RhinoEntity;

public class NewEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinecraftStudy.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino",
                    () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                            .sized(2.5f, 2.5f)
                            .build("rhino"));
    public static final RegistryObject<EntityType<NewBoatEntity>> BOAT =
            ENTITY_TYPES.register("boat",
                    () -> EntityType.Builder.<NewBoatEntity>of(NewBoatEntity::new, MobCategory.MISC)
                            .sized(1.375f, 0.5625f)
                            .build("boat"));
    public static final RegistryObject<EntityType<NewChestBoatEntity>> CHEST_BOAT =
            ENTITY_TYPES.register("chest_boat",
                    () -> EntityType.Builder.<NewChestBoatEntity>of(NewChestBoatEntity::new, MobCategory.MISC)
                            .sized(1.375f, 0.5625f)
                            .build("chest_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
