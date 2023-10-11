class Node(val id:String, val isInverted:Boolean) {

    val name
    get() = if(isInverted) "~$id" else id

    val necessary = ArrayList<Node>()
    val sufficient = ArrayList<Node>()

    fun addNecessary(node:Node) =necessary.add(node)
    fun addSufficient(node:Node) = sufficient.add(node)


    fun printNecessary() {
        necessary.forEach {
            print("${it.name}, ")
        }
    }
    fun printSufficient(){
        sufficient.forEach {
            print("${it.name}, ")
        }
    }
    override fun hashCode(): Int {
        return if(isInverted) id.hashCode().inv()
        else id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Node){
            this.hashCode()==other.hashCode()
        } else false
    }
    companion object{
        val map = HashMap<String, Node>()
        fun makeLink(data:Triple<String, String, Condition>){
            val p= getNode(data.first)
            val q= getNode(data.second)
            when(data.third){
                Condition.NECESSARY -> {
                    //p<=q
                    p.addNecessary(q)
                    q.addSufficient(p)
                }
                Condition.SUFFICIENT ->{
                    //p=>q
                    p.addSufficient(q)
                    q.addNecessary(p)
                }
                Condition.INCLUSIVE ->{
                    //p<=q
                    p.addNecessary(q)
                    q.addSufficient(p)
                    //p=>q
                    p.addSufficient(q)
                    q.addNecessary(p)
                }
            }
        }

        fun getNode(name:String):Node{
            var isInverted = false
            if(name.startsWith("~")) isInverted = true
            if(map[name]==null){
                val id = if(isInverted) name.slice(1..<name.length) else name
                map[name] = Node(id, isInverted)
            }
            return map[name]!!
        }

        fun printAll(){
            map.forEach { (_, n) ->
                print(n.name)
                print("<=")
                n.printNecessary()
                println()
                print(n.name)
                print("=>")
                n.printSufficient()
                println()
            }
        }

        fun findAllLinks(){
            map.forEach { (_, m) ->
                val from = m.necessary
                val to = m.sufficient
                from.forEach {
                    it.sufficient.addAll(to)
                }
            }
        }
    }
}