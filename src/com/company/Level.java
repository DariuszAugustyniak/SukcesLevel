package com.company;

import java.math.BigDecimal;

public class Level {
    int SL;
    BigDecimal szansa;

    public Level(int SL, BigDecimal szansa) {
        this.SL = SL;
        this.szansa = szansa;
    }

    public Level(BigDecimal szansa, int SL) {
        this.SL = SL;
        this.szansa = szansa;
    }

    void setSzansa(BigDecimal sza){
        szansa=sza;
    }
    void setSL(int S){
        SL=S;
    }
    void set(BigDecimal sza,int S){
        szansa=sza;
        SL=S;
    }
    void addSzansa(BigDecimal sza){
        szansa=szansa.add(sza);
    }


}
