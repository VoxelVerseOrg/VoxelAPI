package me.nelmin.spigot.configs

interface ConfigurationInterface {
    fun <T> get(path: String): T?
    fun load()
    fun save()
    fun set(path: String, value: Any?, override: Boolean)
}