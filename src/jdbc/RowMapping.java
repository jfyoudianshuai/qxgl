package jdbc;

import java.util.Map;

public interface RowMapping {
     public <T>T mapping(Map<String, Object> row);
}
