package alfsoft.com.prueba2;

import java.io.Serializable;

public class Datos_Posts implements Serializable {
    private int Id;
    private int UserId;
    private String Title;
    private String Body;

    public Datos_Posts(int id, int userId, String title, String body) {
        Id = id;
        UserId = userId;
        Title = title;
        Body = body;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }
}
