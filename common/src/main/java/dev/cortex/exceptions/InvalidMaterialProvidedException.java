package dev.cortex.exceptions;

import dev.cortex.item.ItemBuilder;
import org.bukkit.Material;

import javax.annotation.Nullable;

public class InvalidMaterialProvidedException extends Exception {

    public InvalidMaterialProvidedException(@Nullable Material material) {
        super("Invalid Material" + material + " provided to ItemBuilder");
    }
}
