package util;

public class Formatador {
	
	 /**
     * converte a virgula de uma string para ponto
     * @param pString
     * @return double
     */
    public float converterVirgulaParaPonto(String pString){
        String retorno = new String();
        int tamanhoString = pString.length();
        for(int i = 0; i < tamanhoString; i++){
            if(pString.charAt(i) == ','){
                retorno += '.';
            } else {
                retorno += pString.charAt(i);
            }
        }
        return Float.parseFloat(retorno);
    }

}
