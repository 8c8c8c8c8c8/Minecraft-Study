package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.util.NewTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class NewBlockTagsProvider extends IntrinsicHolderTagsProvider<Block> {
    public NewBlockTagsProvider(PackOutput p_256164_, ResourceKey<? extends Registry<Block>> p_256155_,
                                CompletableFuture<HolderLookup.Provider> p_256488_,
                                Function<Block, ResourceKey<Block>> p_256168_,
                                String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256164_, p_256155_, p_256488_, p_256168_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        HolderLookup<Block> blockHolderLookup = pProvider.lookupOrThrow(Registries.BLOCK);
        Holder<Block> sapphireOre = blockHolderLookup.getOrThrow(NewBlocks.SAPPHIRE_ORE.getKey());
        tag(NewTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(sapphireOre.get())
                .addTag(Tags.Blocks.ORES);

        Holder<Block> rawSapphireBlock = blockHolderLookup.getOrThrow(NewBlocks.RAW_SAPPHIRE_BLOCK.getKey());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(rawSapphireBlock.get());

        Holder<Block> sapphireBlock = blockHolderLookup.getOrThrow(NewBlocks.SAPPHIRE_BLOCK.getKey());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(sapphireBlock.get());

        Holder<Block> netherSapphireOre = blockHolderLookup.getOrThrow(NewBlocks.NETHER_SAPPHIRE_ORE.getKey());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(netherSapphireOre.get());

        Holder<Block> endStoneSapphireOre = blockHolderLookup.getOrThrow(NewBlocks.END_STONE_SAPPHIRE_ORE.getKey());
        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(endStoneSapphireOre.get());

        Holder<Block> deepslateSapphireOre = blockHolderLookup.getOrThrow(NewBlocks.DEEPSLATE_SAPPHIRE_ORE.getKey());
        Holder<Block> soundBlock = blockHolderLookup.getOrThrow(NewBlocks.SOUND_BLOCK.getKey());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(rawSapphireBlock.get(), sapphireOre.get(), deepslateSapphireOre.get(),
                        netherSapphireOre.get(), endStoneSapphireOre.get(), soundBlock.get());

        Holder<Block> sapphireFence = blockHolderLookup.getOrThrow(NewBlocks.SAPPHIRE_FENCE.getKey());
        tag(BlockTags.FENCES)
                .add(sapphireFence.get());

        Holder<Block> sapphireFenceGate = blockHolderLookup.getOrThrow(NewBlocks.SAPPHIRE_FENCE_GATE.getKey());
        tag(BlockTags.FENCE_GATES)
                .add(sapphireFenceGate.get());

        Holder<Block> sapphireWall = blockHolderLookup.getOrThrow(NewBlocks.SAPPHIRE_WALL.getKey());
        tag(BlockTags.WALLS)
                .add(sapphireWall.get());

        tag(NewTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .add(soundBlock.get());

        Holder<Block> pineLog = blockHolderLookup.getOrThrow(NewBlocks.PINE_LOG.getKey());
        Holder<Block> pineWood = blockHolderLookup.getOrThrow(NewBlocks.PINE_WOOD.getKey());
        Holder<Block> strippedPineLog = blockHolderLookup.getOrThrow(NewBlocks.STRIPPED_PINE_LOG.getKey());
        Holder<Block> strippedPineWood = blockHolderLookup.getOrThrow(NewBlocks.STRIPPED_PINE_WOOD.getKey());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(pineLog.get(), pineWood.get(), strippedPineLog.get(), strippedPineWood.get());
        
        Holder<Block> pinePlanks = blockHolderLookup.getOrThrow(NewBlocks.PINE_PLANKS.getKey());
        tag(BlockTags.PLANKS)
                .add(pinePlanks.get());
    }
}
