package town.of.salem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import roles.Roles;

public class Server {

    private ServerSocket serverSocket;
    private Thread clientThread;
    private Lobby lobby;
    private ArrayList<ClientConnection> players;
    private final Roles[] classic = {Roles.SHERIFF, Roles.DOCTOR, Roles.INVESTIGATOR, Roles.JAILOR, Roles.MEDIUM, Roles.GODFATHER, Roles.FRAMER, Roles.EXECUTIONER, Roles.ESCORT, Roles.MAFIOSO, Roles.LOOKOUT, Roles.SERIALKILLER,
    Roles.TOWNKILLING, Roles.JESTER, Roles.RANDOMTOWN};

    public Server(Lobby lobby) { //created when user hosts a game
        this.lobby = lobby;
        players = new ArrayList<>(); //create list of socket connections
        try {
            serverSocket = new ServerSocket(4444); //open our serverSocket
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForClient(); //wait for clients to join
    }

    private void waitForClient() {
        Runnable client = () -> {
            try {
                while (true) {
                    Socket socket = serverSocket.accept(); //wait for a client
                    players.add(new ClientConnection(socket, players.size() + 1)); //create a connection with their details and then loop for more clients
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        clientThread = new Thread(client);
        clientThread.start();
    }

    private class ClientConnection {

        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        int id;

        private ClientConnection(Socket s, int id) {
            this.socket = s;
            this.id = id;
            try {
                output = new ObjectOutputStream(socket.getOutputStream());
                output.flush();
                input = new ObjectInputStream(socket.getInputStream()); //open our streams
            } catch (Exception e) {
                e.printStackTrace();
            }
            waitMessage(); //receive player data from the client
        }

        private void waitMessage() {
            Runnable waitClient = () -> {
                while (true) {
                    Object obj = null;
                    try {
                        obj = input.readObject();
                        if(obj instanceof String) {
                            lobby.chatbox.append((String)obj);
                        }
                        else if (obj instanceof Player) {
                            receivePlayer(obj);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread clientThread = new Thread(waitClient);
            clientThread.start();
        }

        private void receivePlayer(Object obj) {
            Player player = (Player)obj;
            lobby.g.players.add(player);
            lobby.refreshList();
            sendAll(lobby.g.players);
        }
        
        private void send(Object obj) {
            try {
                output.writeObject(obj);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGame() {
        assignRoles();
    }

    private void assignRoles() {
        ArrayList<Integer> range = new ArrayList<>();
        for (int x = 0; x < classic.length; x++) {
            range.add(x);
        }
        Collections.shuffle(range);
        for (ClientConnection c : players) {
            c.send(classic[range.remove(0)]);
        }
    }

    public void sendAll(Object obj) {
        try {
            for (ClientConnection connection : players) { //for every client we have connected
                connection.output.writeObject(obj);
                connection.output.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
