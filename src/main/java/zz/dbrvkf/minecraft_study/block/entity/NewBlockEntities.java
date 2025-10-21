package zz.dbrvkf.minecraft_study.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.block.NewBlocks;

public class NewBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MinecraftStudy.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemPolishingStationBlockEntity>> GEM_POLISHING_STATION =
            BLOCK_ENTITIES.register("gem_polishing_station",
                    () -> BlockEntityType.Builder.of(GemPolishingStationBlockEntity::new,
                            NewBlocks.GEM_POLISHING_STATION.get()).build(null));
    public static final RegistryObject<BlockEntityType<NewSignBlockEntity>> SIGNS =
            BLOCK_ENTITIES.register("signs",
                    () -> BlockEntityType.Builder.of(NewSignBlockEntity::new, NewBlocks.PINE_SIGN.get(),
                            NewBlocks.PINE_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<NewHangingSignBlockEntity>> HANGING_SIGNS =
            BLOCK_ENTITIES.register("hanging_signs",
                    () -> BlockEntityType.Builder.of(NewHangingSignBlockEntity::new, NewBlocks.PINE_HANGING_SIGN.get(),
                            NewBlocks.PINE_WALL_HANGING_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
