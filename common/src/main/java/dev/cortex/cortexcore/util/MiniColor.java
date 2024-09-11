package dev.cortex.cortexcore.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public enum MiniColor {

    CHAT,

    INVENTORY(
            StandardTags.color(),
            StandardTags.decorations(),
            StandardTags.gradient(),
            StandardTags.rainbow(),
            StandardTags.translatable(),
            StandardTags.font(),
            StandardTags.reset()
    ),

    CONSOLE(
            StandardTags.color(),
            StandardTags.reset()
    );

    private final @NotNull MiniMessage miniMessage;

    MiniColor(final @NotNull TagResolver... tags) {
        miniMessage = MiniMessage.builder()
                .tags(TagResolver.builder().resolvers(tags).build())
                .build();
    }

    MiniColor() {
        miniMessage = MiniMessage.miniMessage();
    }

    public @NotNull Component deserialize(final @NotNull String input) {
        return this == INVENTORY
                ? miniMessage.deserialize(input).decoration(TextDecoration.ITALIC, false)
                : miniMessage.deserialize(input);
    }

    public @NotNull List<Component> deserialize(final @NotNull List<String> input) {
        final List<Component> components = new ArrayList<>();
        input.forEach(string -> components.add(deserialize(string)));
        return components;
    }

    public @NotNull List<Component> deserialize(final @NotNull String... input) {
        return deserialize(List.of(input));
    }

    public @NotNull String serialize(final @NotNull Component input) {
        return miniMessage.serialize(input);
    }

    public @NotNull List<String> serialize(final @NotNull List<Component> input) {
        final List<String> strings = new ArrayList<>();
        input.forEach(component -> strings.add(miniMessage.serialize(component)));
        return strings;
    }

    public @NotNull List<String> serialize(final @NotNull Component... components) {
        return serialize(List.of(components));
    }

    public @NotNull String stripTags(final @NotNull String input) {
        return miniMessage.stripTags(input);
    }

    public @NotNull List<String> stripTags(final @NotNull List<String> input) {
        final List<String> stripped = new ArrayList<>();
        input.forEach(string -> stripped.add(miniMessage.stripTags(string)));
        return stripped;
    }

    public @NotNull List<String> stripTags(final @NotNull String... input) {
        return stripTags(List.of(input));
    }

}
