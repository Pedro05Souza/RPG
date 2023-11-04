package Inimigos;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import Inventario.Itens;
import Inventario.Inventario;
import Personagens.*;

    public abstract class Inimigos {

        protected int vida, dano, armadura, xp, nivel, pontos;
        protected ArrayList<Itens> drops = new ArrayList<>(3); 
        protected static ArrayList<Inimigos> ObjInimigo = new ArrayList<>();
        protected String nome;
        protected Random random = new Random();
        protected int ai;
        protected Timer timer = new Timer(); 
        private Inventario inv = new Inventario();
        private static Random r = new Random();

        public Inimigos(int p){
            setPontos(Scaling(p));
        }

        // Função que seta os métodos do inimigo pegando o level do player * 8
        public void setPontos(int nivel) {
            int FormulaPonto = nivel * 8;
            int FormulaXp = nivel * 40;
            pontos = FormulaPonto;
            setXp(FormulaXp);
        }

        // Função que faz o inimigo tomar dano
        public int danoTomadoI(int dmg){
            int dano = dmg;
            int defesa = getArmadura();
            if(defesa > 0){
                int danoFinal = (int) Math.max(dano - (int) (2 * Math.sqrt(defesa)), 2);
                setVida(getVida() - danoFinal);
                return danoFinal;
            }
            return dano;
        }

        // Função que pega o ataque do Inimigo
        public void getAtaque(Object a, Personagem p) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            Method[] declaredMethods = a.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.getName().equals("atacar")) {
                    try {
                        method.invoke(a, p);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Função que seta o nivel do inimigo para o player * 2
        public int Scaling(int nivelP){
            int lvlInimigo = getNivel();
            lvlInimigo = nivelP * 2;
            return lvlInimigo;
        }
        
        // Seta os atributos do inimigo com um número aleatório
        public void setAtributosInimigo(){
            int pontos = getPontos();
            int vidaAtual = getVida();
            int danoAtual = getDano();
            int defesaAtual = getArmadura();
            System.out.println(vidaAtual);
            int vidaFinal = 0, danoFinal = 0, defesaFinal = 0;
            while(pontos > 0){
                int ai = random.nextInt(3);
                switch(ai){
                    case 0:
                    int vidaAumentada = vidaAtual / 10;
                    vidaFinal += vidaAumentada;
                    pontos--;
                    break;
                    case 1:
                    int danoAumentado = danoAtual / 2;
                    danoFinal += danoAumentado;
                    pontos--;
                    break;
                    case 2:
                    int defesaAumentada = defesaAtual / 2;
                    defesaFinal += defesaAumentada;
                    pontos--;
                    break;
                }

            }
            setVida(vidaAtual + vidaFinal);
            setDano(danoAtual + danoFinal);
            setArmadura(defesaAtual + defesaFinal);
        }

        // Função que faz o inimigo dropar um item
        public void dropItem() {
            int rng = random.nextInt(101);
            int sorteTotal = 0;
            int menorDiferenca = Integer.MAX_VALUE;
            Itens itemEscolhido = null;
            for (Itens item : drops) {
                sorteTotal += item.chanceRaridade();
            }

            if (sorteTotal >= rng) {
                for (Itens item : drops) {
                    int diferencaAtual = Math.abs(item.chanceRaridade() - rng);
                    if (Math.abs(menorDiferenca - rng) > diferencaAtual) {
                        menorDiferenca = item.chanceRaridade() - rng;
                        itemEscolhido = item;
                    }
                }
            }
            if (itemEscolhido != null) {
                inv.pegaritemInimigo(itemEscolhido);
            }
            
        }

        // Função que verifica se o inimigo morreu
        public void death(Personagem p){
            if(getVida() < 0){
                System.out.println("You have defeated " + getNome() + "!");
                System.out.println("You have gained " + getXp() + " XP!");
                dropItem();
                p.setXp(p.getXp() + getXp());
                
            }
        }


        // Método que retorna um inimigo aleatório do array de inimigos
        public static Inimigos inimigoEscolhido(ArrayList<Inimigos> subObj){
            if(subObj.size() == 0){
                System.out.println("There are no enemies");
                return null;
            } else {
            int rng = r.nextInt(subObj.size());
            return subObj.get(rng);
            }
       
        }

        // Método que adiciona um inimigo ao array de inimigos
        public static void AdicionarInimigo(Inimigos i){
            ObjInimigo.add(i);
        }



        // Setters and Getters
        public static ArrayList<Inimigos> getObjInimigo() {
            return ObjInimigo;
        }

        public int tamanhoArray(){
            return ObjInimigo.size();
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getPontos() {
            return pontos;
        }


        public int getVida() {
            return vida;
        }

        public void setVida(int vida) {
            this.vida = vida;
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

        public int getXp() {
            return xp;
        }

        public void setXp(int xp) {
            this.xp = xp;
        }

        public int getNivel() {
            return nivel;
        
        }

        public int getAi() {
            return ai;
        }

        public void setAi(int ai) {
            this.ai = ai;
        }
        
    }