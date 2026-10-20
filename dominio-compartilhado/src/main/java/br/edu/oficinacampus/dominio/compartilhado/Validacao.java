package br.edu.oficinacampus.dominio.compartilhado;

import java.net.URI;
import java.util.List;
import java.util.Locale;

/** Validações de valores reutilizáveis, sem metamodelo de formulários. */
public final class Validacao {
    private Validacao() { }
    public static String texto(String nome, String valor, int minimo, int maximo, boolean obrigatorio) {
        if (valor == null || valor.isBlank()) {
            if (obrigatorio) throw ErroDominio.validacao(nome + " é obrigatório.");
            return "";
        }
        String normal = valor.trim();
        if (normal.length() < minimo || maximo > 0 && normal.length() > maximo)
            throw ErroDominio.validacao(nome + " deve ter " + minimo + " a " + maximo + " caracteres.");
        return normal;
    }
    public static String linha(String nome, String valor, int minimo, int maximo, boolean obrigatorio) {
        String normal = texto(nome, valor, minimo, maximo, obrigatorio);
        if (normal.contains("\n") || normal.contains("\r")) throw ErroDominio.validacao(nome + " deve ocupar uma única linha.");
        return normal;
    }
    public static String opcao(String nome, String valor, String... opcoes) {
        String normal = texto(nome, valor, 1, 100, true);
        if (!List.of(opcoes).contains(normal)) throw ErroDominio.validacao(nome + " possui uma opção inválida.");
        return normal;
    }
    public static Integer inteiro(String nome, Integer valor, int minimo, int maximo, boolean obrigatorio) {
        if (valor == null) {
            if (obrigatorio) throw ErroDominio.validacao(nome + " é obrigatório.");
            return null;
        }
        if (valor < minimo || valor > maximo) throw ErroDominio.validacao(nome + " deve estar entre " + minimo + " e " + maximo + ".");
        return valor;
    }
    public static String email(String nome, String valor) {
        String normal = texto(nome, valor, 1, 254, true);
        if (!normal.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) throw ErroDominio.validacao(nome + " deve ter formato de e-mail.");
        return normal;
    }
    public static String endereco(String nome, String valor) {
        String normal = texto(nome, valor, 1, 0, true);
        try {
            URI uri = URI.create(normal);
            if (!List.of("http", "https").contains(uri.getScheme() == null ? "" : uri.getScheme().toLowerCase(Locale.ROOT)) || uri.getHost() == null) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) { throw ErroDominio.validacao(nome + " deve ser uma URL HTTP ou HTTPS com host."); }
        return normal;
    }
    public static List<String> componentes(List<String> valores) {
        if (valores == null || valores.isEmpty()) throw ErroDominio.validacao("Componentes deve conter pelo menos um componente.");
        return valores.stream().map(v -> texto("Componente", v, 1, 100, true)).toList();
    }
}
