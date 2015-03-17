package net.c0nan.dhs.cs.client;

import net.c0nan.dhs.cs.exceptions.ClientException;

/**
 * The interface {@code IClient}
 * defines the contract for Clients
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public interface IClient {

    public void runClient() throws ClientException;

}
