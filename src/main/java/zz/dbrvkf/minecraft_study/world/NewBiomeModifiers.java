package zz.dbrvkf.minecraft_study.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import zz.dbrvkf.minecraft_study.MinecraftStudy;

public class NewBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SAPPHIRE_ORE = registerKey("add_sapphire_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_SAPPHIRE_ORE = registerKey("add_nether_sapphire_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_SAPPHIRE_ORE = registerKey("add_end_sapphire_ore");
    public static final ResourceKey<BiomeModifier> ADD_PINE_TREE = registerKey("add_pine_tree");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        context.register(ADD_SAPPHIRE_ORE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeatures.getOrThrow(NewPlacedFeatures.SAPPHIRE_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NETHER_SAPPHIRE_ORE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_NETHER),
                        HolderSet.direct(placedFeatures.getOrThrow(NewPlacedFeatures.NETHER_SAPPHIRE_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_END_SAPPHIRE_ORE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_END),
                        HolderSet.direct(placedFeatures.getOrThrow(NewPlacedFeatures.END_SAPPHIRE_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_PINE_TREE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                        HolderSet.direct(placedFeatures.getOrThrow(NewPlacedFeatures.PINE_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(MinecraftStudy.MOD_ID, name));
    }
}
