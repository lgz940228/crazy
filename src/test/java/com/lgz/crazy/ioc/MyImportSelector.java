package com.lgz.crazy.ioc;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.lgz.crazy.ioc.CatImportSelector","com.lgz.crazy.ioc.DogImportSelector"};
    }
}
