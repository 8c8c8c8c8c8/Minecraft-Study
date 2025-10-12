package zz.dbrvkf.minecraft_study.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.block.NewBlocks;

public class NewCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinecraftStudy.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab",
                    () -> CreativeModeTab.builder().icon(() -> new ItemStack(NewItems.SAPPHIRE.get()))
                            .title(Component.translatable("creativetab.tutorial_tab"))
                            .displayItems(((pParms, pOutput) -> {
                                pOutput.accept(NewItems.SAPPHIRE.get());
                                pOutput.accept(NewItems.RAW_SAPPHIRE.get());
                                pOutput.accept(NewItems.META_DETECTOR.get());
                                pOutput.accept(NewItems.STRAWBERRY.get());
                                pOutput.accept(NewItems.PINE_CONE.get());
                                pOutput.accept(NewItems.SAPPHIRE_STAFF.get());
                                pOutput.accept(NewItems.SAPPHIRE_SWORD.get());
                                pOutput.accept(NewItems.SAPPHIRE_PICKAXE.get());
                                pOutput.accept(NewItems.SAPPHIRE_AXE.get());
                                pOutput.accept(NewItems.SAPPHIRE_SHOVEL.get());
                                pOutput.accept(NewItems.SAPPHIRE_HOE.get());
                                pOutput.accept(NewItems.SAPPHIRE_HELMET.get());
                                pOutput.accept(NewItems.SAPPHIRE_CHESTPLATE.get());
                                pOutput.accept(NewItems.SAPPHIRE_LEGGINGS.get());
                                pOutput.accept(NewItems.SAPPHIRE_BOOTS.get());
                                pOutput.accept(NewItems.STRAWBERRY_SEEDS.get());
                                pOutput.accept(NewItems.CORN_SEEDS.get());
                                pOutput.accept(NewItems.CORN.get());
                                pOutput.accept(NewItems.BAR_BRAWL_MUSIC_DISC.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_BLOCK.get());
                                pOutput.accept(NewBlocks.RAW_SAPPHIRE_BLOCK.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_ORE.get());
                                pOutput.accept(NewBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                                pOutput.accept(NewBlocks.NETHER_SAPPHIRE_ORE.get());
                                pOutput.accept(NewBlocks.END_STONE_SAPPHIRE_ORE.get());
                                pOutput.accept(NewBlocks.SOUND_BLOCK.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_STAIRS.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_SLAB.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_BUTTON.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_PRESSURE_PLATE.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_FENCE.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_FENCE_GATE.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_WALL.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_DOOR.get());
                                pOutput.accept(NewBlocks.SAPPHIRE_TRAPDOOR.get());
                                pOutput.accept(NewBlocks.STRAWBERRY_CROP.get());
                                pOutput.accept(NewBlocks.CORN_CROP.get());
                                pOutput.accept(NewBlocks.CATMINT.get());
                            })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
