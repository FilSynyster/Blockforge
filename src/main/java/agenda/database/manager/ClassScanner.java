package agenda.database.manager;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {

    private String packageName;

    public ClassScanner(String packageName) {
        this.packageName = packageName;
    }

    public List<Class<?>> findClassesWithAnnotation(Class<? extends Annotation> annotation) {

        List<Class<?>> ret = new ArrayList<>();

        try (ScanResult scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .acceptPackages(packageName)
                .scan()
        ) {

            ClassInfoList classes = scanResult.getClassesWithAnnotation(annotation);

            for (ClassInfo classInfo : classes) {
                ret.add(classInfo.loadClass());
            }
        }

        return ret;
    }

    public List<Class<?>> findSubclasses(Class<?> clazz) {
        List<Class<?>> ret = new ArrayList<>();
        try (ScanResult result = new ClassGraph()
                .acceptPackages(packageName)
                .scan()
        ) {
            ClassInfoList subclasses = result.getSubclasses(clazz.getName());
            for (ClassInfo info : subclasses) {
                ret.add(info.loadClass());
            }
        }
        return ret;
    }


}
