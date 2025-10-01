package zz.dbrvkf.minecraft_study.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.util.NewTags;

import java.util.List;

public class NewToolTiers {
    public static final Tier SAPPHIRE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    NewTags.Blocks.NEEDS_SAPPHIRE_TOOL, () -> Ingredient.of(NewItems.SAPPHIRE.get())),
            new ResourceLocation(MinecraftStudy.MOD_ID, "sapphire"), List.of(Tiers.NETHERITE), List.of());
}
