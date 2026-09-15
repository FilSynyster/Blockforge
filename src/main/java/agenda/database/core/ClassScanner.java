package agenda.database.core;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {

    private String rootPackage;
    private String entityPackage;

    public List<Class<?>> findClassesWithAnnotation(Class<? extends Annotation> annotation) {

        List<Class<?>> ret = new ArrayList<>();
        try (ScanResult scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .acceptPackages(entityPackage)
                .scan()
        ) {

            ClassInfoList classes = scanResult.getClassesWithAnnotation(annotation);

            for (ClassInfo classInfo : classes) {
                ret.add(classInfo.loadClass());
            }
        }

        return ret;
    }

    public List<Class<?>> findSubInterfaces(Class<?> clazz) {

        List<Class<?>> ret = new ArrayList<>();

        try (ScanResult result = new ClassGraph().acceptPackages(rootPackage).enableClassInfo().scan()) {

            ClassInfoList interfaces = result.getAllInterfaces();

            for (ClassInfo info : interfaces) {

                if (info.implementsInterface(clazz.getName())) {
                    ret.add(info.loadClass());
                }
            }
        }

        return ret;
    }


    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }
}
