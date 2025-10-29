package zz.dbrvkf.minecraft_study.world.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.world.tree.custom.PineFoliagePlacer;

public class NewFoliagePlacerType {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MinecraftStudy.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<PineFoliagePlacer>> PINE_FOLIAGE_PLACER =
            FOLIAGE_PLACER.register("pine_foliage_placer", () -> new FoliagePlacerType<>(PineFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACER.register(eventBus);
    }
}
