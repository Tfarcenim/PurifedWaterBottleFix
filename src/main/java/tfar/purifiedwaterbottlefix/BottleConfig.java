package tfar.purifiedwaterbottlefix;

import net.minecraftforge.common.config.Config;

@Config(modid = PurifiedWaterBottleFix.MOD_ID)
public class BottleConfig {

    @Config.RangeInt(min = 1)
    public static int capacity = 250;

}
