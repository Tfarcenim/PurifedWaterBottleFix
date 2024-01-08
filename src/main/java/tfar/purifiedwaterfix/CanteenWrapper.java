package tfar.purifiedwaterfix;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import toughasnails.api.thirst.WaterType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CanteenWrapper implements IFluidHandlerItem, ICapabilityProvider {
    @Nonnull
    protected ItemStack container;

    public CanteenWrapper(@Nonnull ItemStack container) {
        this.container = container;
    }

    @Nonnull
    @Override
    public ItemStack getContainer() {
        return container;
    }

    @Nullable
    public FluidStack getFluid() {
        int meta = container.getMetadata();
        int type = meta & 3;


        WaterType waterType = type > 0 ? WaterType.values()[type - 1] : null;
        if (waterType == null) return null;
        int fill = 3 - meta / 4;//should be 1 - 3
        switch (waterType) {
            case RAIN: return FluidRegistry.getFluidStack("rain", fill * BottleConfig.capacity);
            case NORMAL:return new FluidStack(FluidRegistry.WATER,fill * BottleConfig.capacity);
            case PURIFIED: return FluidRegistry.getFluidStack("purified_water", fill * BottleConfig.capacity);
        }
        return null;
    }

    protected int getSwigs() {
        int meta = container.getMetadata();
        if (meta == 0) return 0;
        int fill = 3 - meta / 4;//should be 1 - 3
        return fill;
    }

    protected void setFluid(@Nullable FluidStack fluidStack) {
        if (fluidStack == null)
            container.setItemDamage(0);//this is empty
        else {

            Fluid fluid = fluidStack.getFluid();
            int fill = fluidStack.amount / BottleConfig.capacity;

            int meta1 = 12 - fill * 4 + 1;

            int type = 0;
            if (fluid == FluidRegistry.WATER) {
            } else if (fluid.getName().equals("purified_water")) {
                type = 1;
            } else if (fluid.getName().equals("rain")) {
                type = 2;
            }
            container.setItemDamage(type + meta1);
        }
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        int fill = getSwigs();
        return new FluidTankProperties[]{new FluidTankProperties(getFluid(), fill * BottleConfig.capacity)};
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        FluidStack contained = getFluid();
        if (container.getCount() != 1 || resource == null || resource.amount < BottleConfig.capacity || !isFluidValid(resource)) {
            return 0;
        }

        if (contained == null) {
            if (doFill)
                setFluid(new FluidStack(resource.getFluid(),BottleConfig.capacity * 3));
            return BottleConfig.capacity * 3;
        }

        if (!contained.isFluidEqual(resource)) return 0;

        int swigs = contained.amount / BottleConfig.capacity;

        if (swigs >= 3) return 0;

        int incomingSwings = resource.amount / BottleConfig.capacity;

        int maxSwigFill = Math.min(3 - swigs,incomingSwings);

        if (doFill) {
            int newSwigs = swigs + maxSwigFill;
            setFluid(new FluidStack(contained.getFluid(),newSwigs * BottleConfig.capacity));
        }

        return maxSwigFill * BottleConfig.capacity;
    }

    public boolean isFluidValid(FluidStack fluidStack) {
        Fluid fluid = fluidStack.getFluid();
        return fluid == FluidRegistry.WATER || fluid.getName().equals("purified_water") || fluid.getName().equals("rain");
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(getFluid())) {
            return null;
        }
        return drain(resource.amount,doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        if (container.getCount() != 1 || maxDrain < BottleConfig.capacity) {
            return null;
        }

        FluidStack fluidStack = getFluid();
        if (fluidStack == null) return null;

        int currentSwigs = fluidStack.amount / BottleConfig.capacity;
        int drainSwigs = maxDrain / BottleConfig.capacity;

        int removedSwigs = Math.min(currentSwigs,drainSwigs);

        if (doDrain) {
            if (removedSwigs == currentSwigs) {
                setFluid(null);
            } else {
                setFluid( new FluidStack(fluidStack.getFluid(),(currentSwigs - removedSwigs) * BottleConfig.capacity));
            }
        }
        return new FluidStack(fluidStack.getFluid(),removedSwigs * BottleConfig.capacity);

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
