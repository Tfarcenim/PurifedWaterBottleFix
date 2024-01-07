package tfar.purifiedwaterbottlefix;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FilledFluidBottleWrapper implements IFluidHandlerItem, ICapabilityProvider {
    @Nonnull
    protected ItemStack container;

    public FilledFluidBottleWrapper(@Nonnull ItemStack container) {
        this.container = container;
    }

    @Nonnull
    @Override
    public ItemStack getContainer() {
        return container;
    }

    @Nullable
    public FluidStack getFluid() {
        Item item = container.getItem();
        if (item.getRegistryName().equals(PurifiedWaterBottleFix.PURIFIED_WATER)) {
            return FluidRegistry.getFluidStack("purified_water", BottleConfig.capacity);
        }
        return null;
    }

    /**
     * @deprecated use the NBT-sensitive version {@link #setFluid(FluidStack)}
     */
    @Deprecated
    protected void setFluid(@Nullable Fluid fluid) {
        setFluid(new FluidStack(fluid, BottleConfig.capacity));
    }

    protected void setFluid(@Nullable FluidStack fluidStack) {
        if (fluidStack == null)
            container = new ItemStack(Items.GLASS_BOTTLE);
        else
            container = FluidUtil.getFilledBucket(fluidStack);
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new FluidTankProperties[]{new FluidTankProperties(getFluid(), Fluid.BUCKET_VOLUME)};
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;//can't fill an already filled bottle
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if (container.getCount() != 1 || resource == null || resource.amount < BottleConfig.capacity) {
            return null;
        }

        FluidStack fluidStack = getFluid();
        if (fluidStack != null && fluidStack.isFluidEqual(resource)) {
            if (doDrain) {
                setFluid((FluidStack) null);
            }
            return fluidStack;
        }

        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        if (container.getCount() != 1 || maxDrain < BottleConfig.capacity) {
            return null;
        }

        FluidStack fluidStack = getFluid();
        if (fluidStack != null) {
            if (doDrain) {
                setFluid((FluidStack) null);
            }
            return fluidStack;
        }

        return null;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;
    }

    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY.cast(this);
        }
        return null;
    }
}
