package uk.co.therhys;

import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ConfigParser {
    private File configFile;

    public ConfigParser(String file){
        this(new File(file));
    }

    public ConfigParser(File file){
        configFile = file;

        if(! file.exists()){
            System.err.printf("Config file %s does not exist!%n", file.getAbsolutePath());
            System.exit(1);
        }
    }

    public Map<String, Host> getHosts(){
        try {
            Ini ini = new Ini(configFile);

            return ini.entrySet().stream()
                    .collect(
                            toMap(Map.Entry::getKey, i -> {
                                Profile.Section sect = ini.get(i.getKey());
                                return new Host(
                                        sect.get("host"),
                                        Integer.parseInt(sect.get("port")),
                                        sect.containsKey("internalPort")? Integer.parseInt(sect.get("internalPort")) : Integer.parseInt(sect.get("port"))
                                );
                            })
                    );
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
