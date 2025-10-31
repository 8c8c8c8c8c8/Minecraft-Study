package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import zz.dbrvkf.minecraft_study.world.NewBiomeModifiers;
import zz.dbrvkf.minecraft_study.world.NewConfiguredFeatures;
import zz.dbrvkf.minecraft_study.world.NewPlacedFeatures;
import zz.dbrvkf.minecraft_study.world.biome.NewBiomes;
import zz.dbrvkf.minecraft_study.world.dimension.NewDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NewWorldProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, NewDimensions::bootstrapType)
            .add(Registries.CONFIGURED_FEATURE, NewConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, NewPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, NewBiomeModifiers::bootstrap)
            .add(Registries.BIOME, NewBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, NewDimensions::bootstrapStem);

    public NewWorldProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, Set<String> modIds) {
        super(output, registries, BUILDER, modIds);
    }
}
