package zz.dbrvkf.minecraft_study.recipe;

import com.google.common.collect.Lists;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class GemPolishingRecipeBuilder extends CraftingRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;

    public GemPolishingRecipeBuilder(RecipeCategory pCategory, ItemLike pResult, int pCount) {
        this.category = pCategory;
        this.result = pResult.asItem();
        this.count = pCount;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    public static GemPolishingRecipeBuilder shapeless(RecipeCategory pCategory, ItemLike pResult, int pCount) {
        return new GemPolishingRecipeBuilder(pCategory, pResult, pCount);
    }

    @Override
    public GemPolishingRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public GemPolishingRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public GemPolishingRecipeBuilder requires(ItemLike item, int pQuantity) {
        for(int i = 0; i < pQuantity; ++i) {
            this.ingredients.add(Ingredient.of(item));
        }
        return this;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new GemPolishingRecipeBuilder.Result(pRecipeId, this.result, this.count, this.group == null ? "" : this.group, determineBookCategory(this.category), this.ingredients, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result extends ShapelessRecipeBuilder.Result {
        public Result(ResourceLocation pId, Item pResult, int pCount, String pGroup,
                      CraftingBookCategory pCategory, List<Ingredient> pIngredients,
                      Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            super(pId, pResult, pCount, pGroup, pCategory, pIngredients, pAdvancement, pAdvancementId);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return GemPolishingRecipe.Serializer.INSTANCE;
        }
    }
}
