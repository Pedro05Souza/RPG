package Personagens;
import Inimigos.Inimigos;


public class Mago extends Personagem implements PersonagemInterface {
    private int mana, manaTotal, wisdom;

    public Mago(){
        classeC = 'M';
        vida = 70;
        dano = 75;
        manaTotal = 100;
        mana = manaTotal;
        level = 1;
        armadura = 10; 
        wisdom = 1;
    }
    

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    
    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

     public int getManaTotal() {
        return manaTotal;
    }


    public void setManaTotal(int manaTotal) {
        this.manaTotal = manaTotal;
    }
  
    @Override
    public void atacar(Inimigos i) {
        PersonagemInterface.super.atacar(i);
        int menu = input.nextInt();
        switch(menu){
            case 1:
            ataque1(i);
            break;
            case 2:
            //ataque2(i);
            break;
        }
        
    }

    public void ataque1(Inimigos i) {
        int dano = getDano();
        if(mana >= manaNecessaria(.2)){
            System.out.println("Your attack deals: " + i.danoTomadoI(dano) + " damage.");
           reducaoMana(.2);
        } else {
            System.out.println("Insufficient mana!");
        }
    }

    public void ataque2(Inimigos i){
        int dano = getDano();
        if(mana >= manaNecessaria(.5)){
            Thread t = new Thread(new Runnable(){
            long endTime = System.currentTimeMillis() + 10000;
                @Override
                public void run() {
                    int danoFogo = (int)  Math.sqrt(dano);
                    int danoFinal = Math.max(danoFogo, 1);
                    while(System.currentTimeMillis() < endTime){
                    i.danoTomadoI(danoFinal);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                reducaoMana(.5);
                }
            });
            t.start();
            long tempoVidaThread = 10000;
            try{
                t.join(tempoVidaThread);
                if(t.isAlive()){
                    t.interrupt();
                }
            } catch(InterruptedException e){
                
            }
        } else {
            System.out.println("Insufficient mana");
        }
    
    }

    public void reducaoMana(double porcentagem){
        int valorReduzido = (int) Math.max(((int) mana * porcentagem), 0);
        setMana(getMana() - valorReduzido);
        System.out.println("Your mana has been reduced to:" + getMana());
    }

    public int manaNecessaria(double percentual){
        int manaN = (int) (manaTotal * percentual);
        return manaN;
    }

    
    
}
