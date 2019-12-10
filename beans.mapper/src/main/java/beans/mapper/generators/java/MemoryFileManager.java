package beans.mapper.generators.java;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject.Kind;
import java.util.HashMap;
import java.util.Map;

class MemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private final Map<String, OutputFile> map = new HashMap<>();

    public MemoryFileManager(JavaCompiler compiler) {
        super(compiler.getStandardFileManager(null, null, null));
    }

    @Override
    public OutputFile getJavaFileForOutput(Location location, String className, Kind kind, FileObject source) {

        OutputFile outputFile = map.get(className);

        if (outputFile == null) {
            outputFile = new OutputFile(className, kind);
            map.put(className, outputFile);
        }

        return outputFile;
    }

    public Map<String, OutputFile> getMap() {
        return map;
    }

}
