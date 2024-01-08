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
    public static final String MOD_ID = "purifiedwaterfix";

    public static final String TAN = "toughasnails";

    public static final ResourceLocation PURIFIED_WATER = new ResourceLocation(TAN,"purified_water_bottle");
    public static final ResourceLocation CANTEEN = new ResourceLocation(TAN,"canteen");


    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        ResourceLocation registryName = stack.getItem().getRegistryName();
        if (PURIFIED_WATER.equals(registryName)) {
            event.addCapability(new ResourceLocation(MOD_ID,"purified_water_bottle"),new FilledFluidBottleWrapper(stack));
        } else if (CANTEEN.equals(registryName)) {
            event.addCapability(new ResourceLocation(MOD_ID,"canteen"),new CanteenWrapper(stack));
        } else if (stack.getItem() == Items.GLASS_BOTTLE) {
            event.addCapability(new ResourceLocation(MOD_ID,"glass_bottle"),new EmptyBottleWrapper(stack));
        }
    }
}
