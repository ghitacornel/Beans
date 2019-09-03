package beans.mapper.generators.java;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * Output class file
 *
 * @author Cornel
 */
class OutputFile extends SimpleJavaFileObject {

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public OutputFile(String name, Kind kind) {
        super(URI.create("memo:///" + name.replace('.', '/') + kind.extension),
                kind);
    }

    public byte[] toByteArray() {
        return baos.toByteArray();
    }

    @Override
    public ByteArrayOutputStream openOutputStream() {
        return baos;
    }
}
