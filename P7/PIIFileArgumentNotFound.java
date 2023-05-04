package P7;

public class PIIFileArgumentNotFound extends Exception {

    private String filename;

    public PIIFileArgumentNotFound(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }
}
