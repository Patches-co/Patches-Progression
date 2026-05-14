package net.patches.progression.event;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;

public class ModItemModifiers {

    public static void registerModifiers() {

        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(item -> item instanceof ArmorItem, (builder, item) -> {
                ArmorItem armor = (ArmorItem) item;

                if (armor.getMaterial() == ArmorMaterials.CHAIN) {
                    builder.add(DataComponentTypes.MAX_DAMAGE, switch (armor.getType()) {
                        case HELMET -> 77;
                        case CHESTPLATE -> 112;
                        case LEGGINGS -> 105;
                        case BOOTS -> 91;
                        case BODY -> 100;
                    });
                }
                else if (armor.getMaterial() == ArmorMaterials.GOLD) {
                    builder.add(DataComponentTypes.MAX_DAMAGE, switch (armor.getType()) {
                        case HELMET -> 99;
                        case CHESTPLATE -> 144;
                        case LEGGINGS -> 135;
                        case BOOTS -> 117;
                        case BODY -> 125;
                    });
                }
            });
        });
    }
}
