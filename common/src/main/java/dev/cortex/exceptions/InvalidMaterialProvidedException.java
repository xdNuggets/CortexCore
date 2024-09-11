package dev.cortex.exceptions;

import dev.cortex.item.ItemBuilder;
import org.bukkit.Material;

public class InvalidMaterialProvidedException extends Exception {

    public InvalidMaterialProvidedException(Material material) {
        super("Invalid Material" + material + " provided to ItemBuilder");
    }
}
