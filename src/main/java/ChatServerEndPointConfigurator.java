import javax.websocket.server.ServerEndpointConfig.Configurator;


public class ChatServerEndPointConfigurator extends Configurator {
    private ChatServerEndPoint chatServer = new ChatServerEndPoint();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass)
            throws InstantiationException {
        return (T)chatServer;
    }

}
