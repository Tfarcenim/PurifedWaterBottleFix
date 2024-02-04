package tfar.purifiedwaterfix;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Constants.MOD_ID)
@Mod.EventBusSubscriber
public class PurifiedWaterFix {

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        ResourceLocation registryName = stack.getItem().getRegistryName();
        if (Constants.PURIFIED_WATER_TAN.equals(registryName)) {
            event.addCapability(Constants.PURIFIED_WATER_WRAPPER, new PurifiedWaterBottleWrapper(stack));
            
        } else if (Constants.CANTEEN_TAN.equals(registryName)) {
            event.addCapability(Constants.CANTEEN_WRAPPER, new CanteenWrapper(stack));
            
        } else if (stack.getItem() == Items.GLASS_BOTTLE) {
            event.addCapability(Constants.GLASS_BOTTLE, new GlassBottleWrapperWrapper(stack));
        }
    }
}
