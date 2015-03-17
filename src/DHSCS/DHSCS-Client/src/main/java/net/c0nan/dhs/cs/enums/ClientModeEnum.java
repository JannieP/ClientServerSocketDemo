package net.c0nan.dhs.cs.enums;

/**
 *
 * The enum {@code ClientModeEnum}
 * defines the modes for ClientMain parameters
 *
 * @author Jannie
 * Date: 2013/07/13
 *
 */
public enum ClientModeEnum {
    STANDALONE("Standalone Mode"),NETWORK("Network Mode");

    private String description;

    private ClientModeEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
