package trabalho;

public class CentroDistribuicao {
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int tAditivo;
    private int tGasolina;
    private int tAlcool1;
    private int tAlcool2;
    private SITUACAO situacao;

    public CentroDistribuicao (int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
     }

     public static void main(String[] args) {
        CentroDistribuicao centro = new CentroDistribuicao(500, 100, 2500, 2500);
         int[] encomenda = centro.encomendaCombustivel(200, TIPOPOSTO.COMUM);
         System.out.println(encomenda[0] + " " + encomenda[1] + " " + encomenda[2] + " " + encomenda[3]);

     }
    
    public int gettAditivo() {
        return tAditivo;
    }

    public void settAditivo(int tAditivo) {
        this.tAditivo = tAditivo;
    }

    public int gettGasolina() {
        return tGasolina;
    }

    public void settGasolina(int tGasolina) {
        this.tGasolina = tGasolina;
    }

    public int gettAlcool1() {
        return tAlcool1;
    }

    public void settAlcool1(int tAlcool1) {
        this.tAlcool1 = tAlcool1;
    }

    public int gettAlcool2() {
        return tAlcool2;
    }

    public void settAlcool2(int tAlcool2) {
        this.tAlcool2 = tAlcool2;
    }

    public void defineSituacao(){
        if (tAditivo > MAX_ADITIVO * 0.5 && tGasolina > MAX_GASOLINA * 0.5 && tAlcool1 > MAX_ALCOOL * 0.5 && tAlcool2 > MAX_ALCOOL * 0.5) {
            this.situacao =  SITUACAO.NORMAL;
        }
        else if (tAditivo > MAX_ADITIVO * 0.25 && tAditivo <= MAX_ADITIVO * 0.5 || tGasolina > MAX_GASOLINA * 0.25 && tGasolina <= MAX_GASOLINA * 0.5 || tAlcool1 > MAX_ALCOOL * 0.25 && tAlcool1 <= MAX_ALCOOL * 0.5 || tAlcool2 > MAX_ALCOOL * 0.25 && tAlcool2 <= MAX_ALCOOL * 0.5) {
            this.situacao =  SITUACAO.SOBRAVISO;
        }
        else if (tAditivo < MAX_ADITIVO * 0.25 || tGasolina < MAX_GASOLINA * 0.25 || tAlcool1 < MAX_ALCOOL * 0.25 || tAlcool2 < MAX_ALCOOL * 0.25) {
            this.situacao =  SITUACAO.EMERGENCIA;
        }
        else {
            this.situacao =  SITUACAO.NORMAL;
        }
     }
    
    public SITUACAO getSituacao(){
        return this.situacao;
    }

    public int recebeAditivo(int qtdade) {
        if (qtdade > 0) {
            if (tAditivo + qtdade > MAX_ADITIVO) {
                return -1;
            }
            else {
                tAditivo += qtdade;
                return qtdade;
            }
        }
        else {
            return -1;
        }
      }
 
    public int recebeGasolina(int qtdade) {
        if (qtdade > 0) {
            if (tGasolina + qtdade > MAX_GASOLINA) {
                return -1;
            }
            else {
                tGasolina += qtdade;
                return qtdade;
            }
        }
        else {
            return -1;
        }
      }

    public int recebeAlcool(int qtdade) { 
        if (qtdade > 0) {
            if (tAlcool1 + qtdade/2 > MAX_ALCOOL / 2 && tAlcool2 + qtdade/2 > MAX_ALCOOL / 2) 
                return -1;
            else {
                tAlcool1 += qtdade/2;
                tAlcool2 += qtdade/2;
                return qtdade;
            }
        }
        else
            return -1;
    }

     
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) { 
        this.defineSituacao();
        SITUACAO situacao = this.getSituacao();
        int[] retorno = new int[4];

        int qtdadeAditivoNoCentro = this.gettAditivo();
        int qtdadeGasolinaNoCentro = this.gettGasolina();
        int qtdadeAlcool1NoCentro = this.gettAlcool1();
        int qtdadeAlcool2NoCentro = this.gettAlcool2();

        if (situacao == SITUACAO.SOBRAVISO) {
            if (tipoPosto == TIPOPOSTO.COMUM) {
                qtdade = qtdade / 2;
            }
        }

        if (situacao == SITUACAO.EMERGENCIA) {
            if (tipoPosto == TIPOPOSTO.COMUM) {
                retorno[0] = -14;
            } else {
                qtdade = qtdade / 2;
            }
        }

        int qtdAditivo = (int) Math.ceil(qtdade * 0.05);
        int qtdGasolina = (int) Math.ceil(qtdade * 0.70);
        int qtdAlcool1 = (int) Math.ceil(qtdade * 0.125);
        int qtdAlcool2 = (int) Math.ceil(qtdade * 0.125);

        int qtdAditivoRestante = qtdadeAditivoNoCentro - qtdAditivo;
        int qtdGasolinaRestante = qtdadeGasolinaNoCentro - qtdGasolina;
        int qtdAlcool1Restante = qtdadeAlcool1NoCentro - qtdAlcool1;
        int qtdAlcool2Restante = qtdadeAlcool2NoCentro - qtdAlcool2;

        if (retorno[0] == -14) {
            return retorno;
        }

        if (qtdAditivoRestante < 0 || qtdGasolinaRestante < 0 || qtdAlcool1Restante < 0 || qtdAlcool2Restante < 0) {
            retorno[0] = -21;
            return retorno;
        }

        retorno[0] = qtdAditivoRestante;
        retorno[1] = qtdGasolinaRestante;
        retorno[2] = qtdAlcool1Restante;
        retorno[3] = qtdAlcool2Restante;

        settAditivo(qtdAditivoRestante);
        settGasolina(qtdGasolinaRestante);
        settAlcool1(qtdAlcool1Restante);
        settAlcool2(qtdAlcool2Restante);

        return retorno;
    }

}