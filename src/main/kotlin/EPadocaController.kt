class EPadocaController {

    private lateinit var produtosAVenda: List<Produto>

    fun iniciar() {
        carregarProdutos()
        imprimeMenuPrincipal()


    }
    fun carregarProdutos() {
        val paoFrances = Produto("Pão Francês",0.60, TipoProduto.PAES)
        val paoDeLeite = Produto("Pão de Leite",0.40, TipoProduto.PAES)
        val paoDeMilho = Produto("Pão de Milho",0.50, TipoProduto.PAES)
        val coxinha = Produto("Coxinha",5.00,TipoProduto.SALGADOS)
        val esfiha = Produto("Esfiha", 6.00,TipoProduto.SALGADOS)
        val paoDeQueijo = Produto("Pão de Queijo",3.00, TipoProduto.SALGADOS)
        val carolina = Produto("Carolina",1.50,TipoProduto.DOCES)
        val pudim = Produto("Pudim",4.00,TipoProduto.DOCES)
        val brigadeiro = Produto("Brigadeiro",2.00,TipoProduto.DOCES)
        produtosAVenda = listOf(paoFrances,paoDeLeite,paoDeMilho,coxinha,esfiha,paoDeQueijo,carolina,pudim,brigadeiro)
    }
    fun imprimeMenuPrincipal() {
        val menuPrincipal = """
        1..................Pães
        2..............Salgados
        3.................Doces
        0......Finalizar compra
    """.trimIndent()
        println(menuPrincipal)
        println("Escolha uma das opções do cardápio:")
        val escolhaMenuPrincipal = readln().toInt()
        when (escolhaMenuPrincipal) {
            1 -> imprimeMenuPaes()
            2 -> imprimeMenuSalgados()
            3 -> imprimeMenuDoces()
            0 -> finalizarVenda()
            else -> {
                println("Opção inválida.")
                imprimeMenuPrincipal()
            }
        }

    }
    fun imprimeMenuPaes() {
        val paes = produtosAVenda.filter { it.tipoProduto == TipoProduto.PAES }
        println("Item - Nome - Preço")
        for (itemMenu in 1..paes.size) {
            val produto = paes[itemMenu-1]
            println("$itemMenu - ${produto.nome} - R$ ${produto.preco}")
        }
        println("0 - Voltar para o menu anterior")


    }
    fun imprimeMenuSalgados() {

    }
    fun imprimeMenuDoces() {

    }
    fun finalizarVenda() {

    }
}
