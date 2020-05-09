import com.microsoft.azure.eventhubs.EventHubException;

import java.io.IOException;

public class Context {
    DataShowing dataShowing;

    public Context(DataShowing dataShowing){
        this.dataShowing = dataShowing;
    }

    public void executeInfo() throws IOException, EventHubException {
        dataShowing.showInfo();
    }

}
