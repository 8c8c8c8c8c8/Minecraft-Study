package zz.dbrvkf.minecraft_study.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.item.NewItems;

public class NewCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinecraftStudy.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab",
                    () -> CreativeModeTab.builder().icon(() -> new ItemStack(NewItems.SAPPHIRE.get()))
                            .title(Component.translatable("creativetab.tutorial_tab"))
                            .displayItems(((pParms, pOutput) -> {
                                NewItems.ITEMS.getEntries().forEach(item -> pOutput.accept(item.get()));
                                NewBlocks.BLOCKS.getEntries().forEach(block -> pOutput.accept(block.get()));
                            })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
