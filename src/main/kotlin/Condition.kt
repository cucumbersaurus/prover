enum class Condition(val notation: List<String>) {
    NECESSARY(listOf("<=")),
    SUFFICIENT(listOf("=>")),
    INCLUSIVE(listOf("=", "<=>")),
}
