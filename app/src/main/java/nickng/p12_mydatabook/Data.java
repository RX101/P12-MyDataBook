package nickng.p12_mydatabook;

import java.io.Serializable;

/**
 * Created by 15056201 on 10/8/2017.
 */

public class Data implements Serializable {
    private int id;
    private String data;

    public Data(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}