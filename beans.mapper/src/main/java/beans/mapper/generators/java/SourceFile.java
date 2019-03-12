package beans.mapper.generators.java;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * The source file
 *
 * @author Cornel
 *
 */
class SourceFile extends SimpleJavaFileObject {

    // class String content
    private final String content;

    public SourceFile(String canonicalClassName, String content) {
        this(canonicalClassName, Kind.SOURCE, content);
    }

    private SourceFile(String name, Kind kind, String content) {
        super(URI.create("memo:///" + name.replace('.', '/') + kind.extension),
                kind);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(boolean ignore) {
        return content;
    }

}
