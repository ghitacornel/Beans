package convertors;

import beans.SourceBean;
import beans.TargetBean;
import beans.mapper.Mapper;

final public class ManualCopy implements Mapper<Object, Object> {

    @Override
    final public void map(final Object sourceBean, final Object destinationBean) {
        SourceBean source = (SourceBean) sourceBean;
        TargetBean target = (TargetBean) destinationBean;
        target.setB2(source.getB2());
        target.setB4(source.getB4());
        target.setByte1(source.getByte1());
        target.setByte2(source.getByte2());
        target.setByte3(source.getByte3());
        target.setByte4(source.getByte4());
        target.setD1(source.getD1());
        target.setD2(source.getD2());
        target.setD3(source.getD3());
        target.setD4(source.getD4());
        target.setDate1(source.getDate1());
        target.setTimestamp1(source.getTimestamp1());
        target.setF1(source.getF1());
        target.setF2(source.getF2());
        target.setF3(source.getF3());
        target.setF4(source.getF4());
        target.setI1(source.getI1());
        target.setI2(source.getI2());
        target.setI3(source.getI3());
        target.setI4(source.getI4());
        target.setL1(source.getL1());
        target.setL2(source.getL2());
        target.setL3(source.getL3());
        target.setL4(source.getL4());
        target.setS1(source.getS1());
        target.setS2(source.getS2());
        target.setS3(source.getS3());
        target.setS4(source.getS4());
        target.setString(source.getString());
        target.setB1(source.isB1());
        target.setB3(source.isB3());
        target.setO(source.getO());
        target.setDummyEnum(source.getDummyEnum());
    }
}
