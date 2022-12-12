import kotlin.system.exitProcess

class EPadocaController {

    private lateinit var produtosAVenda: List<Produto>
    private var venda = Venda()

    fun iniciar() {
        carregarProdutos()
        val header = """
   ,ggggggg,         ,ggggggggggg,                                                          
 ,dP""${'"'}${'"'}${'"'}${'"'}Y8b       dP""${'"'}88""${'"'}${'"'}${'"'}${'"'}Y8,                  8I                                    
 d8'    a  Y8       Yb,  88      `8b                  8I                                    
 88     "Y8P'        `"  88      ,8P                  8I                                    
 `8baaaa                 88aaaad8P"                   8I                                    
,d8P""${'"'}${'"'}     aaaaaaaa    88""${'"'}${'"'}${'"'}    ,gggg,gg    ,gggg,8I    ,ggggg,      ,gggg,    ,gggg,gg 
d8"          ""${'"'}${'"'}${'"'}${'"'}${'"'}${'"'}    88        dP"  "Y8I   dP"  "Y8I   dP"  "Y8ggg  dP"  "Yb  dP"  "Y8I 
Y8,                      88       i8'    ,8I  i8'    ,8I  i8'    ,8I   i8'       i8'    ,8I 
`Yba,,_____,             88      ,d8,   ,d8b,,d8,   ,d8b,,d8,   ,d8'  ,d8,_    _,d8,   ,d8b,
  `"Y8888888             88      P"Y8888P"`Y8P"Y8888P"`Y8P"Y8888P"    P""Y8888PPP"Y8888P"`Y8
                                                                                                                                                                             
        """.trimIndent()
        println(header)
        println("*******************************************************************************************************")
        imprimeMenuPrincipal()
    }
    private fun carregarProdutos() {
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
    private fun imprimeMenuPrincipal() {
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
    private fun imprimeMenuPaes() {
        imprimeMenu(TipoProduto.PAES)
    }
    private fun imprimeMenuSalgados() {
        imprimeMenu(TipoProduto.SALGADOS)
    }
    private fun imprimeMenuDoces() {
        imprimeMenu(TipoProduto.DOCES)
    }
    private fun finalizarVenda() {
        if (venda.carrinhoVazio()) {
            println("Deseja cancelar a compra? (S/N)")
            val cancelarCompra = readln().uppercase()

            if(cancelarCompra.equals("S")) {
                exitProcess(0)
            } else {
                imprimeMenuPrincipal()
                return
            }
        }

        println("Deseja adicionar um cupom (S/N)?")
        val querCupom = readln().uppercase()
        when(querCupom){
            "S"-> {
                println("Digite o nome do cupom")
                var nomeCupom = readln().uppercase()
                when (nomeCupom) {
                    "5PADOCA", "10PADOCA", "5OFF" -> {
                        venda.aplicarCupom(nomeCupom)
                        venda.fecharCompra()
                        venda = Venda()
                        iniciar()
                    }
                    else -> {
                        println("Cupom inválido.")
                        finalizarVenda()
                    }
                }
            }
            "N" -> {
                venda.fecharCompra()
                venda = Venda()
                iniciar()
            }
            else -> {
                println("Opção inválida.")
                finalizarVenda()
            }
        }
    }

    private fun imprimeMenu(tipoProduto: TipoProduto) {
        val produtos = produtosAVenda.filter { it.tipoProduto == tipoProduto }
        println("Item - Nome - Preço")
        for (itemMenu in 1..produtos.size) {
            val produto = produtos[itemMenu-1]
            println("$itemMenu - ${produto.nome} - R$ %.2f".format(produto.preco))
        }
        println("0 - Voltar para o menu anterior")
        val opcaoMenu = readln().toInt()
        when (opcaoMenu) {
            1, 2, 3 -> {
                println("Informe a quantidade:")
                val quantidade = readln().toInt()
                val produto = produtos[opcaoMenu - 1]
                venda.addCarrinho(produto, quantidade)
                when(tipoProduto) {
                    TipoProduto.SALGADOS -> imprimeMenuSalgados()
                    TipoProduto.DOCES -> imprimeMenuDoces()
                    TipoProduto.PAES -> imprimeMenuPaes()
                }
            }
            0 -> imprimeMenuPrincipal()
            else -> {
                println("Opção inválida.")
                when(tipoProduto) {
                    TipoProduto.SALGADOS -> imprimeMenuSalgados()
                    TipoProduto.DOCES -> imprimeMenuDoces()
                    TipoProduto.PAES -> imprimeMenuPaes()
                }
            }
        }

    }
}
