package tfar.purifiedwaterfix;

import net.minecraftforge.common.config.Config;

@Config(modid = Constants.MOD_ID)
public class BottleConfig {

    @Config.RangeInt(min = 1)
    public static int capacity = 250;

}
