package dev.cortex.cortexcore.util.configuration;

import dev.cortex.cortexcore.CortexCore;
import dev.cortex.cortexcore.util.logging.CortexLog;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class YamlConfig {

    private final @NotNull String filePath;
    private File file;
    private FileConfiguration fileConfiguration;

    /**
     * <p>Create a new yml file in the plugins' folder on the server.
     * The file must be in the jar's resources!</p>
     * @param filePath The {@link String} path to the file (with or without .yml extension)
     */
    public YamlConfig(final @NotNull String filePath) {
        this.filePath = filePath.endsWith(".yml") ? filePath : filePath + ".yml";
        save();
        reload();
    }

    private File file() {
        return new File(CortexCore.getPlugin().getDataFolder(), filePath);
    }

    /**
     * Gets the current {@link FileConfiguration}
     * @return {@link FileConfiguration}
     */
    public FileConfiguration options() {
        return fileConfiguration;
    }

    /**
     * Saves any changes from the {@link FileConfiguration} to the {@link File} on the server
     */
    public void saveFile() {
        if (fileConfiguration == null || file == null) {
            return;
        }

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            CortexLog.log(e);
        }
    }

    /**
     * Saves the {@link File} from resources to the server
     */
    public void save() {
        if (file == null) {
            file = file();
        }

        if (file.exists()) {
            return;
        }

        final JavaPlugin plugin = CortexCore.getPlugin();
        if (plugin.getResource(filePath) == null) {
            return;
        }

        plugin.saveResource(filePath, false);
    }

    /**
     * Loads the {@link FileConfiguration} from the {@link File} on the server
     */
    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file());
    }

}
