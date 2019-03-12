package beans;

import java.sql.Timestamp;
import java.util.Date;

class AbstractSourceBean {

    protected Object o = new Object();
    protected String string = "source string";
    protected DummyEnum dummyEnum = DummyEnum.A;

    protected boolean b1 = true;
    protected Boolean b2 = true;
    protected boolean b3 = true;
    protected Boolean b4 = true;
    protected byte byte1 = 1;
    protected Byte byte2 = 1;
    protected byte byte3 = 1;
    protected Byte byte4 = 1;
    protected short s1 = 2;
    protected Short s2 = 2;
    protected short s3 = 2;
    protected Short s4 = 2;
    protected int i1 = 3;
    protected Integer i2 = 3;
    protected int i3 = 3;
    protected Integer i4 = 3;
    protected long l1 = 4;
    protected Long l2 = 4L;
    protected long l3 = 4;
    protected Long l4 = 4L;
    protected float f1 = 5;
    protected Float f2 = (float) 5;
    protected float f3 = 5;
    protected Float f4 = (float) 5;
    protected double d1 = 6;
    protected Double d2 = (double) 6;
    protected double d3 = 6;
    protected Double d4 = (double) 6;

    protected Date date1 = new Date(5);
    protected Timestamp timestamp1 = new Timestamp(7);

}
