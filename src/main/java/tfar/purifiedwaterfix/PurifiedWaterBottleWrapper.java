package tfar.purifiedwaterfix;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PurifiedWaterBottleWrapper extends FluidHandlerItemStackSimple {
    public PurifiedWaterBottleWrapper(@Nonnull ItemStack container) {
            super(container, BottleConfig.capacity);
    }

    @Nullable
    public FluidStack getFluid() {
        return FluidRegistry.getFluidStack(PurifiedWaterFix.PURIFIED_WATER, this.capacity);
    }

    protected void setFluid(FluidStack fluid) {
        // do nothing; this is already full
    }
    
    public boolean canFillFluidType(FluidStack fluid) {
        return false;
    }

    public boolean canDrainFluidType(FluidStack fluid) {
        return fluid.getFluid() == FluidRegistry.getFluid(PurifiedWaterFix.PURIFIED_WATER);
    }
    
    protected void setContainerToEmpty() {
        this.container = new ItemStack(Items.GLASS_BOTTLE);
    }
}
