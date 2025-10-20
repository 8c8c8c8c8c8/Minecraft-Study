package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.util.NewTags;

import java.util.concurrent.CompletableFuture;

public class NewBlockTagsProvider extends BlockTagsProvider {
    public NewBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(NewTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(NewBlocks.SAPPHIRE_ORE.get())
                .addTag(Tags.Blocks.ORES);
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(NewBlocks.RAW_SAPPHIRE_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(NewBlocks.SAPPHIRE_BLOCK.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(NewBlocks.NETHER_SAPPHIRE_ORE.get());
        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(NewBlocks.END_STONE_SAPPHIRE_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(NewBlocks.RAW_SAPPHIRE_BLOCK.get())
                .add(NewBlocks.SAPPHIRE_ORE.get())
                .add(NewBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(NewBlocks.NETHER_SAPPHIRE_ORE.get())
                .add(NewBlocks.END_STONE_SAPPHIRE_ORE.get())
                .add(NewBlocks.SOUND_BLOCK.get());
        tag(BlockTags.FENCES)
                .add(NewBlocks.SAPPHIRE_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(NewBlocks.SAPPHIRE_FENCE_GATE.get());
        tag(BlockTags.WALLS)
                .add(NewBlocks.SAPPHIRE_WALL.get());
        tag(NewTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .add(NewBlocks.SOUND_BLOCK.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(NewBlocks.PINE_LOG.get())
                .add(NewBlocks.PINE_WOOD.get())
                .add(NewBlocks.STRIPPED_PINE_LOG.get())
                .add(NewBlocks.STRIPPED_PINE_WOOD.get());
        tag(BlockTags.PLANKS)
                .add(NewBlocks.PINE_PLANKS.get());
    }
}
