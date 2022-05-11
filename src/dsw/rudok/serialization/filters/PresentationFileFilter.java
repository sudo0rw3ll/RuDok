package dsw.rudok.serialization.filters;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PresentationFileFilter extends FileFilter {

    @Override
    public String getDescription() {
        return "RuDok Presentation File (*.rps)";
    }

    @Override
    public boolean accept(File f) {
        return (f.isDirectory() || f.getName().toLowerCase().endsWith(".rps"));
    }
}
