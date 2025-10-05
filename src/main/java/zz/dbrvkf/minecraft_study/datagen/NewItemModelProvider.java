package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;

import java.util.Objects;

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
                .texture("layer0", modLoc("item/" + getItemPath(NewItems.SAPPHIRE_SWORD.get())));
        simpleItem(NewItems.SAPPHIRE_PICKAXE, "item/handheld")
                .texture("layer0", modLoc("item/" + getItemPath(NewItems.SAPPHIRE_PICKAXE.get())));
        simpleItem(NewItems.SAPPHIRE_AXE, "item/handheld")
                .texture("layer0", modLoc("item/" + getItemPath(NewItems.SAPPHIRE_AXE.get())));
        simpleItem(NewItems.SAPPHIRE_SHOVEL, "item/handheld")
                .texture("layer0", modLoc("item/" + getItemPath(NewItems.SAPPHIRE_SHOVEL.get())));
        simpleItem(NewItems.SAPPHIRE_HOE, "item/handheld")
                .texture("layer0", modLoc("item/" + getItemPath(NewItems.SAPPHIRE_HOE.get())));
        basicItem(NewBlocks.SAPPHIRE_DOOR.get());
        basicItem(NewItems.STRAWBERRY_SEEDS.get());

        basicBlockItemWithParent(NewBlocks.SAPPHIRE_TRAPDOOR.get(), modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_TRAPDOOR.get()) + "_bottom"));
        basicBlockItemWithParent(NewBlocks.SAPPHIRE_STAIRS.get(), modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_STAIRS.get())));
        basicBlockItemWithParent(NewBlocks.SAPPHIRE_SLAB.get(), modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_SLAB.get())));
        basicBlockItemWithParent(NewBlocks.SAPPHIRE_PRESSURE_PLATE.get(), modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_PRESSURE_PLATE.get())));
        basicBlockItemWithParent(NewBlocks.SAPPHIRE_FENCE_GATE.get(), modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_FENCE_GATE.get())));
        basicBlockItemWithParent(NewBlocks.SAPPHIRE_FENCE.get(), mcLoc("block/fence_inventory"))
                .texture("texture", modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_BLOCK.get())));
        basicBlockItemWithParent(NewBlocks.SAPPHIRE_BUTTON.get(), mcLoc("block/button_inventory"))
                .texture("texture", modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_BLOCK.get())));
        basicBlockItemWithParent(NewBlocks.SAPPHIRE_WALL.get(), mcLoc("block/wall_inventory"))
                .texture("wall", modLoc("block/" + getBlockPath(NewBlocks.SAPPHIRE_BLOCK.get())));

        trimmableArmorItem(NewItems.SAPPHIRE_HELMET.get());
        trimmableArmorItem(NewItems.SAPPHIRE_CHESTPLATE.get());
        trimmableArmorItem(NewItems.SAPPHIRE_LEGGINGS.get());
        trimmableArmorItem(NewItems.SAPPHIRE_BOOTS.get());
    }

    private void trimmableArmorItem(Item item) {

    }

    private void basicItem(Block block) {
        basicItem(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
    }

    private ItemModelBuilder basicBlockItemWithParent(Block block, ResourceLocation parent) {
        return this.withExistingParent(getBlockPath(block), parent);
    }

    private String getItemPath(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private String getBlockPath(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private <T> ItemModelBuilder simpleItem(RegistryObject<T> obj, String parent) {
        return withExistingParent(obj.getId().getPath(),new ResourceLocation(parent));

    }
}
