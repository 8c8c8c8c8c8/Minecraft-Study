package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
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
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(NewItems.SAPPHIRE_HELMET.get(),
                        NewItems.SAPPHIRE_CHESTPLATE.get(),
                        NewItems.SAPPHIRE_LEGGINGS.get(),
                        NewItems.SAPPHIRE_BOOTS.get());
        this.tag(ItemTags.MUSIC_DISCS)
                .add(NewItems.BAR_BRAWL_MUSIC_DISC.get());
    }
}
