package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;

import java.util.function.Supplier;

public class NewEnLangProvider extends LanguageProvider {
    public NewEnLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        addItem(NewItems.SAPPHIRE, "Sapphire");
        addItem(NewItems.RAW_SAPPHIRE, "Raw Sapphire");
        addItem(NewItems.META_DETECTOR, "Metal Detector");
        addItem(NewItems.STRAWBERRY, "Strawberry");
        addItem(NewItems.PINE_CONE, "Pine cone");
        addItem(NewItems.SAPPHIRE_STAFF, "Sapphire Staff");
        addItem(NewItems.SAPPHIRE_SWORD, "Sapphire Sword");
        addItem(NewItems.SAPPHIRE_PICKAXE, "Sapphire Pickaxe");
        addItem(NewItems.SAPPHIRE_AXE, "Sapphire Axe");
        addItem(NewItems.SAPPHIRE_SHOVEL, "Sapphire Shovel");
        addItem(NewItems.SAPPHIRE_HOE, "Sapphire Hoe");
        addItem(NewItems.SAPPHIRE_HELMET, "Sapphire Helmet");
        addItem(NewItems.SAPPHIRE_CHESTPLATE, "Sapphire Chest plate");
        addItem(NewItems.SAPPHIRE_LEGGINGS, "Sapphire Leggings");
        addItem(NewItems.SAPPHIRE_BOOTS, "Sapphire Boots");
        addItem(NewItems.STRAWBERRY_SEEDS, "Strawberry Seeds");
        addItem(NewItems.CORN_SEEDS, "Corn Seeds");
        addItem(NewItems.CORN, "Corn");
        addItem(NewItems.BAR_BRAWL_MUSIC_DISC, "Bar Brawl Music Disc", "Bryan Tech - Bar Brawl (CC0)");
        addItem(NewItems.RHINO_SPAWN_EGG, "Rhino Egg");
        addBlock(NewBlocks.SAPPHIRE_BLOCK, "Block of Sapphire");
        addBlock(NewBlocks.RAW_SAPPHIRE_BLOCK, "Block of Raw Sapphire");
        addBlock(NewBlocks.SAPPHIRE_ORE, "Sapphire Ore");
        addBlock(NewBlocks.DEEPSLATE_SAPPHIRE_ORE, "Deepslate Sapphire Ore");
        addBlock(NewBlocks.NETHER_SAPPHIRE_ORE, "Nether Sapphire Ore");
        addBlock(NewBlocks.END_STONE_SAPPHIRE_ORE, "Endstone Sapphire Ore");
        addBlock(NewBlocks.SOUND_BLOCK, "Sound Block");
        addBlock(NewBlocks.SAPPHIRE_STAIRS, "Sapphire Stairs");
        addBlock(NewBlocks.SAPPHIRE_SLAB, "Sapphire Slab");
        addBlock(NewBlocks.SAPPHIRE_BUTTON, "Sapphire Button");
        addBlock(NewBlocks.SAPPHIRE_PRESSURE_PLATE, "Sapphire Pressure Plate");
        addBlock(NewBlocks.SAPPHIRE_FENCE, "Sapphire Fence");
        addBlock(NewBlocks.SAPPHIRE_FENCE_GATE, "Sapphire Fence Gate");
        addBlock(NewBlocks.SAPPHIRE_WALL, "Sapphire Wall");
        addBlock(NewBlocks.SAPPHIRE_DOOR, "Sapphire Door");
        addBlock(NewBlocks.SAPPHIRE_TRAPDOOR, "Sapphire Trapdoor");
        addBlock(NewBlocks.CATMINT, "Catmint");
        addBlock(NewBlocks.GEM_POLISHING_STATION, "Gem Polishing Station");
        addBlock(NewBlocks.PINE_LOG, "Pine Log");
        addBlock(NewBlocks.PINE_WOOD, "Pine Wood");
        addBlock(NewBlocks.STRIPPED_PINE_LOG, "Stripped Pine Log");
        addBlock(NewBlocks.STRIPPED_PINE_WOOD, "Stripped Pine Wood");
        addBlock(NewBlocks.PINE_PLANKS, "Pine Planks");
        addBlock(NewBlocks.PINE_LEAVES, "Pine Leaves");
        addBlock(NewBlocks.PINE_SIGN, "Pine Sign");
        addBlock(NewBlocks.PINE_HANGING_SIGN, "Pine Hanging Sign");
        add("creativetab.tutorial_tab", "Tutorial tab");
        add("tooltip.minecraft_study.metal_detector.tooltip", "Finds valuables underground.\nwhenever used one time, hp will be decreased -1");
        add("tooltip.minecraft_study.sound_block.tooltip", "Makes some sound when touched.");
        add("entity.minecraft.villager.minecraft_study.sound_master", "Sound Master");
        add("sounds.minecraft_study.metal_detector_found_ore", "Metal Detector Jingle");
    }

    private void addItem(Supplier<? extends Item> key, String name, String description) {
        super.addItem(key, name);
        add(key.get().getDescriptionId() + ".desc", description);
    }

    private void addBlock(Supplier<? extends Block> key, String name, String description) {
        super.addBlock(key, name);
        add(key.get().getDescriptionId() + ".desc", description);
    }
}
