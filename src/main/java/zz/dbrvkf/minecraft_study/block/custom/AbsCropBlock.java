package zz.dbrvkf.minecraft_study.block.custom;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public abstract class AbsCropBlock extends CropBlock {
    public AbsCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return null;
    }

    @Override
    public int getMaxAge() {
        return 0;
    }
}
