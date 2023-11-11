package me.ilucah.advancedarmor.boosting.providers;

import dev.drawethree.xprison.XPrison;
import dev.drawethree.xprison.autosell.api.events.XPrisonAutoSellEvent;
import dev.drawethree.xprison.autosell.api.events.XPrisonSellAllEvent;
import dev.drawethree.xprison.multipliers.enums.MultiplierType;
import me.ilucah.advancedarmor.AdvancedArmor;
import me.ilucah.advancedarmor.armor.BoostType;
import me.ilucah.advancedarmor.boosting.model.BiBoostProvider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class XPCMoneyProvider extends BiBoostProvider<XPrisonAutoSellEvent, XPrisonSellAllEvent> {

    public XPCMoneyProvider(AdvancedArmor instance) {
        super(instance, BoostType.MONEY);
        instance.getAPI().registerProviders(
                this,
                XPrisonAutoSellEvent.class,
                XPrisonSellAllEvent.class);
    }

    @Override
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBoostT(XPrisonAutoSellEvent event) {
        double totalAmount = event.getItemsToSell().values().stream().mapToDouble(Double::doubleValue).sum();
        if (XPrison.getInstance().getAutoSell().isMultipliersModuleEnabled())
            totalAmount = (long) XPrison.getInstance().getMultipliers().getApi().getTotalToDeposit(event.getPlayer(), totalAmount, MultiplierType.SELL);
        double amountToGive = resolveNewAmount(event.getPlayer(), totalAmount) - totalAmount;
        XPrison.getInstance().getEconomy().depositPlayer(event.getPlayer(), amountToGive);
    }

    @Override
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBoostV(XPrisonSellAllEvent event) {
        double totalAmount = event.getItemsToSell().values().stream().mapToDouble(Double::doubleValue).sum();
        if (XPrison.getInstance().getAutoSell().isMultipliersModuleEnabled())
            totalAmount = (long) XPrison.getInstance().getMultipliers().getApi().getTotalToDeposit(event.getPlayer(), totalAmount, MultiplierType.SELL);
        double amountToGive = resolveNewAmount(event.getPlayer(), totalAmount) - totalAmount;
        XPrison.getInstance().getEconomy().depositPlayer(event.getPlayer(), amountToGive);
    }
}