# VoxelAPI

### What is VoxelAPI?

VoxelAPI consits of different modules, to prevent duplicate code in different VoxelVerse Plugins.



### What modules exist?

```kts
implementation("me.nelmin.spigot:VoxelAPI-Core:<VERSION>")
```

Replace <VERSION> with the latest version.



### How to use?

```kotlin
val loadedUserData: MutableSet<PlayerData> = mutableSetOf()

val messagesConfig: ConfigInterface = ...

val voxelAPI = VoxelAPI

fun onEnable() {
    voxelAPI.plugin = this // this: the plugin
    voxelAPI.mariadb = MariaDB(hostAdress, port, username, password, databaseName)
    voxelAPI.loadedUserData = loadedUserData
    voxelAPI.messagesConfig = messagesConfig
}
```


