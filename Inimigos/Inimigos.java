package Inimigos;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import Personagens.Personagem;

    public abstract class Inimigos {
        protected int vida, dano, armadura, xp, nivel, pontos;
        protected String nome;
        protected Random random = new Random();

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

        // Função que seta o nivel do inimigo para o player
        public int Scaling(int nivelP){
            int lvlInimigo = getNivel();
            lvlInimigo = nivelP;
            return lvlInimigo;
        }
        
        // Seta os atributos do inimigo comm um número aleatório
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

        // Setters and Getters
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
    }