package data;

/**
 * @author : August, Sebastian
 */
public class InMemoryDataSource implements IDataSource {


    private static final InMemoryDataSource dataSource = new InMemoryDataSource();

    public static InMemoryDataSource getInstance(){
        return dataSource;
    }

    private InMemoryDataSource(){

    }
}
