package com.example.image

data class Character(
    val hairColor: HairColor = HairColor.Default,
    val hairstyle: Hairstyle = Hairstyle.Default,
    val beard: Beard = Beard.Default,
    val accessories: Accessories = Accessories.Default
)

enum class HairColor(val label: String, val drawableName: String) {
    Default("Default", "default_hair"),
    Black("Black", "hair_black"),
    Blonde("Blonde", "hair_blonde"),
    Brown("Brown", "hair_brown"),
    Red("Red", "hair_red")
}

enum class Hairstyle(val label: String, val drawableName: String) {
    Default("Default", "default_hairstyle"),
    Short("Short", "hairstyle_short"),
    Medium("Medium", "hairstyle_medium"),
    Long("Long", "hairstyle_long")
}

enum class Beard(val label: String, val drawableName: String) {
    Default("Default", "default_beard"),
    None("None", "beard_none"),
    Mustache("Mustache", "beard_mustache"),
    FullBeard("FullBeard", "beard_fullbeard")
}

enum class Accessories(val label: String, val drawableName: String) {
    Default("Default", "default_accessory"),
    Glasses("Glasses", "accessory_glasses"),
}
