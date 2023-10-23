package Inimigos;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import Personagens.Personagem;

    public abstract class Inimigos {
        protected int vida, dano, armadura, xp, nivel, pontos;
        protected Random random = new Random();

        public Inimigos(int i){
            setPontos(Scaling(i));

        }

        public void setPontos(int nivel) {
            int FormulaPonto = nivel * 8;
            int FormulaXp = nivel * 40;
            pontos = FormulaPonto;
            setXp(FormulaXp);
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

        public int danoTomadoI(int dmg){
            int dano = dmg;
            int defesa = getArmadura();
            if(defesa > 0){
                int danoFinal = (int) Math.max(dano - (int) (2 * Math.sqrt(defesa)), 8);
                setVida(getVida() - danoFinal);
                return danoFinal;
            }
            return dano;
        }


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

        public int Scaling(int nivelP){
            int lvlInimigo = getNivel();
            lvlInimigo = nivelP;
            return lvlInimigo;
        }
        

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
        
        

    }