package net.md_5.bungee.api.connection;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;

/**
 * Represents a player who's connection is being connected to somewhere else,
 * whether it be a remote or embedded server.
 */
public interface ProxiedPlayer extends Connection, CommandSender
{

    /**
     * Gets this player's display name.
     *
     * @return the players current display name
     */
    String getDisplayName();

    /**
     * Sets this players display name to be used as their nametag
     * name.
     *
     * @param name the name to set
     */
    void setDisplayName(String name);

    /**
     * Connects / transfers this user to the specified connection, gracefully
     * closing the current one. Depending on the implementation, this method
     * might return before the user has been connected.
     *
     * @param target the new server to connect to
     */
    void connect(ServerInfo target);

    /**
     * Gets the server this player is connected to.
     *
     * @return the server this player is connected to
     */
    Server getServer();

    /**
     * Gets the ping time between the proxy and this connection.
     *
     * @return the current ping time
     */
    //int getPing();

    /**
     * Send a plugin message to this player.
     *
     * @param channel the channel to send this data via
     * @param data the data to send
     */
    void sendData(String channel, byte[] data);

    /**
     * Get the pending connection that belongs to this player.
     *
     * @return the pending connection that this player used
     */
    PendingConnection getPendingConnection();

    /**
     * Make this player chat (say something), to the server he is currently on.
     *
     * @param message the message to say
     */
    void chat(String message);

    /**
     * Get the server which this player will be sent to next time the log in.
     *
     * @return the server, or null if default
     */
    ServerInfo getReconnectServer();

    /**
     * Set the server which this player will be sent to next time the log in.
     *
     * @param server the server to set
     */
    void setReconnectServer(ServerInfo server);
}
