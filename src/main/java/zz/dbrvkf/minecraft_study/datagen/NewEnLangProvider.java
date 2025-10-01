package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;

public class NewEnLangProvider extends LanguageProvider {
    public NewEnLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.addItem(NewItems.SAPPHIRE, "Sapphire");
        this.addItem(NewItems.RAW_SAPPHIRE, "Raw Sapphire");
        this.addItem(NewItems.META_DETECTOR, "Metal Detector");
        this.addItem(NewItems.STRAWBERRY, "Strawberry");
        this.addItem(NewItems.PINE_CONE, "Pine cone");
        this.addItem(NewItems.SAPPHIRE_STAFF, "Sapphire Staff");
        this.addItem(NewItems.SAPPHIRE_SWORD, "Sapphire Sword");
        this.addItem(NewItems.SAPPHIRE_PICKAXE, "Sapphire Pickaxe");
        this.addItem(NewItems.SAPPHIRE_AXE, "Sapphire Axe");
        this.addItem(NewItems.SAPPHIRE_SHOVEL, "Sapphire Shovel");
        this.addItem(NewItems.SAPPHIRE_HOE, "Sapphire Hoe");
        this.addBlock(NewBlocks.SAPPHIRE_BLOCK, "Block of Sapphire");
        this.addBlock(NewBlocks.RAW_SAPPHIRE_BLOCK, "Block of Raw Sapphire");
        this.addBlock(NewBlocks.SAPPHIRE_ORE, "Sapphire Ore");
        this.addBlock(NewBlocks.DEEPSLATE_SAPPHIRE_ORE, "Deepslate Sapphire Ore");
        this.addBlock(NewBlocks.NETHER_SAPPHIRE_ORE, "Nether Sapphire Ore");
        this.addBlock(NewBlocks.END_STONE_SAPPHIRE_ORE, "Endstone Sapphire Ore");
        this.addBlock(NewBlocks.SOUND_BLOCK, "Sound Block");
        this.addBlock(NewBlocks.SAPPHIRE_STAIRS, "Sapphire Stairs");
        this.addBlock(NewBlocks.SAPPHIRE_SLAB, "Sapphire Slab");
        this.addBlock(NewBlocks.SAPPHIRE_BUTTON, "Sapphire Button");
        this.addBlock(NewBlocks.SAPPHIRE_PRESSURE_PLATE, "Sapphire Pressure Plate");
        this.addBlock(NewBlocks.SAPPHIRE_FENCE, "Sapphire Fence");
        this.addBlock(NewBlocks.SAPPHIRE_FENCE_GATE, "Sapphire Fence Gate");
        this.addBlock(NewBlocks.SAPPHIRE_WALL, "Sapphire Wall");
        this.addBlock(NewBlocks.SAPPHIRE_DOOR, "Sapphire Door");
        this.addBlock(NewBlocks.SAPPHIRE_TRAPDOOR, "Sapphire Trapdoor");
        this.add("creativetab.tutorial_tab", "Tutorial tab");
        this.add("tooltip.minecraft_study.metal_detector.tooltip", "Finds valuables underground.\nwhenever used one time, hp will be decreased -1");
        this.add("tooltip.minecraft_study.sound_block.tooltip", "Makes some sound when touched.");
    }
}
