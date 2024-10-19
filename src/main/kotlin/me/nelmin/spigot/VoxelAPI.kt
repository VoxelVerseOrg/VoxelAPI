package me.nelmin.spigot

import org.bukkit.plugin.java.JavaPlugin

class VoxelAPI(plugin: JavaPlugin?) {
    init {
        Companion.plugin = plugin
    }

    companion object {
        var plugin: JavaPlugin? = null
    }
}
