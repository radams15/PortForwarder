package uk.co.therhys;

import lombok.Data;

@Data
public class Host {
    public final String hostname;
    public final int internalPort;
    public final int externalPort;
}
