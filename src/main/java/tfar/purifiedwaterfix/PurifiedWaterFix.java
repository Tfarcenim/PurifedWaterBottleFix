package tfar.purifiedwaterfix;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = PurifiedWaterFix.MOD_ID)
@Mod.EventBusSubscriber
public class PurifiedWaterFix {
    
    // mod names
    public static final String MOD_ID = "purifiedwaterfix";
    public static final String TAN = "toughasnails";

    // resources
    public static final String PURIFIED_WATER = "purified_water";
    private static final String PURIFIED_WATER_BOTTLE = "purified_water_bottle";
    public static final ResourceLocation PURIFIED_WATER_TAN = new ResourceLocation(TAN, PURIFIED_WATER_BOTTLE);
    public static final ResourceLocation PURIFIED_WATER_WRAPPER = new ResourceLocation(MOD_ID, PURIFIED_WATER_BOTTLE);

    private static final String CANTEEN = "canteen";
    public static final ResourceLocation CANTEEN_TAN = new ResourceLocation(TAN, CANTEEN);
    public static final ResourceLocation CANTEEN_WRAPPER = new ResourceLocation(MOD_ID, CANTEEN);

    public static final ResourceLocation GLASS_BOTTLE = new ResourceLocation(MOD_ID, "glass_bottle");
    
    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        ResourceLocation registryName = stack.getItem().getRegistryName();
        if (PURIFIED_WATER_TAN.equals(registryName)) {
            event.addCapability(PURIFIED_WATER_WRAPPER, new PurifiedWaterBottleWrapper(stack));
            
        } else if (CANTEEN_TAN.equals(registryName)) {
            event.addCapability(CANTEEN_WRAPPER, new CanteenWrapper(stack));
            
        } else if (stack.getItem() == Items.GLASS_BOTTLE) {
            event.addCapability(GLASS_BOTTLE, new GlassBottleWrapperWrapper(stack));
        }
    }
}
