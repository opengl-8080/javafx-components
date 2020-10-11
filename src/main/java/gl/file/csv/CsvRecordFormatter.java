package gl.file.csv;

import java.util.List;

public interface CsvRecordFormatter<T> {
    List<String> format(T record);
}
