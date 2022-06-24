public abstract class Element {
    protected Element parent;
    private final String name;

    // Constructor setting the attributes
    public Element(Element parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    // Return the name of the element
    public String getName() {
        return name;
    }

    // Return the parent element
    public Element getParent() {
        return parent;
    }

    // Return the whole path of the element
    public String getPath() {
        if (parent == this) return name;
        return parent.getPath() + "/" + name;
    }

    // Check, whether arbitrary objects are folders
    public static boolean isFolder(Object object) {
        return object instanceof Folder;
    }

    // Check, whether arbitrary objects are files
    public static boolean isFile(Object object) {
        return object instanceof File;
    }

    // Starts the UI with a short How-To Guide
    public static void main(String[] args) {
        Folder f = new Folder();
        f.printHelp();
        f.ui();
    }
}
