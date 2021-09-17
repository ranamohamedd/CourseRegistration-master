package sample.java.db;

import javafx.collections.ObservableList;

import java.util.Map;

public interface DBOperations {

    public String addOperation(Object obj);
    public ObservableList<Map> viewOperation();
    public String deleteOperation(String key);
    public String updateOperation(Object obj);
}
