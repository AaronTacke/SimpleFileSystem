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
        System.out.println("Benutzung:");
        System.out.println("cd ORDNER - Öffnet Ordner ORDNER");
        System.out.println("cd .. - Schließt aktuellen Ordner");
        System.out.println("ls - listet alle Elemente im Ordner auf");
        System.out.println("pwd - gibt den Pfad des aktuellen Ordners aus");
        System.out.println("mkdir ORDNER - erstellt Ordner namens ORDNER");
        System.out.println("touch DATEI - erstellt Datei namens DATEI");
        System.out.println("cat DATEI - gibt Inhalt der Datei DATEI aus");
        System.out.println("nano DATEI - ermöglicht Hinzufügen einer Zeile an DATEI");
        System.out.println("TODO help - gibt diese Benutzungs-Hilfe erneut aus");
        System.out.println("TODO clear DATEI - entfernt den Inhalt aus DATEI");
        System.out.println("TODO rm ELEMENT - entfernt Datei oder Ordner namens ELEMENT");
        System.out.println("TODO tree - gibt einen Verzeichnis-Baum ab dem aktuellen Ordner aus");
        System.out.println("TODO mv ELEMENT PATH - verschiebt Element ELEMENT nach PATH");
        System.out.println("TODO cp ELEMENT PATH - kopiert Element ELEMENT nach PATH");

        new Folder().ui();
    }
}
