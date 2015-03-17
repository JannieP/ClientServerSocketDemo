package net.c0nan.dhs.cs.client.impl;

import net.c0nan.dhs.cs.client.IClient;
import net.c0nan.dhs.cs.exceptions.ClientException;
import net.c0nan.dhs.cs.out.OutputStream;

/**
 *
 * The class {@code AbstractClient} implements {@code IClient}
 * is the base Client and sets the redirect for the {@code System.out}
 *
 * @author Jannie
 * Date: 2013/07/13
 *
 */
public abstract class AbstractClient implements IClient{

    /**
     * Redirects the {@code System.out} to an output defined in the Common Library
     */
    protected void setOut() {
        OutputStream outputStream = new OutputStream(System.out);
        System.setOut(outputStream);
    }

    /**
     * The generic method defined by the interface {@code IClient}
     * @throws ClientException
     */
    public abstract void runClient() throws ClientException;

}
