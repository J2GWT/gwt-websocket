package ir.moke.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import ir.moke.client.ws.CloseEvent;
import ir.moke.client.ws.WebSocket;
import ir.moke.client.ws.WebSocketHandler;
import ir.moke.shared.Person;

import java.util.ArrayList;
import java.util.List;

public class gwt implements EntryPoint {

    @Override
    public void onModuleLoad() {
        CellTable<Person> cellTable = new CellTable<>();
        cellTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        TextColumn<Person> nameColumn = new TextColumn<Person>() {
            @Override
            public String getValue(Person person) {
                return person.getName();
            }
        };
        TextColumn<Person> idColumn = new TextColumn<Person>() {
            @Override
            public String getValue(Person person) {
                return String.valueOf(person.getId());
            }
        };

        cellTable.addColumn(idColumn);
        cellTable.addColumn(nameColumn);


        WebSocket webSocket = new WebSocket("localhost", 8080, "/test");
        webSocket.addHandler(new WebSocketHandler() {
            @Override
            public void onOpen() {

            }

            @Override
            public void onMessage(String message) {
                JSONValue jsonValue = JSONParser.parseStrict(message);
                JSONArray array = jsonValue.isArray();
                List<Person> personList = new ArrayList<>();
                if (array.size() > 0) {
                    for (int i = 0; i < array.size(); i++) {
                        Person person = new Person();
                        String id = array.get(i).isObject().get("id").toString();
                        String name = array.get(i).isObject().get("name").toString();
                        person.setName(name);
                        person.setId(Long.parseLong(id));
                        personList.add(person);
                    }
                }

                cellTable.setRowData(personList);
                cellTable.addLoadingStateChangeHandler(loadingStateChangeEvent -> Window.alert(loadingStateChangeEvent.getLoadingState().toString()));
            }

            @Override
            public void onError() {

            }

            @Override
            public void onClose(CloseEvent event) {

            }
        });
        webSocket.connect();


        RootPanel.get("data").add(cellTable);
    }
}
