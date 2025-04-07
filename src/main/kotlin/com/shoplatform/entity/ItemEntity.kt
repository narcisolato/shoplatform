package com.shoplatform.entity

import jakarta.persistence.*


@Entity(name = "item")
@Table(indexes = [Index(name = "idx_item", columnList = "shop_id, code")])
class ItemEntity(

    @ManyToOne
    val shop: ShopEntity,

    @ManyToOne
    var category: CategoryEntity?,

    @Column(length = 20)
    val code: String,

    @Column(length = 100)
    var name: String?,

    var price: Double?,

    @OneToMany(mappedBy = "item", cascade = [(CascadeType.REMOVE)])
    val optionList: List<OptionEntity>,

    ) : BaseEntity() {

    companion object {
        fun of(shop: ShopEntity, code: String): ItemEntity {
            return ItemEntity(
                shop = shop,
                category = null,
                code = code,
                name = null,
                price = 0.0,
                optionList = emptyList()
            )
        }
    }
}
