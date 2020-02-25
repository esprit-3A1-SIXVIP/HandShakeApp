package IServices;

import java.sql.SQLException;
import javafx.collections.ObservableList;

public interface IService<T> {
  void ajouter(T paramT) throws SQLException;
  
  boolean delete(T paramT) throws SQLException;
  
  boolean update(T paramT) throws SQLException;
  
  ObservableList<T> readAll() throws SQLException;
}

