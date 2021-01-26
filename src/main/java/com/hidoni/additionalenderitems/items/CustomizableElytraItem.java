package com.hidoni.additionalenderitems.items;

import com.hidoni.additionalenderitems.AdditionalEnderItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class CustomizableElytraItem extends ElytraItem implements IDyeableArmorItem
{
    public CustomizableElytraItem(Properties builder)
    {
        super(builder);
    }

    @Override
    public int getColor(ItemStack stack)
    {
        CompoundNBT compoundnbt = stack.getChildTag("display");
        if (compoundnbt != null)
        {
            return compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : 16777215;
        }
        compoundnbt = stack.getChildTag("BlockEntityTag");
        if (compoundnbt != null)
        {
            return DyeColor.byId(compoundnbt.getInt("Base")).getColorValue();
        }
        return 16777215;
    }

    @Nullable
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack)
    {
        return EquipmentSlotType.CHEST;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        BannerItem.appendHoverTextFromTileEntityTag(stack, tooltip);
    }

    @OnlyIn(Dist.CLIENT)
    public static ResourceLocation getTextureLocation(BannerPattern bannerIn) {
        return new ResourceLocation(AdditionalEnderItems.MOD_ID, "entity/elytra_banner/" + bannerIn.getFileName());
    }
}