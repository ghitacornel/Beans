package beans.mapper;

public interface Mapper<S, T> {

    void map(S source, T target);

    // TODO add merge functionality with MERGE policy or COPY policy

    // TODO add create new instance of bean of different specified class

    // TODO add toString method like web corresponding js

    // TODO add toMap method

    // TODO add additional functionalities provided by beanutils

    // TODO chain convertors in collections, sub properties

    // search apache bean utils copy based on reflection vs copy based on code generation
}