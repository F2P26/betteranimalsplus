package its_meow.betteranimalsplus.compat.curios;

import its_meow.betteranimalsplus.common.item.ItemBearCape;
import its_meow.betteranimalsplus.common.item.ItemCape;
import its_meow.betteranimalsplus.common.item.ItemWolfCape;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CuriosModCompat {

    public static void subscribe(IEventBus modBus) {
        modBus.addListener(CuriosModCompat::interModEnqueue);
        MinecraftForge.EVENT_BUS.register(CuriosModCompat.class);
        ItemCape.can_equip = (stack, armorType, entity) -> !CuriosAPI.getCurioEquipped(s -> s.getItem() instanceof ItemCape, (PlayerEntity) entity).isPresent();
    }

    public static void interModEnqueue(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("back"));
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        Item item = stack.getItem();
        if(item instanceof ItemWolfCape || item instanceof ItemBearCape) {
            event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
                final LazyOptional<ICurio> curio = LazyOptional.of(() -> new CurioCape(item instanceof ItemWolfCape ? "wolf" : "bear", stack));

                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                    return CuriosCapability.ITEM.orEmpty(cap, curio);
                }
            });
        }
    }

}
