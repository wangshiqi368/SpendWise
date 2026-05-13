package com.spendwise.app.domain.model

data class Category(
    val name: String,
    val icon: String // Using Emoji for simplicity
) {
    companion object {
        val defaultCategories = listOf(
            Category("餐饮", "🍔"),
            Category("购物", "🛍️"),
            Category("交通", "🚗"),
            Category("娱乐", "🎮"),
            Category("医疗", "🏥"),
            Category("教育", "📚"),
            Category("工资", "💰"),
            Category("其他", "📦")
        )
    }
}
