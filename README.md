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
fun onEnable() {
    VoxelAPI.plugin = this // this: the plugin
    VoxelAPI.mariadb = MariaDB()
}
```


