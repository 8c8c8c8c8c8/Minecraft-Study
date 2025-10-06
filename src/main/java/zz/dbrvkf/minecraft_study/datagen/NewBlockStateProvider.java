package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.block.custom.CornCropBlock;
import zz.dbrvkf.minecraft_study.block.custom.StrawberryCropBlock;

import java.util.function.Function;

public class NewBlockStateProvider extends BlockStateProvider {
    public NewBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(NewBlocks.SAPPHIRE_BLOCK);
        blockWithItem(NewBlocks.SOUND_BLOCK);
        blockWithItem(NewBlocks.RAW_SAPPHIRE_BLOCK);
        blockWithItem(NewBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockWithItem(NewBlocks.END_STONE_SAPPHIRE_ORE);
        blockWithItem(NewBlocks.NETHER_SAPPHIRE_ORE);
        blockWithItem(NewBlocks.SAPPHIRE_ORE);

        stairsBlock(((StairBlock) NewBlocks.SAPPHIRE_STAIRS.get()), blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()));
        slabBlock(((SlabBlock) NewBlocks.SAPPHIRE_SLAB.get()),
                blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()), blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()));
        buttonBlock(((ButtonBlock) NewBlocks.SAPPHIRE_BUTTON.get()), blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) NewBlocks.SAPPHIRE_PRESSURE_PLATE.get()), blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()));
        fenceBlock(((FenceBlock) NewBlocks.SAPPHIRE_FENCE.get()), blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) NewBlocks.SAPPHIRE_FENCE_GATE.get()), blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()));
        wallBlock(((WallBlock) NewBlocks.SAPPHIRE_WALL.get()), blockTexture(NewBlocks.SAPPHIRE_BLOCK.get()));
        doorBlockWithRenderType(((DoorBlock) NewBlocks.SAPPHIRE_DOOR.get()),
                modLoc("block/sapphire_door_bottom"), modLoc("block/sapphire_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) NewBlocks.SAPPHIRE_TRAPDOOR.get()), modLoc("block/sapphire_trapdoor"), true, "cutout");
        makeStrawberryCrop((CropBlock) NewBlocks.STRAWBERRY_CROP.get(),
                "strawberry_stage", "strawberry_stage");
        makeCornCrop((CropBlock) NewBlocks.CORN_CROP.get(),
                "corn_stage_", "corn_stage_");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        IntegerProperty age = ((StrawberryCropBlock) block).getAgeProperty();
        return new ConfiguredModel[]{new ConfiguredModel(
                models().crop(modelName + state.getValue(age),
                                modLoc("block/" + textureName + state.getValue(age)))
                        .renderType("cutout"))};
    }

    public void makeCornCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
        IntegerProperty age = ((CornCropBlock) block).getAgeProperty();
        return new ConfiguredModel[]{new ConfiguredModel(
                models().crop(modelName + state.getValue(age),
                                modLoc("block/" + textureName + state.getValue(age)))
                        .renderType("cutout"))};
    }
}
