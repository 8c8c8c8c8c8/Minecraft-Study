package zz.dbrvkf.minecraft_study.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class NewOverworldRegion extends Region {
    public NewOverworldRegion(ResourceLocation name, RegionType type, int weight) {
        super(name, type, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint,
            ResourceKey<Biome>>> mapper) {
        addModifiedVanillaOverworldBiomes(mapper, modifiedBuilder -> {
//            builder.replaceBiome(Biomes.FOREST, NewBiomes.TEST_BIOME);
            VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
            // Overlap Vanilla's parameters with our own for our NewBiomes.TEST_BIOME.
            // The parameters for this biome are chosen arbitrarily.
            new ParameterPointListBuilder()
                    .temperature(Temperature.span(Temperature.COOL, Temperature.FROZEN))
                    .humidity(Humidity.span(Humidity.ARID, Humidity.DRY))
                    .continentalness(Continentalness.INLAND)
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
                    .build().forEach(point -> builder.add(point, NewBiomes.TEST_BIOME));

            // Add our points to the mapper
            builder.build().forEach(mapper::accept);
        });
    }
}
