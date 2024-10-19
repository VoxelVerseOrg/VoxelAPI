package me.nelmin.spigot.builder

import com.google.common.collect.Multimap
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.components.FoodComponent
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent
import org.bukkit.inventory.meta.components.ToolComponent
import org.jetbrains.annotations.ApiStatus

class ItemBuilder(private val itemStack: ItemStack) {

    constructor(material: Material, amount: Int = 1): this(ItemStack(material, amount))

    private val itemMeta = itemStack.getItemMeta()

    fun displayName(name: String?) = itemMeta?.setDisplayName(name)

    fun setItemName(name: String?) = itemMeta?.setItemName(name)

    fun setLore(lore: MutableList<String>?): ItemBuilder {
        itemMeta?.lore = lore
        return this
    }

    fun addEnchant(ench: Enchantment, level: Int, ignoreLevelRestriction: Boolean): Boolean?
            = itemMeta?.addEnchant(ench, level, ignoreLevelRestriction)

    fun removeEnchant(ench: Enchantment): Boolean? = itemMeta?.removeEnchant(ench)

    fun addItemFlags(vararg itemFlags: ItemFlag?) = itemMeta?.addItemFlags(*itemFlags)

    fun removeItemFlags(vararg itemFlags: ItemFlag?) = itemMeta?.removeItemFlags(*itemFlags)

    fun setHideTooltip(hideTooltip: Boolean): ItemBuilder {
        itemMeta?.isHideTooltip = hideTooltip
        return this
    }

    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        itemMeta?.isUnbreakable = unbreakable
        return this
    }

    fun setEnchantmentGlintOverride(override: Boolean?) = itemMeta?.setEnchantmentGlintOverride(override)

    fun setFireResistant(fireResistant: Boolean): ItemBuilder {
        itemMeta?.isFireResistant = fireResistant
        return this
    }

    fun setMaxStackSize(max: Int?) = itemMeta?.setMaxStackSize(max)

    fun setRarity(rarity: ItemRarity?) = itemMeta?.setRarity(rarity)

    @ApiStatus.Experimental
    fun setFood(food: FoodComponent?) = itemMeta?.setFood(food)

    @ApiStatus.Experimental
    fun setTool(tool: ToolComponent?) = itemMeta?.setTool(tool)

    @ApiStatus.Experimental
    fun setJukeboxPlayable(jukeboxPlayable: JukeboxPlayableComponent?): ItemBuilder {
        itemMeta?.jukeboxPlayable = jukeboxPlayable
        return this
    }

    fun addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean?
            = itemMeta?.addAttributeModifier(attribute, modifier)

    fun setAttributeModifiers(attributeModifiers: Multimap<Attribute, AttributeModifier>?): ItemBuilder {
        itemMeta?.attributeModifiers = attributeModifiers
        return this
    }

    fun removeAttributeModifier(attribute: Attribute): Boolean?
            = itemMeta?.removeAttributeModifier(attribute)

    fun removeAttributeModifier(slot: EquipmentSlot): Boolean?
            = itemMeta?.removeAttributeModifier(slot)

    fun removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean?
            = itemMeta?.removeAttributeModifier(attribute, modifier)

    @ApiStatus.Experimental
    fun setVersion(version: Int): ItemBuilder {
        itemMeta?.setVersion(version)
        return this
    }

    fun toItemStack(): ItemStack {
        itemStack.itemMeta = itemMeta
        return itemStack
    }
}