package zz.dbrvkf.minecraft_study;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.block.entity.NewBlockEntities;
import zz.dbrvkf.minecraft_study.entity.NewEntities;
import zz.dbrvkf.minecraft_study.entity.client.NewModelLayers;
import zz.dbrvkf.minecraft_study.entity.client.RhinoModel;
import zz.dbrvkf.minecraft_study.entity.client.RhinoRenderer;
import zz.dbrvkf.minecraft_study.screen.GemPolishingStationScreen;
import zz.dbrvkf.minecraft_study.screen.NewMenuTypes;
import zz.dbrvkf.minecraft_study.tab.NewCreativeTabs;
import zz.dbrvkf.minecraft_study.item.NewItems;
import zz.dbrvkf.minecraft_study.loot.NewLootModifiers;
import zz.dbrvkf.minecraft_study.sound.NewSounds;
import zz.dbrvkf.minecraft_study.villager.NewVillagers;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinecraftStudy.MOD_ID)
public class MinecraftStudy {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "minecraft_study";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MinecraftStudy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        NewItems.register(modEventBus);
        NewCreativeTabs.register(modEventBus);
        NewBlocks.register(modEventBus);
        NewLootModifiers.register(modEventBus);
        NewVillagers.register(modEventBus);
        NewSounds.register(modEventBus);
        NewEntities.register(modEventBus);
        NewBlockEntities.register(modEventBus);
        NewMenuTypes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NewBlocks.CATMINT.getId(), NewBlocks.POTTED_CATMINT);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(NewItems.SAPPHIRE);
            event.accept(NewItems.RAW_SAPPHIRE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            EntityRenderers.register(NewEntities.RHINO.get(),
                    (EntityRendererProvider.Context pContext) -> new RhinoRenderer(pContext, new RhinoModel<>(pContext.bakeLayer(NewModelLayers.RHINO_LAYER)), 2f));
            MenuScreens.register(NewMenuTypes.GEM_POLISHING_MENU.get(), GemPolishingStationScreen::new);
        }
    }
}
