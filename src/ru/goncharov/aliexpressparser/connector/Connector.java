package ru.goncharov.aliexpressparser.connector;

import java.net.HttpURLConnection;

public interface Connector {
    HttpURLConnection getConnect();
    void setOffset(int offset);
    int getOffset();
}
