package dev.cortex.exceptions;

import dev.cortex.item.ItemBuilder;

public class InvalidMaterialProvidedException extends Exception {

    public InvalidMaterialProvidedException(ItemBuilder builder) {
        super("Invalid Material" + builder.getMaterial().name() + " provided in ItemBuilder");
    }
}
