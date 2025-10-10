package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;

public class NewItemModelProvider extends ItemModelProvider {
    public NewItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(NewItems.META_DETECTOR.get());
        basicItem(NewItems.PINE_CONE.get());
        basicItem(NewItems.RAW_SAPPHIRE.get());
        basicItem(NewItems.STRAWBERRY.get());
        basicItem(NewItems.SAPPHIRE.get());
        simpleItem(NewItems.SAPPHIRE_SWORD, "item/handheld")
                .texture("layer0", getModLocWithObj(NewItems.SAPPHIRE_SWORD, "item"));
        simpleItem(NewItems.SAPPHIRE_PICKAXE, "item/handheld")
                .texture("layer0", getModLocWithObj(NewItems.SAPPHIRE_PICKAXE, "item"));
        simpleItem(NewItems.SAPPHIRE_AXE, "item/handheld")
                .texture("layer0", getModLocWithObj(NewItems.SAPPHIRE_AXE, "item"));
        simpleItem(NewItems.SAPPHIRE_SHOVEL, "item/handheld")
                .texture("layer0", getModLocWithObj(NewItems.SAPPHIRE_SHOVEL, "item"));
        simpleItem(NewItems.SAPPHIRE_HOE, "item/handheld")
                .texture("layer0", getModLocWithObj(NewItems.SAPPHIRE_HOE, "item"));
        basicItem(NewBlocks.SAPPHIRE_DOOR);
        basicItem(NewItems.STRAWBERRY_SEEDS.get());
        basicItem(NewItems.STRAWBERRY.get());
        basicItem(NewItems.CORN_SEEDS.get());
        basicItem(NewItems.CORN.get());
        simpleItem(NewBlocks.SAPPHIRE_TRAPDOOR, getModLocWithObj(NewBlocks.SAPPHIRE_TRAPDOOR, "block", "_bottom"));
        simpleItem(NewBlocks.SAPPHIRE_STAIRS, getModLocWithObj(NewBlocks.SAPPHIRE_STAIRS, "block"));
        simpleItem(NewBlocks.SAPPHIRE_SLAB, getModLocWithObj(NewBlocks.SAPPHIRE_SLAB, "block"));
        simpleItem(NewBlocks.SAPPHIRE_PRESSURE_PLATE, getModLocWithObj(NewBlocks.SAPPHIRE_PRESSURE_PLATE, "block"));
        simpleItem(NewBlocks.SAPPHIRE_FENCE_GATE, getModLocWithObj(NewBlocks.SAPPHIRE_FENCE_GATE, "block"));
        simpleItem(NewBlocks.SAPPHIRE_FENCE, "block/fence_inventory")
                .texture("texture", getModLocWithObj(NewBlocks.SAPPHIRE_BLOCK, "block"));
        simpleItem(NewBlocks.SAPPHIRE_BUTTON, "block/button_inventory")
                .texture("texture", getModLocWithObj(NewBlocks.SAPPHIRE_BLOCK, "block"));
        simpleItem(NewBlocks.SAPPHIRE_WALL, "block/wall_inventory")
                .texture("wall", getModLocWithObj(NewBlocks.SAPPHIRE_BLOCK, "block"));

        trimmableArmorItem(NewItems.SAPPHIRE_HELMET.get());
        trimmableArmorItem(NewItems.SAPPHIRE_CHESTPLATE.get());
        trimmableArmorItem(NewItems.SAPPHIRE_LEGGINGS.get());
        trimmableArmorItem(NewItems.SAPPHIRE_BOOTS.get());
        simpleItem(NewBlocks.CATMINT, "item/generated")
                .texture("layer0", getModLocWithObj(NewBlocks.CATMINT, "block"));
    }

    private void trimmableArmorItem(Item item) {
        // todo
    }

    private ItemModelBuilder basicItem(RegistryObject<Block> block) {
        return basicItem(block.getId());
    }

    private <T> ItemModelBuilder simpleItem(RegistryObject<T> obj, String parent) {
        return withExistingParent(getPath(obj), mcLoc(parent));
    }

    private <T> ItemModelBuilder simpleItem(RegistryObject<T> obj, ResourceLocation parent) {
        return withExistingParent(getPath(obj), parent);
    }

    private <T> ResourceLocation getModLocWithObj(RegistryObject<T> obj, String type, String... rest) {
        return modLoc(String.format("%s/%s%s", type, getPath(obj), String.join("", rest)));
    }

    private <T> String getPath(RegistryObject<T> obj) {
        return obj.getId().getPath();
    }

}
