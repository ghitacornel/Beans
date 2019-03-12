package beans.mapper;

public interface BidirectionalMapper<S, T> extends Mapper<S, T> {

    void reverse(T target, S source);

}
