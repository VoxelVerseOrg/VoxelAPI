package me.nelmin.spigot.builder

import com.google.common.collect.Multimap
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.components.FoodComponent
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent
import org.bukkit.inventory.meta.components.ToolComponent
import org.bukkit.persistence.PersistentDataContainer
import org.jetbrains.annotations.ApiStatus

class VVItemStack : ItemStack() {

    private val itemMeta = super.getItemMeta()

    fun cloneItemMeta(): ItemMeta? = itemMeta?.clone()

    fun getPersistentDataContainer(): PersistentDataContainer?
            = itemMeta?.persistentDataContainer

    fun hasDisplayName(): Boolean? = itemMeta?.hasDisplayName()

    fun displayName(): String? = itemMeta?.displayName

    fun displayName(name: String?) = itemMeta?.setDisplayName(name)

    fun hasItemName(): Boolean? = itemMeta?.hasItemName()

    fun getItemName(): String? = itemMeta?.itemName

    fun setItemName(name: String?) = itemMeta?.setItemName(name)

    fun hasLore(): Boolean? = itemMeta?.hasLore()

    fun getLore(): MutableList<String>? = itemMeta?.lore

    fun setLore(lore: MutableList<String>?): VVItemStack {
        itemMeta?.lore = lore
        return this
    }

    fun hasCustomModelData(): Boolean? = itemMeta?.hasCustomModelData()

    fun getCustomModelData(): Int? = itemMeta?.customModelData

    fun setCustomModelData(data: Int?) = itemMeta?.setCustomModelData(data)

    fun hasEnchants(): Boolean? = itemMeta?.hasEnchants()

    fun hasEnchant(ench: Enchantment): Boolean? = itemMeta?.hasEnchant(ench)

    fun getEnchantLevel(ench: Enchantment): Int? = itemMeta?.getEnchantLevel(ench)

    fun getEnchants(): MutableMap<Enchantment, Int>? = itemMeta?.enchants

    fun addEnchant(ench: Enchantment, level: Int, ignoreLevelRestriction: Boolean): Boolean?
            = itemMeta?.addEnchant(ench, level, ignoreLevelRestriction)

    fun removeEnchant(ench: Enchantment): Boolean? = itemMeta?.removeEnchant(ench)

    fun hasConflictingEnchant(ench: Enchantment): Boolean? = itemMeta?.hasConflictingEnchant(ench)

    fun addItemFlags(vararg itemFlags: ItemFlag?) = itemMeta?.addItemFlags(*itemFlags)

    fun removeItemFlags(vararg itemFlags: ItemFlag?) = itemMeta?.removeItemFlags(*itemFlags)

    fun getItemFlags(): MutableSet<ItemFlag>? = itemMeta?.itemFlags

    fun hasItemFlag(flag: ItemFlag): Boolean? = itemMeta?.hasItemFlag(flag)

    fun isHideTooltip(): Boolean? = itemMeta?.isHideTooltip

    fun setHideTooltip(hideTooltip: Boolean): VVItemStack {
        itemMeta?.isHideTooltip = hideTooltip
        return this
    }

    fun isUnbreakable(): Boolean? = itemMeta?.isUnbreakable

    fun setUnbreakable(unbreakable: Boolean): VVItemStack {
        itemMeta?.isUnbreakable = unbreakable
        return this
    }

    fun hasEnchantmentGlintOverride(): Boolean? = itemMeta?.hasEnchantmentGlintOverride()

    fun getEnchantmentGlintOverride(): Boolean? = itemMeta?.enchantmentGlintOverride

    fun setEnchantmentGlintOverride(override: Boolean?) = itemMeta?.setEnchantmentGlintOverride(override)

    fun isFireResistant(): Boolean? = itemMeta?.isFireResistant

    fun setFireResistant(fireResistant: Boolean): VVItemStack {
        itemMeta?.isFireResistant = fireResistant
        return this
    }

    fun hasMaxStackSize(): Boolean? = itemMeta?.hasMaxStackSize()

    fun setMaxStackSize(max: Int?) = itemMeta?.setMaxStackSize(max)

    fun hasRarity(): Boolean? = itemMeta?.hasRarity()

    fun getRarity(): ItemRarity? = itemMeta?.rarity

    fun setRarity(rarity: ItemRarity?) = itemMeta?.setRarity(rarity)

    fun hasFood(): Boolean? = itemMeta?.hasFood()

    @ApiStatus.Experimental
    fun getFood(): FoodComponent? = itemMeta?.food

    @ApiStatus.Experimental
    fun setFood(food: FoodComponent?) = itemMeta?.setFood(food)

    fun hasTool(): Boolean? = itemMeta?.hasTool()

    @ApiStatus.Experimental
    fun getTool(): ToolComponent? = itemMeta?.tool

    @ApiStatus.Experimental
    fun setTool(tool: ToolComponent?) = itemMeta?.setTool(tool)

    fun hasJukeboxPlayable(): Boolean? = itemMeta?.hasJukeboxPlayable()

    @ApiStatus.Experimental
    fun getJukeboxPlayable(): JukeboxPlayableComponent? = itemMeta?.jukeboxPlayable

    @ApiStatus.Experimental
    fun setJukeboxPlayable(jukeboxPlayable: JukeboxPlayableComponent?): VVItemStack {
        itemMeta?.jukeboxPlayable = jukeboxPlayable
        return this
    }

    fun hasAttributeModifiers(): Boolean? = itemMeta?.hasAttributeModifiers()

    fun getAttributeModifiers(): Multimap<Attribute, AttributeModifier>?
            = itemMeta?.getAttributeModifiers()

    fun getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier>?
            = itemMeta?.getAttributeModifiers(slot)

    fun getAttributeModifiers(attribute: Attribute): MutableCollection<AttributeModifier>?
            = itemMeta?.getAttributeModifiers(attribute)

    fun addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean?
            = itemMeta?.addAttributeModifier(attribute, modifier)

    fun setAttributeModifiers(attributeModifiers: Multimap<Attribute, AttributeModifier>?): VVItemStack {
        itemMeta?.attributeModifiers = attributeModifiers
        return this
    }

    fun removeAttributeModifier(attribute: Attribute): Boolean?
            = itemMeta?.removeAttributeModifier(attribute)

    fun removeAttributeModifier(slot: EquipmentSlot): Boolean?
            = itemMeta?.removeAttributeModifier(slot)

    fun removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean?
            = itemMeta?.removeAttributeModifier(attribute, modifier)

    fun getAsString(): String? = itemMeta?.asString

    fun getAsComponentString(): String? = itemMeta?.asComponentString

    @ApiStatus.Experimental
    fun setVersion(version: Int): VVItemStack {
        itemMeta?.setVersion(version)
        return this
    }

    fun modify(): VVItemStack {
        super.setItemMeta(itemMeta)
        return this
    }
}