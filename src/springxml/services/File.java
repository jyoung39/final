package springxml.services;

public class File implements LogService {
    private String log;

    @Override
    public void setLog(String str) {
        log = str;
    }

    public String getLog(){
        return log;
    }
}
