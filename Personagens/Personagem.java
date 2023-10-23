package Personagens;
import java.util.Scanner;

import Inimigos.Inimigos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import java.util.Random;

public abstract class Personagem {
    protected int vida, xp, dano, armadura, level, pts;
    protected Scanner input = new Scanner(System.in);
    private String [] classes = new String[3];
    private static final Map<Character, String> classMap = new HashMap<>();

    static {
        classMap.put('M', "Mana");
        classMap.put('G', "Sangramento");
        classMap.put('T', "Dano crtico");
    }

    protected Random r = new Random();
    protected char classeC;                                                     
    
    public int getVida() {
        return vida;
    }

    public String [] getClasse(Personagem p){
        if(classMap.containsKey(p.classeC)){
            classes[0] = classMap.get(p.classeC);
            return classes;
        }
        return null;
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

    public void uparLvl(){   
        int xpMax = level * 8;
        if(xp >= xpMax){
            int nivelNovo = getLevel() + 1;
            int atributos = 8;
            setLevel(nivelNovo);
            setXp(0);
            setPts(getPts() + atributos);
            System.out.println("Your character just leveled up, current level: " + getLevel() 
            + "Attribute points rewarded: " + getPts());
        }
    }

    public void verificaClasse(Object a, String [] atributo) throws IllegalArgumentException, IllegalAccessException{
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

    public void getAtaque(Object a, Inimigos i) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field [] camposAtaque = a.getClass().getDeclaredFields();
        for(Field campo: camposAtaque){
            if(campo.getName().equalsIgnoreCase("atacar")){
                try{
                    Method metodoAtacar = a.getClass().getMethod(campo.getName(), Inimigos.class);
                    metodoAtacar.invoke(a, i);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                
            }
        }

    }
}
    
    public int danoTomadoP(int dmg) {
        int defesa = getArmadura();
        int dano = dmg;
        if(defesa > 0){
            int danoFinal = (int) Math.max(dano - (int) (2 * Math.sqrt(defesa)), 8);
            setVida(getVida() - danoFinal);
            return danoFinal;
        }
        return dano;
    }

    public void setAtributos(Personagem p) throws IllegalArgumentException, IllegalAccessException{
        if(getPts() > 0){
            int vidaAtual = p.getVida();
            int danoAtual = p.getDano();
            int defesaAtual = p.getArmadura();
            int vidaFinal = 0, danoFinal = 0, defesaFinal = 0;
            boolean continuar = true;
            int PontosAtributos = getPts();
            while(PontosAtributos > 0 || continuar){
                System.out.println("What attributes do you wish to improve?");
                System.out.println("1. Health;");
                System.out.println("2. Damage;");
                System.out.println("3. Defense;");
                verificaClasse(p, classes);   
                System.out.println("5. Leave;");
                int menu = input.nextInt();
                System.out.println("How many points do you wish to use? Points remaining: " + getPts());
                int ptsDistri = input.nextInt();
                if(ptsDistri > PontosAtributos){
                    System.out.println("Invalid.");
                } else {
                switch(menu){
                    case 1:
                        int vidaAumentada = vidaAtual / 10;
                        vidaFinal += vidaAumentada;
                        PontosAtributos -= ptsDistri;
                    break;
                    case 2:
                        int danoAumentado = danoAtual / 2;
                        danoFinal += danoAumentado;
                        PontosAtributos -= ptsDistri;
                    break;
                    case 3:
                        int defesaAumentada = defesaAtual / 2;
                        defesaFinal += defesaAumentada;
                        PontosAtributos -= ptsDistri;
                    break;
                    case 4:
                    
                    break;
                    case 5:
                    continuar = false;
                    break;
                }
                p.setVida(danoAtual + vidaFinal);
                p.setDano(danoAtual + danoFinal);
                p.setArmadura(defesaAtual + defesaFinal);
            }
        }
        }
    }

    
}