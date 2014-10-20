package com.winit.svr.spring.annotations;

import org.springframework.context.annotation.Import;

import com.winit.svr.spring.components.config.annotations.LabelConfiguration;

import java.lang.annotation.*;

/**
 * @author Josh Long
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LabelConfiguration.class)
public @interface EnableActiviti {
}
