package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;

import java.util.concurrent.CompletableFuture;

public class NewItemTagsProvider extends ItemTagsProvider {
    public NewItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, String modId,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        HolderLookup<Item> itemHolderLookup = pProvider.lookupOrThrow(Registries.ITEM);

        Holder<Item> sapphireHelmet = itemHolderLookup.getOrThrow(NewItems.SAPPHIRE_HELMET.getKey());
        Holder<Item> sapphireChestplate = itemHolderLookup.getOrThrow(NewItems.SAPPHIRE_CHESTPLATE.getKey());
        Holder<Item> sapphireLeggings = itemHolderLookup.getOrThrow(NewItems.SAPPHIRE_LEGGINGS.getKey());
        Holder<Item> sapphireBoots = itemHolderLookup.getOrThrow(NewItems.SAPPHIRE_BOOTS.getKey());
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(sapphireHelmet.get(), sapphireChestplate.get(),
                        sapphireLeggings.get(), sapphireBoots.get());

        Holder<Item> barBrawlMusicDisc = itemHolderLookup.getOrThrow(NewItems.BAR_BRAWL_MUSIC_DISC.getKey());
        this.tag(ItemTags.MUSIC_DISCS)
                .add(barBrawlMusicDisc.get());

        HolderLookup<Block> blockHolderLookup = pProvider.lookupOrThrow(Registries.BLOCK);
        Holder<Block> pineLog = blockHolderLookup.getOrThrow(NewBlocks.PINE_LOG.getKey());
        Holder<Block> pineWood = blockHolderLookup.getOrThrow(NewBlocks.PINE_WOOD.getKey());
        Holder<Block> strippedPineLog = blockHolderLookup.getOrThrow(NewBlocks.STRIPPED_PINE_LOG.getKey());
        Holder<Block> strippedPineWood = blockHolderLookup.getOrThrow(NewBlocks.STRIPPED_PINE_WOOD.getKey());
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(pineLog.get().asItem(), pineWood.get().asItem(),
                        strippedPineLog.get().asItem(), strippedPineWood.get().asItem());

        Holder<Block> pinePlanks = blockHolderLookup.getOrThrow(NewBlocks.PINE_PLANKS.getKey());
        this.tag(ItemTags.PLANKS)
                .add(pinePlanks.get().asItem());
    }
}
