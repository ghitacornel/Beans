package beans.mapper.custom;

import java.sql.Timestamp;
import java.util.Date;

import beans.mapper.BidirectionalMapper;

public class DateTimestamp implements BidirectionalMapper<Date, Timestamp> {

    @Override
    public void map(Date source, Timestamp target) {
        target.setTime(source.getTime());
    }

    @Override
    public void reverse(Timestamp target, Date source) {
        target.setTime(source.getTime());
    }

}
