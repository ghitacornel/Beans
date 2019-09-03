package test.beans.simple;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * just to test inheritance
 *
 * @author cornel.ghita
 */
abstract class AbstractBean {

    private String string;
    private SimpleEnum simpleEnum;
    private BigInteger bigInteger;
    private BigDecimal bigDecimal;
    private Date date;
    private java.sql.Date sqlDate;
    private java.sql.Timestamp sqlTimestamp;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleEnum getSimpleEnum() {
        return simpleEnum;
    }

    public void setSimpleEnum(SimpleEnum simpleEnum) {
        this.simpleEnum = simpleEnum;
    }

    public BigInteger getBigInteger() {
        return bigInteger;
    }

    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    public java.sql.Timestamp getSqlTimestamp() {
        return sqlTimestamp;
    }

    public void setSqlTimestamp(java.sql.Timestamp sqlTimestamp) {
        this.sqlTimestamp = sqlTimestamp;
    }

}
