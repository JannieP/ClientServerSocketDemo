package net.c0nan.dhs.cs.interfaces;

import java.io.IOException;

/**
 *
 * The interface {@code IServer}
 * defines the contract for Server
 *
 * @author Jannie
 * Date: 2013/07/13
 *
 */
public interface IServer {
    String getFileContent(String pathToFile) throws IOException;
    boolean isBound();
}
