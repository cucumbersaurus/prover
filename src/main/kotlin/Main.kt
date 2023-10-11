fun main() {

    println(""""p<=>q" 형식으로 입력하세요. 입력을 종료하려면 "end"를 입력하세요.""")

    while (true) {
        val line = readln()
        if (line == "end") break

        lateinit var proposition:Triple<String, String, Condition>
        try {
            proposition = parse(line)
        }
        catch (e: IllegalArgumentException) {
            if(e.message!=null) println(e.message)
            else println("잘못된 형식입니다.")
            continue
        }
        //println(proposition)
        Node.makeLink(proposition)
        Node.makeLink(makeContraposition(proposition))
    }
    //Node.printAll()
    Node.findAllLinks()
    println("-----------")
    //Node.printAll()

    println(""""검증할 명제를 입력하세요. 종료하려면 "end"를 입력하세요.""")

    while (true) {
        val line = readln()
        if (line == "end") break

        lateinit var eval: Triple<String, String, Condition>

        try {
            eval = parse(line)
        } catch (e: IllegalArgumentException) {
            if (e.message != null) println(e.message)
            else println("잘못된 형식입니다.")
        }

        val a = Node.getNode(eval.first)
        val b = Node.getNode(eval.second)
        val res = when (eval.third) {
            Condition.INCLUSIVE -> a.sufficient.contains(b) && b.sufficient.contains(a)
            Condition.SUFFICIENT -> a.sufficient.contains(b)
            Condition.NECESSARY -> a.necessary.contains(b)
        }
        println(res)
    }
}

