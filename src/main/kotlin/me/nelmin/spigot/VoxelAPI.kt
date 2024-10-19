package me.nelmin.spigot

import me.nelmin.spigot.configs.ConfigurationInterface
import me.nelmin.spigot.database.MariaDB
import me.nelmin.spigot.database.models.PlayerData
import org.bukkit.plugin.java.JavaPlugin

class VoxelAPI {
    companion object {
        // General
        lateinit var plugin: JavaPlugin
        lateinit var mariadb: MariaDB

        // Database
        lateinit var loadedPlayerData: MutableSet<PlayerData>

        // Configs
        lateinit var messagesConfig: ConfigurationInterface
    }
}