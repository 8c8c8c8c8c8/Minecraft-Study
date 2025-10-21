package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;

import java.util.LinkedHashMap;

public class NewItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> TRIM_MATERIALS = new LinkedHashMap<>();

    static {
        TRIM_MATERIALS.put(TrimMaterials.QUARTZ, 0.1F);
        TRIM_MATERIALS.put(TrimMaterials.IRON, 0.2F);
        TRIM_MATERIALS.put(TrimMaterials.NETHERITE, 0.3F);
        TRIM_MATERIALS.put(TrimMaterials.REDSTONE, 0.4F);
        TRIM_MATERIALS.put(TrimMaterials.COPPER, 0.5F);
        TRIM_MATERIALS.put(TrimMaterials.GOLD, 0.6F);
        TRIM_MATERIALS.put(TrimMaterials.EMERALD, 0.7F);
        TRIM_MATERIALS.put(TrimMaterials.DIAMOND, 0.8F);
        TRIM_MATERIALS.put(TrimMaterials.LAPIS, 0.9F);
        TRIM_MATERIALS.put(TrimMaterials.AMETHYST, 1.0F);
    }

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

        trimmableArmorItem(NewItems.SAPPHIRE_HELMET);
        trimmableArmorItem(NewItems.SAPPHIRE_CHESTPLATE);
        trimmableArmorItem(NewItems.SAPPHIRE_LEGGINGS);
        trimmableArmorItem(NewItems.SAPPHIRE_BOOTS);
        simpleItem(NewBlocks.CATMINT, "item/generated")
                .texture("layer0", getModLocWithObj(NewBlocks.CATMINT, "block"));
        basicItem(NewItems.BAR_BRAWL_MUSIC_DISC.get());
        simpleItem(NewItems.RHINO_SPAWN_EGG, "item/template_spawn_egg");
        basicItem(NewItems.PINE_SIGN.get());
        basicItem(NewItems.PINE_HANGING_SIGN.get());
    }

    private void trimmableArmorItem(RegistryObject<Item> item) {
        if (!(item.get() instanceof ArmorItem armorItem))
            return;

        String armorType = switch (armorItem.getEquipmentSlot()) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "";
        };

        TRIM_MATERIALS.forEach((trimKey, trimValue) -> {
            ResourceLocation armorItemResLoc = modLoc("item/" + armorItem);
            ResourceLocation trimResLoc = mcLoc(String.format("trims/items/%s_trim_%s",
                    armorType, trimKey.location().getPath()));
            ResourceLocation trimNameResLoc = modLoc(String.format("item/%s_%s_trim",
                    armorItem, trimKey.location().getPath()));

            existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");
            getBuilder(trimNameResLoc.getPath())
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", armorItemResLoc)
                    .texture("layer1", trimResLoc);
            simpleItem(item, "item/generated")
                    .override()
                    .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                    .predicate(mcLoc("trim_type"), trimValue)
                    .end()
                    .texture("layer0", modLoc("item/" + getPath(item)));
        });
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
