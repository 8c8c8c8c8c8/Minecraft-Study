package zz.dbrvkf.minecraft_study.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.block.custom.CornCropBlock;
import zz.dbrvkf.minecraft_study.block.custom.StrawberryCropBlock;
import zz.dbrvkf.minecraft_study.item.NewItems;

import java.util.Set;

public class NewBlockLootTables extends BlockLootSubProvider {
    public NewBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(NewBlocks.SAPPHIRE_BLOCK.get());
        dropSelf(NewBlocks.RAW_SAPPHIRE_BLOCK.get());
        dropSelf(NewBlocks.SOUND_BLOCK.get());
        dropSelf(NewBlocks.SAPPHIRE_STAIRS.get());
        dropSelf(NewBlocks.SAPPHIRE_BUTTON.get());
        dropSelf(NewBlocks.SAPPHIRE_PRESSURE_PLATE.get());
        dropSelf(NewBlocks.SAPPHIRE_TRAPDOOR.get());
        dropSelf(NewBlocks.SAPPHIRE_FENCE.get());
        dropSelf(NewBlocks.SAPPHIRE_FENCE_GATE.get());
        dropSelf(NewBlocks.SAPPHIRE_WALL.get());
        add(NewBlocks.SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        add(NewBlocks.END_STONE_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        add(NewBlocks.NETHER_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        add(NewBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        add(NewBlocks.SAPPHIRE_SLAB.get(), this::createSlabItemTable);
        add(NewBlocks.SAPPHIRE_DOOR.get(), this::createDoorTable);

        LootItemCondition.Builder strawberryLootItemConditionBuilder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(NewBlocks.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder
                        .properties().hasProperty(StrawberryCropBlock.AGE, 5));
        this.add(NewBlocks.STRAWBERRY_CROP.get(), createCropDrops(NewBlocks.STRAWBERRY_CROP.get(), NewItems.STRAWBERRY.get(),
                NewItems.STRAWBERRY_SEEDS.get(), strawberryLootItemConditionBuilder));
        LootItemCondition.Builder cornLootItemConditionBuilder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(NewBlocks.CORN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 7))
                .or(LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(NewBlocks.CORN_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 8)));
        this.add(NewBlocks.CORN_CROP.get(), createCropDrops(NewBlocks.CORN_CROP.get(), NewItems.CORN.get(),
                NewItems.CORN_SEEDS.get(), cornLootItemConditionBuilder));
        dropSelf(NewBlocks.CATMINT.get());
        this.add(NewBlocks.POTTED_CATMINT.get(), createPotFlowerItemTable(NewBlocks.CATMINT.get()));
        dropSelf(NewBlocks.GEM_POLISHING_STATION.get());
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 5.0f)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return java.util.stream.Stream.concat(
                NewBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get),
                NewBlocks.NO_BLOCK_ITEMS.getEntries().stream().map(RegistryObject::get)
        )::iterator;
    }
}
