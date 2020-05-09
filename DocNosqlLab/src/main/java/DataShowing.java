import com.microsoft.azure.eventhubs.EventHubException;

import java.io.IOException;

public interface DataShowing {
    void showInfo() throws IOException, EventHubException;
}
