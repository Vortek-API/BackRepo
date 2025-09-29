package fatec.vortek.cimob.domain.enums;

public enum IndicadorMnemonico {
    EXCESSO_VELOCIDADE("Excesso de Velocidade Grave"),
    VARIABILIDADE_VELOCIDADE("Variabilidade de Velocidade"),
    VEICULOS_LENTOS("Veículos Muito Lentos");

    private final String descricao;

    IndicadorMnemonico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
