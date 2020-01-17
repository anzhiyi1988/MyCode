package orz.anzhy.code;


public enum Code {

    C1("code one", "a", 1), C2("code two", "b", 1), C3("code three", "c", 0);


    private String name;
    private String value;
    private int flag;

    Code(String name, String value, int flag) {
        this.name = name;
        this.value = value;
        this.flag = flag;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return this.name + ":" + this.value;
    }
}
