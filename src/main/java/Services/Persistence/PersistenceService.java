package Services.Persistence;

import java.io.IOException;
import java.util.List;

public interface PersistenceService {
    boolean save(List<String> data, String path) throws IOException;
    boolean load(String path, List<String> data) throws IOException;
}
