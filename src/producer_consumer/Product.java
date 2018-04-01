package producer_consumer;

public class Product {

    private int code;
    private String type;
    private String kind;
    private String name;

    public Product(int code, String type, String kind, String name) {
        this.type = type;
        this.code = code;
        this.name = name;
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
