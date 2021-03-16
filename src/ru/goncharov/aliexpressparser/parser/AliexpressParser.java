package ru.goncharov.aliexpressparser.parser;

import ru.goncharov.aliexpressparser.connector.AliexpressConnector;
import ru.goncharov.aliexpressparser.connector.Connector;
import ru.goncharov.aliexpressparser.mapper.CSVMapper;
import ru.goncharov.aliexpressparser.mapper.JSONAliexpressMapper;
import ru.goncharov.aliexpressparser.mapper.Mapper;
import ru.goncharov.aliexpressparser.reader.AliexpressReader;
import ru.goncharov.aliexpressparser.reader.IReader;

public class AliexpressParser {
    private Connector connector = new AliexpressConnector();
    private IReader reader = new AliexpressReader(connector);
    private Mapper mapper = new JSONAliexpressMapper(reader);
    private CSVMapper csvMapper = new CSVMapper(mapper);

    public void massParse(int n){
        for (int i = 0; i < n; i++) {
            csvMapper.writeToCSV();
            connector.setOffset(connector.getOffset()+12);
        }
    }
}
