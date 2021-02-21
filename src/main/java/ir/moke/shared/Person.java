package ir.moke.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class Person implements IsSerializable, Serializable {
    private long id ;
    private String name ;

    public Person() {
    }

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
