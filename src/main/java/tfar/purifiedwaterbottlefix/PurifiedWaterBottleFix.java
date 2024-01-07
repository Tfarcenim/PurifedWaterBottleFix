package tfar.purifiedwaterbottlefix;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = PurifiedWaterBottleFix.MOD_ID)
@Mod.EventBusSubscriber
public class PurifiedWaterBottleFix {
    public static final String MOD_ID = "purifiedwaterbottlefix";

    public static final String TAN = "toughasnails";

    public static final ResourceLocation PURIFIED_WATER = new ResourceLocation(TAN,"purified_water_bottle");

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        if (PURIFIED_WATER.equals(stack.getItem().getRegistryName())) {
            event.addCapability(new ResourceLocation(MOD_ID,"puified_water_bottle"),new FilledFluidBottleWrapper(stack));
        }
    }
}
