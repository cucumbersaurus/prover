fun parse(line:String):Triple<String, String, Condition>{

    val p = StringBuilder()
    val q = StringBuilder()
    val con = StringBuilder()

    val conChar = listOf('=', '>', '<')

    var cnt = 0
    line.forEach {
        if (it.isValidChar()) {
            when(cnt){
                0-> p.append(it)
                1->{
                    cnt++
                    q.append(it)
                }
                2-> q.append(it)
                else -> throw IllegalStateException(InputError.TOO_SHORT_OR_TOO_LONG.message)
            }
        } else if (conChar.contains(it)) {
            when(cnt){
                0 ->{
                    cnt++
                    con.append(it)
                }
                1->{
                    con.append(it)
                }
                else -> throw IllegalStateException(InputError.TOO_SHORT_OR_TOO_LONG.message)
            }
        }
    }
    when{
        p.isEmpty()||q.isEmpty()||cnt != 2 -> throw IllegalArgumentException(InputError.TOO_SHORT_OR_TOO_LONG.message)
        p[0] in '0'..'9'||q[0] in '0'..'9'-> throw IllegalArgumentException(InputError.CONDITION_STARTS_WITH_NUMBER.message)
        p.toString()==q.toString() -> throw IllegalArgumentException(InputError.DIFERENT_CONDITION_NEEDED.message)
    }

    return Triple(p.toString(), q.toString(), getCondition(con.toString()))
}

fun Char.isValidChar() = this.isLetter() || this == ' ' || this == '~' || this in '0'..'9'

fun getCondition(conString: String):Condition{
    return when{
        Condition.NECESSARY.notation.contains(conString) ->  Condition.NECESSARY
        Condition.SUFFICIENT.notation.contains(conString) -> Condition.SUFFICIENT
        Condition.INCLUSIVE.notation.contains(conString) -> Condition.INCLUSIVE
        else -> throw IllegalArgumentException(InputError.INVALID_CONDITION.message)
    }
}

fun makeContraposition(proposition:Triple<String, String, Condition>)
= Triple(proposition.second.getInverseCondition(), proposition.first.getInverseCondition(), proposition.third)

fun String.getInverseCondition():String{
    return if(this.startsWith("~")) this.slice(1..<this.length)
    else "~$this"
}