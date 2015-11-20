package citrix.gotowebinar.automation;

public enum Browser {
    FIREFOX("firefox"), CHROME("chrome"), IE("internet explorer");

    private String name;

    Browser(String name) { this.name = name; }

    @Override
    public String toString() {
        return this.name;
    }


}
