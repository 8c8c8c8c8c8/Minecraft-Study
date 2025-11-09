package zz.dbrvkf.minecraft_study.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.util.Utils;

import java.util.concurrent.CompletableFuture;

public class NewPoiTypeProvider extends PoiTypeTagsProvider {
    public NewPoiTypeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider,
                              String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptional(Utils.modLoc("sound_poi"));
    }
}
