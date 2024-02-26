package com.example.tap2024.vistas;
import java.math.BigDecimal;
public class CalculadoraLogica {

    private BigDecimal result;
    private boolean isReal = true;

    public String getAnswer(){
        return getResult().toString();
    }

    public void setResult(String result){
        this.result = new BigDecimal(result);
    }

    public BigDecimal getResult(){
        return this.result;
    }

    public void setIsReal(boolean isReal){
        this.isReal = isReal;
    }

    public boolean getIsReal(){
        return this.isReal;
    }

    public void add(String operandA, String operandB){
        BigDecimal temp = new BigDecimal(operandA);
        setResult(temp.add(new BigDecimal(operandB)).toString());
        setIsReal(true);
    }

    public void subtract(String operandA, String operandB){
        BigDecimal temp = new BigDecimal(operandA);
        setResult(temp.subtract(new BigDecimal(operandB)).toString());
        setIsReal(true);
    }

    public void multiply(String operandA, String operandB){
        BigDecimal temp = new BigDecimal(operandA);
        setResult(temp.multiply(new BigDecimal(operandB)).toString());
        setIsReal(true);
    }

    public void divide(String operandA, String operandB){
        if(Double.parseDouble(operandB) == 0){
            setIsReal(false);
        }else{
            BigDecimal temp = new BigDecimal(operandA);
            setResult(temp.divide(new BigDecimal(operandB)).toString());
            setIsReal(true);
        }
    }

}
