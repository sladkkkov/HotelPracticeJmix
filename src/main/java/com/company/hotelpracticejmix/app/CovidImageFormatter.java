package com.company.hotelpracticejmix.app;

import io.jmix.core.FileRef;
import io.jmix.ui.component.formatter.Formatter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CovidImageFormatter implements Formatter<FileRef> {

    @Override
    public String apply(FileRef value) {
        return value.getFileName();
    }
}