public abstract class Element {
    protected Folder parent;
    private String name;

    // Constructor setting the attributes
    public Element(Folder parent, String name) {
        if (!isCorrectName(name))
            throw new IllegalArgumentException("Namen dürfen weder leer sein, noch Sonderzeichen enthalten.");
        this.parent = parent;
        this.name = name;
    }

    // Copy constructor
    public Element(Element element) {
        parent = element.parent;
        name = element.name;
    }

    // Return the name of the element
    public String getName() {
        return name;
    }

    // Renames an element safely (checks rules for names and existing names in parent directory)
    public boolean rename(String name) {
        if (parent.getChild(name) != null) {
            System.out.println("\"" + name + "\" existiert bereits in " + parent.getName() + ".");
            return false;
        } else if (!isCorrectName(name)) {
            System.out.println("Namen dürfen weder leer sein, noch Sonderzeichen enthalten.");
            return false;
        } else {
            this.name = name;
            return true;
        }
    }

    // Return the parent element
    public Folder getParent() {
        return parent;
    }

    // Return the whole path of the element
    public String getPath() {
        if (parent == this) return name;
        return parent.getPath() + "/" + name;
    }

    // Check, whether a String abides by the rules for a name of our File System
    public static boolean isCorrectName(String str) {
        return str != null && !str.contains(".") && !str.contains("/") && !str.contains(" ") && !str.contains("\n") && !str.equals("");
    }

    // Check, whether arbitrary objects are folders
    public static boolean isFolder(Object object) {
        return object instanceof Folder;
    }

    // Check, whether arbitrary objects are files
    public static boolean isFile(Object object) {
        return object instanceof File;
    }

    // Prints a tree of the underlaying file structure recursively
    public abstract String getTree();

    // Searches for element recursively
    public abstract boolean search(Element e);

    // Copies arbitrary element
    public static Element copy(Element element) {
        if (isFile(element)) return new File((File) element);
        if (isFolder(element)) return new Folder((Folder) element);
        return null;
    }

    // Starts the UI with a short How-To Guide
    public static void main(String[] args) {
        Folder f = new Folder();
        f.printHelp();
        f.ui();
    }
}
