package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import zz.dbrvkf.minecraft_study.datagen.loot.NewBlockLootTables;

import java.util.List;
import java.util.Set;

public class NewLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(NewBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
