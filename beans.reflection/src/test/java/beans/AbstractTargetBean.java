package beans;

import java.sql.Timestamp;
import java.util.Date;

class AbstractTargetBean {

    protected Object o = new Object();
    protected String string = "source string";
    protected DummyEnum dummyEnum = DummyEnum.A;

    protected boolean b1 = true;
    protected Boolean b2 = true;
    protected boolean b4 = true;
    protected Boolean b3 = true;
    protected byte byte1 = 1;
    protected Byte byte2 = 1;
    protected byte byte4 = 1;
    protected Byte byte3 = 1;
    protected short s1 = 2;
    protected Short s2 = 2;
    protected short s4 = 2;
    protected Short s3 = 2;
    protected int i1 = 3;
    protected Integer i2 = 3;
    protected int i4 = 3;
    protected Integer i3 = 3;
    protected long l1 = 4;
    protected Long l2 = 4L;
    protected long l4 = 4;
    protected Long l3 = 4L;
    protected float f1 = 5;
    protected Float f2 = (float) 5;
    protected float f4 = 5;
    protected Float f3 = (float) 5;
    protected double d1 = 6;
    protected Double d2 = (double) 6;
    protected double d4 = 6;
    protected Double d3 = (double) 6;

    protected Date date1 = new Date(5);
    protected Timestamp timestamp1 = new Timestamp(7);

}
