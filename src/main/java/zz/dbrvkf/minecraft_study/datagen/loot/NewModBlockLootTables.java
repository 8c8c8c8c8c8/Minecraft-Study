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
import zz.dbrvkf.minecraft_study.block.custom.StrawberryCropBlock;
import zz.dbrvkf.minecraft_study.item.NewItems;

import java.util.Set;

public class NewModBlockLootTables extends BlockLootSubProvider {
    public NewModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(NewBlocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(NewBlocks.RAW_SAPPHIRE_BLOCK.get());
        this.dropSelf(NewBlocks.SOUND_BLOCK.get());
        this.dropSelf(NewBlocks.SAPPHIRE_STAIRS.get());
        this.dropSelf(NewBlocks.SAPPHIRE_BUTTON.get());
        this.dropSelf(NewBlocks.SAPPHIRE_PRESSURE_PLATE.get());
        this.dropSelf(NewBlocks.SAPPHIRE_TRAPDOOR.get());
        this.dropSelf(NewBlocks.SAPPHIRE_FENCE.get());
        this.dropSelf(NewBlocks.SAPPHIRE_FENCE_GATE.get());
        this.dropSelf(NewBlocks.SAPPHIRE_WALL.get());
        this.add(NewBlocks.SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        this.add(NewBlocks.END_STONE_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        this.add(NewBlocks.NETHER_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        this.add(NewBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(block, NewItems.RAW_SAPPHIRE.get()));
        this.add(NewBlocks.SAPPHIRE_SLAB.get(), this::createSlabItemTable);
        this.add(NewBlocks.SAPPHIRE_DOOR.get(), this::createSlabItemTable);

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(NewBlocks.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder
                        .properties().hasProperty(StrawberryCropBlock.AGE, 5));
        this.add(NewBlocks.STRAWBERRY_CROP.get(),
                createCropDrops(NewBlocks.STRAWBERRY_CROP.get(), NewItems.STRAWBERRY.get(),
                        NewItems.STRAWBERRY_SEEDS.get(), lootItemConditionBuilder));
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
        return NewBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
