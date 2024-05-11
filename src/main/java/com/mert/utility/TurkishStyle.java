package com.mert.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class TurkishStyle {

    public String  formatPrice(double price){
        Locale turkish = new Locale("tr", "TR");
        BigDecimal point = new BigDecimal(Double.toString(price));
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(turkish);
        return numberFormat.format(point);
    }
}
