package me.nelmin.spigot.hooks

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer

object VaultHook {
    private var economy: Economy? = null

    private fun setupEconomy() {
        val rsp = Bukkit.getServicesManager().getRegistration(
            Economy::class.java
        )

        if (rsp != null) economy = rsp.provider
    }

    fun hasEconomy(): Boolean {
        return economy != null
    }

    fun getBalance(target: OfflinePlayer?): Double {
        if (!hasEconomy()) throw UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.")

        return economy?.getBalance(target) ?: 0.0
    }

    fun withdraw(target: OfflinePlayer?, amount: Double): EconomyResponse {
        if (!hasEconomy()) throw UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.")

        return economy?.withdrawPlayer(target, amount) ?: EconomyResponse(
            0.0,
            0.0,
            EconomyResponse.ResponseType.FAILURE,
            "Economy service not available."
        )
    }

    fun deposit(target: OfflinePlayer?, amount: Double): EconomyResponse {
        if (!hasEconomy()) throw UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.")

        return economy?.depositPlayer(target, amount) ?: EconomyResponse(
            0.0,
            0.0,
            EconomyResponse.ResponseType.FAILURE,
            "Economy service not available."
        )
    }

    fun formatCurrencySymbol(amount: Double): String {
        if (!hasEconomy()) throw UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.")

        return economy!!.format(amount)
    }

    init {
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            setupEconomy()
        }
    }
}