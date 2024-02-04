package tfar.purifiedwaterfix;

import net.minecraft.util.ResourceLocation;

public class Constants {
    // mod names
    public static final String MOD_ID = "purifiedwaterfix";
    public static final String TAN = "toughasnails";
    
    // resources
    public static final ResourceLocation GLASS_BOTTLE = new ResourceLocation(MOD_ID, "glass_bottle");
    
    public static final String PURIFIED_WATER = "purified_water";
    private static final String PURIFIED_WATER_BOTTLE = "purified_water_bottle";
    public static final ResourceLocation PURIFIED_WATER_WRAPPER = new ResourceLocation(MOD_ID, PURIFIED_WATER_BOTTLE);
    public static final ResourceLocation PURIFIED_WATER_TAN = new ResourceLocation(TAN, PURIFIED_WATER_BOTTLE);
    
    private static final String CANTEEN = "canteen";
    public static final ResourceLocation CANTEEN_WRAPPER = new ResourceLocation(MOD_ID, CANTEEN);
    public static final ResourceLocation CANTEEN_TAN = new ResourceLocation(TAN, CANTEEN);
}
