class Tarefa {

    var tipoTarefa: String
        get() = "Retorno"
        set(value) {
            tipoTarefa = value
            print("setou")
        }
}