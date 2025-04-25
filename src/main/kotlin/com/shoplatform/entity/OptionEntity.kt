package com.shoplatform.entity

import com.shoplatform.shared.enums.OptionType
import jakarta.persistence.*

@Entity(name = "option")
@Table(indexes = [Index(name = "idx_option", columnList = "shop_id, code")])
class OptionEntity(

    @ManyToOne
    val shop: ShopEntity,

    @Column(length = 20)
    val code: String,

    @ManyToOne
    var item: ItemEntity?,

    @Column(length = 100)
    var name: String?,

    @Enumerated(EnumType.STRING)
    var type: OptionType?,

    @OneToMany(mappedBy = "option", cascade = [(CascadeType.REMOVE)])
    val valueList: Set<OptionValueEntity>,

    ) : BaseEntity() {

    companion object {
        fun of(shop: ShopEntity, code: String): OptionEntity {
            return OptionEntity(
                shop = shop,
                code = code,
                item = null,
                name = null,
                type = null,
                valueList = emptySet(),
            )
        }
    }
}