class Venda (val listaDeProduto: MutableList<Produto>) {

    private lateinit var cupom: String

    fun aplicarCupom(cupom: String) {
        this.cupom = cupom
    }

}