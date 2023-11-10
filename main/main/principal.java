package main;
import Funcoes.Funcoes;
import Inventario.Inventario;
public class principal {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        Funcoes f = new Funcoes();
        Inventario inv = new Inventario();
        f.loadSave(f.loadStats());
        f.menuPrincipal();
    }
}
