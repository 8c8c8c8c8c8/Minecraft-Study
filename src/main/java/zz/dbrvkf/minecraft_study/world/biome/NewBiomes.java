package zz.dbrvkf.minecraft_study.world.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import zz.dbrvkf.minecraft_study.entity.NewEntities;
import zz.dbrvkf.minecraft_study.sound.NewSounds;
import zz.dbrvkf.minecraft_study.util.Utils;
import zz.dbrvkf.minecraft_study.world.NewPlacedFeatures;

public class NewBiomes {
    public static final ResourceKey<Biome> TEST_BIOME = ResourceKey.create(Registries.BIOME, Utils.modLoc("test_biome"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(TEST_BIOME, testBiome(context));
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static Biome testBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawn$builder = new MobSpawnSettings.Builder();
        spawn$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(NewEntities.RHINO.get(), 2, 3, 5));
        spawn$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        BiomeDefaultFeatures.farmAnimals(spawn$builder);
        BiomeDefaultFeatures.commonSpawns(spawn$builder);
        BiomeGenerationSettings.Builder biome$builder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biome$builder);
        // we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        BiomeDefaultFeatures.addMossyStoneBlock(biome$builder);
        BiomeDefaultFeatures.addForestFlowers(biome$builder);
        BiomeDefaultFeatures.addFerns(biome$builder);
        BiomeDefaultFeatures.addDefaultOres(biome$builder);
        BiomeDefaultFeatures.addExtraGold(biome$builder);
        biome$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);
        BiomeDefaultFeatures.addDefaultMushrooms(biome$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biome$builder);
        biome$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NewPlacedFeatures.PINE_PLACED_KEY);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biome$builder.build())
                .mobSpawnSettings(spawn$builder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(NewSounds.BAR_BRAWL.getHolder().get())).build())
                .build();
    }
}
