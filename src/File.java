public class File extends Element {
    private String content;

    // Create an empty file
    public File(Element parent, String name) {
        // super calls the constructor of Element
        super(parent, name);
        this.content = "";
    }

    // Adds content to a file
    public void write(String content) {
        this.content = content;
    }

    // Return content of File
    public String read() {
        return content;
    }
}
