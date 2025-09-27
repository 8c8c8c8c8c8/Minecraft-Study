package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.block.NewBlocks;

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
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
