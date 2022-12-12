import kotlin.system.exitProcess

class Venda {

    private var cupom: String = ""
    private var carrinho: MutableList<Pair<Produto, Int>> = mutableListOf()

    fun aplicarCupom(cupom: String) {
        this.cupom = cupom
    }
    fun addCarrinho(produto: Produto, quantidade: Int) {
        val indice = carrinho.indexOfFirst { it.first.nome == produto.nome }

        if (indice >= 0) {
           carrinho[indice] = Pair(carrinho[indice].first, carrinho[indice].second+quantidade)
        } else {
            val compra = Pair(produto, quantidade)
            carrinho.add(compra)
        }
    }
    fun fecharCompra() {

        var total = 0.0
        println("====================Comanda E-padoca=======================")
        println("Item.....Preço por unidade.....Quantidade.....Subtotal")

        for (elemento in carrinho) {
            var subtotal = elemento.first.preco * elemento.second
            println("${elemento.first.nome}.....R$ %.2f.....${elemento.second}.....R$ %.2f".format(elemento.first.preco, subtotal))
            total += subtotal
        }

        when(cupom) {
            "5PADOCA" -> total *= 0.95
            "10PADOCA" -> total *= 0.90
            "5OFF" -> total -= 5.00
        }

        println("Total da compra: R$ %.2f".format(total))
        println("*******************************************************************************************************")
    }

    fun carrinhoVazio(): Boolean {
        return carrinho.isEmpty()
    }
}