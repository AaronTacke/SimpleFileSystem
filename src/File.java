public class File extends Element {
    private String content;

    // Create an empty file
    public File(Folder parent, String name) {
        // super calls the constructor of Element
        super(parent, name);
        this.content = "";
    }

    // Copy constructor
    public File(File file) {
        super(file);
        content = file.content;
    }

    @Override
    public String getTree() {
        return getName();
    }

    @Override
    public boolean search(Element e) {
        return e == this;
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
