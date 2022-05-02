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

    public CentroDistribuicao (int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
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
        //Fazer
     }
    
    public SITUACAO getSituacao(){
        if (tAditivo > MAX_ADITIVO * 0.5 && tGasolina > MAX_GASOLINA * 0.5 && tAlcool1 > MAX_ALCOOL * 0.5 && tAlcool2 > MAX_ALCOOL * 0.5) {
            return SITUACAO.NORMAL;
        }
        else if (tAditivo > MAX_ADITIVO * 0.25 && tAditivo <= MAX_ADITIVO * 0.5 || tGasolina > MAX_GASOLINA * 0.25 && tGasolina <= MAX_GASOLINA * 0.5 || tAlcool1 > MAX_ALCOOL * 0.25 && tAlcool1 <= MAX_ALCOOL * 0.5 || tAlcool2 > MAX_ALCOOL * 0.25 && tAlcool2 <= MAX_ALCOOL * 0.5) {
            return SITUACAO.SOBRAVISO;
        }
        else if (tAditivo < MAX_ADITIVO * 0.25 || tGasolina < MAX_GASOLINA * 0.25 || tAlcool1 < MAX_ALCOOL * 0.25 || tAlcool2 < MAX_ALCOOL * 0.25) {
            return SITUACAO.EMERGENCIA;
        }
        else {
            return SITUACAO.NORMAL;
        }
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
        //Fazer
        return null;
    }
}