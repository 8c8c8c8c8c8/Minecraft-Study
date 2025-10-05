package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import zz.dbrvkf.minecraft_study.item.NewItems;
import zz.dbrvkf.minecraft_study.loot.NewItemModifier;

public class NewGlobalModifierProvider extends GlobalLootModifierProvider {
    public NewGlobalModifierProvider(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void start() {
        add("pine_cone_from_grass",
                new NewItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.35f).build()},
                        NewItems.PINE_CONE.get()));
        add("pine_cone_from_creeper",
                new NewItemModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build()},
                        NewItems.PINE_CONE.get()));
        add("metal_detector_from_chests_in_jungle_temple",
                new NewItemModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build()},
                        NewItems.META_DETECTOR.get()));
        add("sapphire_from_desert_pyramid",
                new NewItemModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()},
                        NewItems.SAPPHIRE.get()));
    }
}
