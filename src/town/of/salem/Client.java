package town.of.salem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import roles.RoleBuilder;
import roles.Roles;

public class Client {

    private ServerConnection connection;
    private Thread playerThread;
    private Lobby lobby;

    public Client(Lobby lobby) { //created when user clicks join game
        this.lobby = lobby;
        try {
            Socket socket = new Socket("127.0.0.1", 4444); //create out socket to localhost
            connection = new ServerConnection(socket); //create out server connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ServerConnection {
        ObjectInputStream input;
        ObjectOutputStream output;
        Socket socket;

        private ServerConnection(Socket s) throws IOException {
            this.socket = s;
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream()); //open our streams
            output.writeObject(Game.player); //send our player to the server
            waitForMessage(); //wait for a response back
        }
    }

    public void waitForMessage() {
        Runnable players = () -> {
            while (true) {
                Object obj = null;
                try {
                    obj = connection.input.readObject();
                    if(obj instanceof String) {
                            lobby.chatbox.append((String)obj);
                        }
                    else if(obj instanceof ArrayList) {
                        lobby.g.players = (ArrayList<Player>)obj;
                        lobby.refreshList();
                    }
                    else if(obj instanceof Roles) {
                        Game.player = new RoleBuilder().returnRole(Game.player.name, (Roles)obj);
                        lobby.chatbox.append("Your role is: " + (Roles)obj);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        playerThread = new Thread(players);
        playerThread.start();
    }
    
    public void send(Object obj) {
        try {
            connection.output.writeObject(obj);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
