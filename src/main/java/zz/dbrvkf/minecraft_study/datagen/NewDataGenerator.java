package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import zz.dbrvkf.minecraft_study.MinecraftStudy;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MinecraftStudy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NewDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new NewRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), NewLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new NewEnLangProvider(packOutput, MinecraftStudy.MOD_ID, "en_us"));
        generator.addProvider(event.includeClient(), new NewBlockStateProvider(packOutput, MinecraftStudy.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeClient(), new NewItemModelProvider(packOutput, MinecraftStudy.MOD_ID, existingFileHelper));

        NewBlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(),
                new NewBlockTagProvider(packOutput, lookupProvider, MinecraftStudy.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeServer(),
                new NewItemTagsProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(),
                        MinecraftStudy.MOD_ID, existingFileHelper));
    }
}
