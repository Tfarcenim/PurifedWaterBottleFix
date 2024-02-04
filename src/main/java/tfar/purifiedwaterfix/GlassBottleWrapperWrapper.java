package tfar.purifiedwaterfix;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import snownee.cuisine.internal.capabilities.GlassBottleHandler;

import javax.annotation.Nonnull;

public class GlassBottleWrapperWrapper extends GlassBottleHandler.GlassBottleWrapper {
    
    private static final Item PURIFIED_WATER = Item.REGISTRY.getObject(PurifiedWaterFix.PURIFIED_WATER_TAN);

    public GlassBottleWrapperWrapper(@Nonnull ItemStack container) {
        super(container);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid) {
        return fluid.getFluid().getName().equals(PurifiedWaterFix.PURIFIED_WATER) || super.canFillFluidType(fluid);
    }

    @Override
    protected void setFluid(FluidStack fluid)
    {
        if (fluid.getFluid().getName().equals(PurifiedWaterFix.PURIFIED_WATER)) {
            container = new ItemStack(PURIFIED_WATER);
        } else {
            super.setFluid(fluid);
        }
    }
}
