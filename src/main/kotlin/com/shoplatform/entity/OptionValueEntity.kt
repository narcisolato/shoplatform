package com.shoplatform.entity

import jakarta.persistence.*

@Entity(name = "option_value")
@Table(indexes = [Index(name = "idx_option_value", columnList = "shop_id, code")])
class OptionValueEntity(

    @ManyToOne
    val shop: ShopEntity,

    @Column(length = 20)
    val code: String,

    @ManyToOne
    var option: OptionEntity?,

    @Column(length = 20)
    var name: String?,

    var price: Double?,

    ) : BaseEntity() {

    companion object {
        fun of(shop: ShopEntity, code: String): OptionValueEntity {
            return OptionValueEntity(
                shop = shop,
                code = code,
                option = null,
                name = null,
                price = 0.0,
            )
        }
    }
}