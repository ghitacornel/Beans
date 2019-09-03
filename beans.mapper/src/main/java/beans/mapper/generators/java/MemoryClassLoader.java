package beans.mapper.generators.java;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.ArrayList;
import java.util.List;

class MemoryClassLoader extends ClassLoader {

    // compiler used
    private static final JavaCompiler compiler;

    static {
        compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new RuntimeException(
                    "No compiler found, please run it under a JDK environment");
        }
    }

    // file manager used
    private final MemoryFileManager manager = new MemoryFileManager(compiler);

    public MemoryClassLoader(SourceFile source) {
        List<SourceFile> list = new ArrayList<>();
        list.add(source);
        compiler.getTask(null, manager, null, null, null, list).call();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        synchronized (manager) {
            OutputFile outputFile = manager.getMap().get(name);
            if (outputFile != null) {
                byte[] array = outputFile.toByteArray();
                return defineClass(name, array, 0, array.length);
            }
        }
        return super.findClass(name);
    }
}
