package com.shoplatform.entity

import jakarta.persistence.*

@Entity(name = "category")
@Table(indexes = [Index(name = "idx_category", columnList = "shop_id, code")])
class CategoryEntity(

    @ManyToOne
    val shop: ShopEntity,

    @ManyToOne
    var parentCategory: CategoryEntity?,

    @Column(length = 20)
    val code: String,

    @Column(length = 100)
    var name: String?,

    @OneToMany(mappedBy = "category", cascade = [(CascadeType.REMOVE)])
    val itemList: List<ItemEntity>,

    @OneToMany(mappedBy = "parentCategory", cascade = [CascadeType.REMOVE])
    val childCategoryList: List<CategoryEntity>

) : BaseEntity() {

    companion object {
        fun of(shop: ShopEntity, code: String): CategoryEntity {
            return CategoryEntity(
                shop = shop,
                code = code,
                name = null,
                parentCategory = null,
                itemList = emptyList(),
                childCategoryList = emptyList()
            )
        }
    }
}