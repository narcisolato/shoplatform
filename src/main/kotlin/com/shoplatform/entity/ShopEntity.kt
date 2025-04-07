package com.shoplatform.entity

import jakarta.persistence.*

@Entity(name = "shop")
@Table(indexes = [Index(name = "idx_shop", columnList = "code", unique = true)])
class ShopEntity(

    @Column(length = 20, unique = true)
    val code: String,

    @Column(length = 100)
    var name: String?,

    @OneToMany(mappedBy = "shop", cascade = [(CascadeType.ALL)])
    val categoryList: List<CategoryEntity>,

    @OneToMany(mappedBy = "shop", cascade = [CascadeType.ALL])
    val itemList: List<ItemEntity>,

    @OneToMany(mappedBy = "shop", cascade = [(CascadeType.ALL)])
    val optionList: List<OptionEntity>,

    @OneToMany(mappedBy = "shop", cascade = [(CascadeType.ALL)])
    val optionValueList: List<OptionValueEntity>

) : BaseEntity() {

    companion object {
        fun of(code: String): ShopEntity {
            return ShopEntity(
                code = code,
                name = null,
                categoryList = emptyList(),
                itemList = emptyList(),
                optionList = emptyList(),
                optionValueList = emptyList()
            )
        }
    }
}