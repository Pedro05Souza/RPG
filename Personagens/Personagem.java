package Personagens;
import java.util.Scanner;
import java.util.Timer;
import Funcoes.*;
import Inimigos.Inimigos;
import cores.cores;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * Superclasse de todos os Personagens a serem criados
 * STATUS: Funcional;
 * STATUS: Classes: Falta tirar os ataques com Timer;
 */
public abstract class Personagem {
    protected int vida, vidaMax, xp, dano, armadura, level, pts;
    protected static String ataque1, ataque2;
    protected Scanner input = new Scanner(System.in);
    protected Random r = new Random();
    protected char classeCaractere;
   // em caso de alguma classe precise de algum ataque com tempo
    protected Timer timer = new Timer();  
    private String [] valoresClasses = new String[1];
    private static final Map<Character, String> atributoClasse = new HashMap<>();

    // Define os valores dos atributos das classes
    static {
        atributoClasse.put('M', "Mana");
        atributoClasse.put('G', "CritDmg");
        atributoClasse.put('K', "CoolDown");
        atributoClasse.put('A', "Arrows");
    }

                                      
    // Status do jogador
    public void status() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("HP " + getVida());
        System.out.println("Damage: " + getDano());
        System.out.println("Armor: " + getArmadura());
        System.out.println("Level: " + getLevel());
        System.out.println("XP: " + getXp());
        System.out.println("Attribute points: " + getPts());
        System.out.println(getClasse(valoresClasses) + ": " + getCalculoClasse(this, valoresClasses));
    }

    // Pega a classe do jogador
    public String [] getClasseMap(Personagem p){
        if(atributoClasse.containsKey(p.classeCaractere)){
            valoresClasses[0] = atributoClasse.get(p.classeCaractere);
            return valoresClasses;
        }
        return null;
        }




    // Upar de nível
    public void uparLvl(){   
        int xpMax = level * 8;
        if(xp >= xpMax){
            int nivelNovo = getLevel() + 1;
            int atributos = 8;
            setLevel(nivelNovo);
            setXp(0);
            setPts(getPts() + atributos);
            cores.setGreen("Your character just leveled up, current level: " + getLevel() + " and you have more " + getPts() + " points to distribute.");
        }
    }

    // Cálculo de atributo das habilidades das classes
    public void calculoClasse(Object a, String [] atributo) throws IllegalArgumentException, IllegalAccessException{
       Field [] b = a.getClass().getDeclaredFields();
       for(int i = 0; i < b.length; i++){
        for(int j = 0; j < atributo.length; j++){
            if(b[i].getName().equalsIgnoreCase(atributo[j])){
                b[i].setAccessible(true);
                int atributoAumentado = b[i].getInt(a) / 10;
                b[i].set(a, b[i].getInt(a) + atributoAumentado);
            }
        }
       }
    }
    
    // Pega o método na interface de ataque.
    public void getAtaque(Object a, Inimigos i) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method[] declaredMethods = a.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals("atacar")) {
                try {
                    method.invoke(a, i);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

     // Imprime o atributo da classe
     public int getCalculoClasse(Object a, String [] atStrings) throws IllegalArgumentException, IllegalAccessException{
        int valorAtributo = 0;
        Field [] b = a.getClass().getDeclaredFields();
        for(int i = 0; i < b.length; i ++){
            for(int j = 0; j < atStrings.length; j++){
                if(b[i].getName().equalsIgnoreCase(atStrings[j])){
                    b[i].setAccessible(true);
                    valorAtributo = b[i].getInt(a);
                    return valorAtributo;

                }
            }
        }
        return valorAtributo;
    }

    // Função que o jogador leva dano
    public int danoTomadoP(int dmg) {
        int defesa = getArmadura();
        int dano = dmg;
        if(defesa > 0){
            int danoFinal = (int) Math.max(dano - (int) (2 * Math.sqrt(defesa)), 2);
            vida -= danoFinal;
            return danoFinal;
        }
        vida -= dano;
        return dano;
    }

    // seta os novos atributos do jogador com base nos pontos
    public void setAtributos(Personagem p) throws IllegalArgumentException, IllegalAccessException{
        if(getPts() > 0){
            int vidaAtual = p.getVidaMax();
            int danoAtual = p.getDano();
            int defesaAtual = p.getArmadura();
            int vidaFinal = 0, danoFinal = 0, defesaFinal = 0;
            boolean continuar = true;
            int PontosAtributos = getPts();
            while(PontosAtributos > 0 || continuar){
                PontosAtributos = getPts();
                System.out.println("What attributes do you wish to improve?");
                System.out.println("[1]. Health;");
                System.out.println("[2]. Damage;");
                System.out.println("[3]. Defense;");
                System.out.println("[4]. " + getClasse(valoresClasses));
                System.out.println("[5]. Leave;");
                int menu = input.nextInt();
                if(menu == 5){
                    break;
                }
                System.out.println("How many points do you wish to use? Points remaining: " + getPts());
                int ptsDistri = input.nextInt();
                if(ptsDistri > PontosAtributos){
                    cores.setRed("You don't have enough points.");
                    break;
                } else {
                switch(menu){
                    case 1:
                        int vidaAumentada = vidaAtual / 10;
                        vidaFinal += vidaAumentada;
                    break;
                    case 2:
                        int danoAumentado = danoAtual / 2;
                        danoFinal += danoAumentado;
                    break;
                    case 3:
                        int defesaAumentada = defesaAtual / 2;
                        defesaFinal += defesaAumentada;
                    break;
                    case 4:
                    calculoClasse(p, valoresClasses);
                    break;
                    case 5:
                    cores.setGreen("Returning to the main menu.");
                    continuar = false;
                    break;
                    default:
                    cores.setRed("Invalid option.");
                    break;
                }
                p.setVidaMax(danoAtual + vidaFinal);
                p.setDano(danoAtual + danoFinal);
                p.setArmadura(defesaAtual + defesaFinal);
                p.setPts(PontosAtributos - ptsDistri);
                }
            }
        }
    }
    // Função que cura o jogador: FALTA FAZER, precisa de uma variável q pega o limite da vida do jogador
  //  public void cura() {
       // if(getVida() > 0 && getVida() < 100 && Funcoes.Rodadas > 0){

      //  }
   // }

    // Menu de ataque do jogador
    public static void menuAtaque(){
        System.out.println("--------------------------------");
        System.out.println("Choose your character's attacks: ");
        System.out.println("[1]. " + getAtaque1N());
        System.out.println("[2]. " + getAtaque2N());
        System.out.println("--------------------------------");
    }

    public void hpJogador(){
        int vida = getVida();
        int vidaPorcentagem = (int) (vidaMax * 0.4);
        if(vida < vidaPorcentagem){
            cores.setRed("Your Health: " + getVida() + "/" + getVidaMax());
        } else {
            cores.setGreen("Your Health: " + getVida() + "/" + getVidaMax());
        }
    }

    // Função que o jogador morre deletando a save
    public void death(Funcoes f){
        if(getVida() <= 0){
        cores.setRed("Your character has fallen.");
        if(f.getSave().exists()){
            f.getSave().delete();
        }
        System.exit(0);      
    }
    }


    // Getters e Setters
    public String getClasse(String [] classe){
        return classe[0];
    }

    public int getVida() {
        return vida;
    }
    
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public static String getAtaque1N() {
        return ataque1;
    }

    public static void setAtaque1N(String ataque1) {
        Personagem.ataque1 = ataque1;
    }
    
    public static String getAtaque2N() {
        return ataque2;
    }

    public static void setAtaque2N(String ataque2) {
        Personagem.ataque2 = ataque2;
    }

    public char getClasseCaractere() {
        return classeCaractere;
    }

    public void setClasseCaractere(char classeC) {
        this.classeCaractere = classeC;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }
    
    

}