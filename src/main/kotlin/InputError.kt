enum class InputError(val message: String) {
    CONDITION_STARTS_WITH_NUMBER("조건이 숫자로 시작할 수 없습니다."),
    TOO_SHORT_OR_TOO_LONG("너무 적거나 많은 값이 입력되었습니다. p<=>q의 형식이 맞는지 확인하세요"),
    DIFERENT_CONDITION_NEEDED("p와q는 다른 조건이여야 합니다."),
    INVALID_CONDITION("=, =>, <=, <=>중 하나의 조건을 사용하십시오."),
}