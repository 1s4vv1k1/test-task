public class Airport {
    private int lineNumber;
    private String parameter;
    private String wholeLine;

    public Airport(int lineNumber, String parameter) {
        this.parameter = parameter;
        this.lineNumber = lineNumber;
    }
    public int getLineNumber() {
        return lineNumber;
    }
    public String getParameter() {
        return parameter;
    }
    public void setWholeLine(String wholeLine) {
        this.wholeLine = wholeLine;
    }
    @Override
    public String toString() {
        return parameter + wholeLine;
    }
}
