package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;
import zz.dbrvkf.minecraft_study.recipe.GemPolishingRecipeBuilder;

import java.util.List;
import java.util.function.Consumer;

public class NewRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(NewItems.RAW_SAPPHIRE.get(),
            NewBlocks.SAPPHIRE_ORE.get(), NewBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
            NewBlocks.NETHER_SAPPHIRE_ORE.get(), NewBlocks.END_STONE_SAPPHIRE_ORE.get());
    public NewRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        RecipeProvider.oreBlasting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC,
                NewItems.SAPPHIRE.get(), .25f, 100, "sapphire");
        RecipeProvider.oreSmelting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC,
                NewItems.SAPPHIRE.get(), .25f, 200, "sapphire");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NewBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', NewItems.SAPPHIRE.get())
                .unlockedBy(getHasName(NewItems.SAPPHIRE.get()), has(NewItems.SAPPHIRE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NewItems.SAPPHIRE.get(), 9)
                .requires(NewBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(NewItems.SAPPHIRE.get()), has(NewItems.SAPPHIRE.get()))
                .save(pWriter);
        GemPolishingRecipeBuilder.shapeless(RecipeCategory.MISC, Items.DIAMOND, 7)
                .requires(Items.COAL, 1)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter, new ResourceLocation(MinecraftStudy.MOD_ID, "diamond_from_gem_polishing"));
        GemPolishingRecipeBuilder.shapeless(RecipeCategory.MISC, NewItems.SAPPHIRE.get(), 3)
                .requires(NewItems.RAW_SAPPHIRE.get(), 1)
                .unlockedBy(getHasName(NewItems.RAW_SAPPHIRE.get()), has(NewItems.SAPPHIRE.get()))
                .save(pWriter, new ResourceLocation(MinecraftStudy.MOD_ID, "sapphire_from_gem_polishing"));
    }

}
