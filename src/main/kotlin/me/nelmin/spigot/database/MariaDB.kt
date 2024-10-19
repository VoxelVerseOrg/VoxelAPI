package me.nelmin.spigot.database

import me.nelmin.spigot.VoxelAPI
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MariaDB(
    private val address: String,
    private val port: Int,
    private val username: String,
    private val password: String,
    private val databaseName: String,
) {

    private val plugin = VoxelAPI.plugin
    private var connection: Connection? = null

    @Throws(SQLException::class)
    fun getConnection(): Connection {
        if (connection != null)
            return this.connection!!

        val url = "jdbc:mysql://$address:$port/$databaseName"

        this.connection = DriverManager.getConnection(url, username, password)
        return this.connection!!
    }

    fun disconnect() {
        this.connection?.close()
    }

    @Throws(SQLException::class)
    fun initializeDatabase() {
        plugin.logger.info("Initializing database...")
        getConnection().createStatement().apply {
            addBatch(
                "CREATE TABLE IF NOT EXISTS player_data (uuid varchar(36) primary key, name varchar(16), rank varchar(36), clan varchar(10), balance double, deaths int, kills int, chat_enabled boolean, msg_enabled boolean)"
            )

            addBatch(
                "CREATE TABLE IF NOT EXISTS friends (playerUUID varchar(36) primary key, friendUUID varchar(36))"
            )

            // Why are the upgrades an int? It because to do something like 20985 is colored name, 35546 is colored name and stage 1 explosion protection etc.
            addBatch(
                "CREATE TABLE IF NOT EXISTS clan (name varchar(10) primary key, tag varchar(4), ownerUUID varchar(36), max_members int, clan_upgrades int, base_upgrades int, spaceship_upgrades int)"
            )

            executeBatch()
            close()
        }
        plugin.logger.info("Database initialized!")
    }
}