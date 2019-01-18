package data;

/**
 * @author : August, Sebastian
 */
public class InMemoryDataSource implements IDataSource {


    private InMemoryDataSource dataSource;

    public static InMemoryDataSource getInstance(){
        if(dataSource != null){
            return dataSource;
        }
        dataSource = new InMemoryDataSource();
        return dataSource;
    }

    private InMemoryDataSource(){

    }
}
